package org.inchain;

import org.inchain.queue.manager.QueueManager;
import org.inchain.queue.service.QueueService;
import org.inchain.queue.service.QueueServiceFactory;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public abstract class MQModule<T> extends InchainModule implements QueueService<T> {
    private static MQModule instance = null;

    public static MQModule getInstance() {
        if (instance == null) {
            instance = QueueServiceFactory.createQueueService();
        }
        return instance;
    }

    /**
     * 启动队列服务
     */
    public static void startModule() {
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
