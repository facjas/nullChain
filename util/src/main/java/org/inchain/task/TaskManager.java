package org.inchain.task;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by win10 on 2017/9/19.
 */
public class TaskManager {

    private static Map<String,InchainModule> modules;

    private static TaskManager instance = null;


    private TaskManager(){
        modules = new HashMap<String, InchainModule>();
    }

    public static TaskManager getInstance(){
        if(instance == null){
            return instance = new TaskManager();
        }
        return instance;
    }

    public Map<String,InchainModule> getModule(){
        return modules;
    }

    public InchainModule getModule(String moduleName){
        if(instance == null)
            return null;
        return modules.get(moduleName);
    }

    public void regModule(String modulename,InchainModule module){
        this.getModule().put(modulename,module);
    }
    public String info(){
        StringBuffer sb = new StringBuffer();
        sb.append("Inchain APP info:");
        int index =1;
        Iterator<String> it = modules.keySet().iterator();
        while (it.hasNext()){
            String moduleName = it.next();
            InchainModule module = modules.get(moduleName);
            sb.append(module.info());
            index++;
        }
        return sb.toString();
    }
}
