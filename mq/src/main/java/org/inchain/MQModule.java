package org.inchain;

import org.inchain.queue.manager.QueueManager;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MQModule<T> extends InchainModule {
    private static MQModule instance = null;

    public static synchronized MQModule getInstance() {
        if(null==instance){
            instance = new MQModule();
        }
        return instance;
    }

    /**
     * 启动队列服务
     */
    public void startModule() {
        InchainTread t1 = new InchainTread("queueStatusLogThread") {
            @Override
            public void run() {
                QueueManager.startQueueStatusLog();
            }
        };
        t1.setParent(instance);
        //启动速度统计任务
        ScheduledExecutorService service = new ScheduledThreadPoolExecutor(1);
        service.scheduleAtFixedRate(t1, 0, QueueManager.getLatelySecond(), TimeUnit.SECONDS);
    }
}
