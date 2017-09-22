package org.inchain;

/**
 * Created by win10 on 2017/9/20.
 */
public class InchainContext {

    private static InchainContext instance;
    private static TaskManager taskManager;

    private static DBModule dbInstance;
    private static MQModule mqInstance;
    private static NetworkModule networkModuleInstace;
    private static RpcServer rpcServerInstance;

    public static InchainContext getInchainContext(){
        if(instance == null){
            instance =new InchainContext();
        }
        return instance;
    }

    public static TaskManager getTaskManager(){
        return TaskManager.getInstance();
    }

    private InchainContext(){
        initContext();
    }

    public static void initContext(){
        initDB();
        initMQ();
        initNetwork();
        initRpcServer();
        initConsensus();
        //initContext();
    }

    public static void initDB(){
        dbInstance = DBModule.getInstance();
    }

    public static void initMQ(){
        mqInstance = MQModule.getInstance();
    }

    public static void initNetwork(){
        networkModuleInstace = NetworkModule.getInstance();
    }

    public static void initRpcServer(){
        rpcServerInstance = RpcServer.getInstance();
        RpcServer.starthttp();
    }

    public static void initConsensus(){

    }

    public static void main(String []args){
        InchainContext.initContext();
        System.out.println(getTaskManager().info());
    }
}
