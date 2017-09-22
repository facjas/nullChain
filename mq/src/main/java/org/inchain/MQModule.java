package org.inchain;

public class MQModule extends InchainModule
{
    private static MQModule instance = null;

    public static MQModule getInstance(){
        if(instance == null){
            instance = new MQModule();
        }
        return instance;
    }
}
