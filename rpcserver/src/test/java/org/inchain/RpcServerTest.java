package org.inchain;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for simple RpcServer.
 */
public class RpcServerTest
{
    RpcServer rpcServer;
    @Before
    public  void init(){
        rpcServer = RpcServer.getInstance();
        InchainTread t1 = new InchainTread("rpchttpThread"){
            @Override
            public void run(){

            }
        };
        t1.start();
        t1.setParent(rpcServer);
    }

    @Test
    public void RpcServerTest( )
    {
        System.out.println("---------------------molude information---------------------");
        System.out.println(rpcServer.info());
        System.out.println("---------------------molude information---------------------");
        rpcServer.start();
        System.out.println("---------------------APP information---------------------");
        System.out.println(TaskManager.getInstance().info());
        System.out.println("---------------------APP information---------------------");
    }
}
