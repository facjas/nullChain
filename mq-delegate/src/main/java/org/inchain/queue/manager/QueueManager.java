package org.inchain.queue.manager;

import org.inchain.fqueue.exception.FileFormatException;
import org.inchain.mq.exception.QueueException;
import org.inchain.mq.intf.StatInfo;
import org.inchain.queue.PersistentQueue;
import org.inchain.queue.util.stat.StatInfoImpl;
import org.inchain.util.log.Log;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 持久化队列管理器
 * Created by Niels on 2017/9/20.
 */
public abstract class QueueManager {
    private static final Map<String, PersistentQueue> queuesMap = new HashMap<>();
    private static final Map<String, Lock> lockMap = new HashMap<>();
    //统计日志时间段
    private static final int LatelySecond = 10;

    public static void startQueueStatusLog() {
        for (Map.Entry<String, PersistentQueue> entry : queuesMap.entrySet()) {
            try {
                PersistentQueue queue = entry.getValue();
                long nowIn = queue.getStatInfo().getInCount().get();
                long nowOut = queue.getStatInfo().getOutCount().get();
                long latelyInTps = (nowIn - queue.getStatInfo().getLastInCount()) / queue.getStatInfo().getLatelySecond();
                long latelyOutTps = (nowOut - queue.getStatInfo().getLastOutCount()) / queue.getStatInfo().getLatelySecond();
                queue.getStatInfo().setLatelyInTps(latelyInTps);
                queue.getStatInfo().setLatelyOutTps(latelyOutTps);
                queue.getStatInfo().setLastInCount(nowIn);
                queue.getStatInfo().setLastOutCount(nowOut);
                Log.info(queue.getStatInfo().toString());
            } catch (Exception e) {
            }
        }
    }

    public static final int getLatelySecond() {
        return LatelySecond;
    }

    /**
     * 将队列加入管理中
     *
     * @param queueName 队列名称
     * @param queue     队列实例
     */
    public static void initQueue(String queueName, PersistentQueue queue) {
        initQueue(queueName, queue, LatelySecond);
    }

    /**
     * 将队列加入管理中
     *
     * @param queueName    队列名称
     * @param queue        队列实例
     * @param latelySecond 统计日志时间段
     */
    public static void initQueue(String queueName, PersistentQueue queue, int latelySecond) {
        if (queuesMap.containsKey(queueName)) {
            throw new QueueException("队列名称已存在");
        }
        if (latelySecond == 0) {
            latelySecond = LatelySecond;
        }
        Log.debug("队列初始化，名称：{}，单个文件最大大小：{}", queue.getQueueName(), queue.getMaxSize());
        queue.setStatInfo(new StatInfoImpl(queue.getQueueName(), queue.size(), latelySecond));
        queuesMap.put(queueName, queue);
        lockMap.put(queueName, new ReentrantLock());
    }

    public static void destroyQueue(String queueName) throws IOException, FileFormatException {
        PersistentQueue queue = queuesMap.get(queueName);
        if (null == queue) {
            throw new QueueException("队列不存在");
        }
        queue.distroy();
        queuesMap.remove(queueName);
        Log.debug("队列销毁，名称：{}。", queueName);
    }

    public static Object take(String queueName) throws InterruptedException {
        PersistentQueue queue = queuesMap.get(queueName);
        if (null == queue) {
            throw new QueueException("队列不存在");
        }
        Object value = queue.take();
        queue.getStatInfo().takeOne();
        Log.debug("从队列中取出数据，名称：{}，当前长度：{}。", queueName, queue.size());
        return value;
    }

    public static Object poll(String queueName) {
        PersistentQueue queue = queuesMap.get(queueName);
        if (null == queue) {
            throw new QueueException("队列不存在");
        }
        Object obj = queue.poll();
        boolean notNull = null != obj;
        if (notNull) {
            queue.getStatInfo().takeOne();
            Log.debug("从队列中取出数据，名称：{}，当前长度：{}。", queueName, queue.size());
        }
        return obj;
    }

    public static void offer(String queueName, Object item) {
        PersistentQueue queue = queuesMap.get(queueName);
        if (null == queue) {
            throw new QueueException("队列不存在");
        }

        queue.offer(item);
        queue.getStatInfo().putOne();
        Log.debug("向队列中加入数据，名称：{}，当前长度：{}。", queueName, queue.size());
    }

    public static void clear(String queueName) {
        PersistentQueue queue = queuesMap.get(queueName);
        if (null == queue) {
            throw new QueueException("队列不存在");
        }
        Log.debug("清空队列数据，名称：{}，当前长度：{}。", queueName, queue.size());
        queue.clear();
    }

    public static void close(String queueName) throws QueueException {
        PersistentQueue queue = queuesMap.get(queueName);
        if (null == queue) {
            throw new QueueException("队列不存在");
        }
        try{
            queue.close();
        }catch(Exception e){
            throw new QueueException(e);
        }
        Log.debug("关闭队列实例，名称：{}，当前长度：{}。", queueName, queue.size());
    }

    public static long size(String queueName) {
        PersistentQueue queue = queuesMap.get(queueName);
        if (null == queue) {
            throw new QueueException("队列不存在");
        }
        return queue.size();
    }

    public static long getMaxSize(String queueName) {
        PersistentQueue queue = queuesMap.get(queueName);
        if (null == queue) {
            throw new QueueException("队列不存在");
        }
        return queue.getMaxSize();
    }

    public static StatInfo getStatInfo(String queueName) {
        PersistentQueue queue = queuesMap.get(queueName);
        if (null == queue) {
            throw new QueueException("队列不存在");
        }
        return queue.getStatInfo();
    }
}
