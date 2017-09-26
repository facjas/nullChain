package org.inchain.task;

import java.util.Date;


public class InchainTread extends Thread {

    public enum STATUS{
        RUNING,
        HUNGUP,
    }
    private STATUS status;
    private InchainModule parent = null;
    private Date starttime;

    public InchainTread(){
        this("noName");
    }

    public InchainTread(String name){
        super();
        this.setName(name);
        starttime = new Date();
        //DOTO change to log
        System.out.println("log debug: "+this.getClass().getName()+" start runing");
    }

    public String getStartTimeStr(){
        return starttime.toLocaleString();
    }

    public void setParent(InchainModule parent){
        this.parent = parent;
        parent.regTread(this);
    }

    public void setStatus(STATUS status){
        this.status = status;
    }

    public STATUS getStatus(){
        return this.status;
    }
}
