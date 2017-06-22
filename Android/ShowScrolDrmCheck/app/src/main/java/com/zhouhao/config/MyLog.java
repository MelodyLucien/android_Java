package com.zhouhao.config;

/**
 * 
 * @author zhouhao2
 *
 */
public class MyLog  {

    private static boolean isDebug = true;

    private static ParentLog localLog;
    
    public static void setLocalLogObject(ParentLog mylog) {
        localLog = mylog;
    }

    public static void printLog(String TAG,String msg) throws Exception {

        if (localLog == null) {
            throw new Exception("localLog is null !!");
        }
        localLog.info(TAG,msg);
    }

    /**
     * Send an {@link #INFO} log message.
     * 
     * @param tag
     *            Used to identify the source of a log message. It usually
     *            identifies the class or activity where the log call occurs.
     * @param msg
     *            The message you would like logged.
     */
    public static void info(String TAG,String msg) throws Exception {

        if (isDebug ) {
            if(localLog!=null){
                printLog(TAG, msg);
            }else{
                System.out.println(TAG + "   : " + msg);
            }
        } else {
            //do nothing
            return;
        }
    }
    
    /**
     * switch off the log
     */
    public static void switchlog(){
        isDebug=false;
    }
}
