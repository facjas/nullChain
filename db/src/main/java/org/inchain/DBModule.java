package org.inchain;

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
