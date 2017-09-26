package org.inchain.mq;

import org.inchain.task.InchainModule;
import org.inchain.task.InchainTread;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Niels on 2017/9/26.
 * inchain.org
 */
public abstract class MQModule extends InchainModule {
    private static MQModule instance = null;

    public static  MQModule getInstance(){

        return instance;
    }

    /**
     * 启动队列服务
     */
    public void startModule() {
//        InchainTread t1 = new InchainTread("queueStatusLogThread") {
//            @Override
//            public void run() {
//                QueueManager.startQueueStatusLog();
//            }
//        };
//        t1.setParent(instance);
//        //启动速度统计任务
//        ScheduledExecutorService service = new ScheduledThreadPoolExecutor(1);
//        service.scheduleAtFixedRate(t1, 0, QueueManager.getLatelySecond(), TimeUnit.SECONDS);
    }

}
