package org.inchain;

import org.inchain.task.InchainModule;
import org.inchain.task.InchainTread;

public class RpcServer extends InchainModule
{
    private static RpcServer instance = null;

    public static RpcServer getInstance(){
        if(instance == null){
            instance = new RpcServer();
        }
        return instance;
    }

    public static void startModule(){
        InchainTread t1 = new InchainTread("rpchttpThread"){
            @Override
            public void run(){

            }
        };
        t1.start();
        InchainTread t2 = new InchainTread(""){
            @Override
            public void run(){

            }
        };
        t2.start();
        t2.setParent(instance);
    }
}
