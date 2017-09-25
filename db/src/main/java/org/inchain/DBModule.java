package org.inchain;

import org.inchain.task.InchainModule;

public class DBModule extends InchainModule
{
    private static DBModule instance = null;

    public static DBModule getInstance(){
        if(instance == null){
            instance = new DBModule();
        }
        return instance;
    }
}
