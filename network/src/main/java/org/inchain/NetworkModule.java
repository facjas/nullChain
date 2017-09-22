package org.inchain;

/**
 * Hello world!
 *
 */
public class NetworkModule extends InchainModule
{
    private static NetworkModule instance = null;

    public static NetworkModule getInstance(){
        if(instance == null){
            instance = new NetworkModule();
        }
        return instance;
    }
}
