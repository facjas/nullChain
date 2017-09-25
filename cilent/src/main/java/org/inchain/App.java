package org.inchain;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        InchainContext appContext = InchainContext.getInchainContext();
        System.out.println(appContext.getTaskManager().info());
        appContext.getQueueService().createQueue("testqueue",100);

    }
}
