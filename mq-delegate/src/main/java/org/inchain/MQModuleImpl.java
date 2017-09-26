package org.inchain;

import org.inchain.mq.MQModule;
import org.inchain.queue.manager.QueueManager;
import org.inchain.task.InchainModule;
import org.inchain.task.InchainTread;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MQModuleImpl extends InchainModule {
    private static MQModuleImpl instance = null;

    public static synchronized MQModuleImpl getInstance() {
        if(null==instance){
            instance = new MQModuleImpl();
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
