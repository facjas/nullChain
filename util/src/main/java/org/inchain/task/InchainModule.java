package org.inchain.task;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by win10 on 2017/9/19.
 */
public class InchainModule extends InchainTread{
    private String modulename;
    private Map<String,InchainTread> threads;

    protected InchainModule(String modulename){
        threads = new HashMap<String, InchainTread>();
        String name = (modulename==null)?this.getClass().getSimpleName():modulename;
        this.setModuleName(name);
        TaskManager.getInstance().regModule(this.getModulename(),this);
    }

    protected InchainModule(){
        this(null);
    }

    private void setModuleName(String moduleName){
        this.modulename = moduleName;
    }

    public String getModulename(){
        return modulename;
    }

    public String toString(){
        StringBuffer sb = new StringBuffer();
        sb.append("\n\tInchain Module:"+modulename+" "+this.getStartTimeStr()+"");
        int index =1;
        Iterator<String> it = threads.keySet().iterator();
        while (it.hasNext()){
            String threadName = it.next();
            InchainTread tread = threads.get(threadName);
            sb.append("\n\t\tthread_"+index+" : "+threadName+" "+tread.getStartTimeStr());
            index++;
        }
        return sb.toString();
    }

    public String info(){
        return toString();
    }

    public InchainTread getTread(String threadname){

        return threads.get(threadname);
    }

    public Map<String,InchainTread> getThreads(){
        return  threads;
    }

    public void startMudule(){
        run();
    }

    public void regTread(InchainTread tread){
        threads.put(tread.getName(),tread);
    }

    public void regModule(){
        TaskManager.getInstance().regModule(this.modulename,this);
    }

}
