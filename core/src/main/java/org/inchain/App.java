package org.inchain;

import org.inchain.queue.impl.InchainFQueue;
import org.inchain.queue.service.QueueService;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        InchainContext appContext = InchainContext.getInchainContext();
        System.out.println(appContext.getTaskManager().info());
        QueueService mqservice = appContext.getMqInstance();
        appContext.getMqInstance().createQueue("testqueue",100);

    }
}
