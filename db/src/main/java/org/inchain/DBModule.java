package org.inchain;

import org.inchain.task.InchainModule;

public class DBModule extends InchainModule {
    private static DBModule instance = null;

    //private Map <String,DBservice> dbimpl = null;
    @Autowired
    private DBService service;

    public static DBModule getInstance(){
        if(instance == null){
            instance = new DBModule();
        }
        return instance;
    }

    public void start(){
        service.init();
    }



}
