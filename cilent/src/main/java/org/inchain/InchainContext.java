package org.inchain;

import org.inchain.Task.TaskManager;
import org.inchain.queue.service.QueueService;
import org.inchain.queue.service.QueueServiceFactory;

/**
 * Created by win10 on 2017/9/20.
 */
public class InchainContext {

    private static InchainContext instance;
    private static TaskManager taskManager;
    private static QueueService queueService;

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

    public TaskManager getTaskManager(){
        return TaskManager.getInstance();
    }

    /**
     * 队列服务对象
     * @return
     */
    public QueueService getQueueService(){
        if(queueService==null){
            queueService = QueueServiceFactory.createQueueService();
        }
        return queueService;
    }

    public MQModule getMqInstance(){
        return mqInstance;
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
        mqInstance.startModule();
    }

    public static void initNetwork(){
        networkModuleInstace = NetworkModule.getInstance();
    }

    public static void initRpcServer(){
        rpcServerInstance = RpcServer.getInstance();
        RpcServer.startModule();
    }

    public static void initConsensus(){

    }

    public static void main(String []args){
        InchainContext.initContext();
        System.out.println(InchainContext.getInchainContext().getTaskManager().info());
    }
}
