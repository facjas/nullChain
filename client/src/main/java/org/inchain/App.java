package org.inchain;

public class App 
{
    public static void main( String[] args ) {
        InchainContext appContext = InchainContext.getInchainContext();
        System.out.println(appContext.getTaskManager().info());
        appContext.getQueueService().createQueue("testqueue",100);

    }
}
