package com.zhouhao.config;


public abstract class ParentLog {
    
    public void set2MyLog(ParentLog subClass){
       MyLog.setLocalLogObject(subClass);
    }
   
    public abstract void info(String TAG,String msg) throws Exception;
   
}
