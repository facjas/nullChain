package org.inchain;

import org.inchain.util.log.Log;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by Niels on 2017/9/21.
 */
public class MQModuleTest {

    private final String queueName = "test1";
    private QueueService<Long> service = QueueServiceFactory.createQueueService();


    @Test
    public void test() {
        //创建
        boolean b = service.createQueue(queueName, 64);
        assertTrue(b);

        //写入
        int count = 10;
        long start = System.currentTimeMillis();
        for (; count >= 0; count--) {
            service.offer(queueName, count - 1l);
        }
        Log.info("offer count=" + count + ",use time(ms):" + (System.currentTimeMillis() - start));
        assertTrue(true);

        //取出
        while (true) {
            Log.info("start poll....");

            Long data = null;
//            try {
//                data = service.take(queueName);
//            } catch (InterruptedException e) {
//                log.error("", e);
//            }
            data = service.poll(queueName);
            if (data == null) {
                break;
            }
            Log.info("poll data:" + data);
        }


        service.destroyQueue(queueName);
        assertTrue(true);
    }


}
