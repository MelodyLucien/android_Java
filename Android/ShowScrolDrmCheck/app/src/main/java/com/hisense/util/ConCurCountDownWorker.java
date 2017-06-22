package com.hisense.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.util.Log;

import com.zhouhao.config.MyLog;
import com.zhouhao.config.PlfConstants;

/**
 * 
 * @author zhouhao2
 * 
 */
public class ConCurCountDownWorker {

    /**
     * result type
     */
    public static final int RESULT_IN = 0;
    public static final int RESULT_ERR = 1;
    public static final int RESULT_RETURN = 2;
    
    public static final String STRING_RETURN_OK = "return_OK";
    public static final String STRING_RETURN_FAIL = "return_FAILED";
    
    public static String TAG = PlfConstants.TAG;
    // default number is 10
    private int WORKER_NUMBER = 0;
    private CountDownLatch countDownLatch = null;
    private ArrayList<String> cmds = null;
    
    public static boolean isRunning = false;

    /**
     * 
     * @param cmds
     * @param Tag
     */
    public ConCurCountDownWorker(ArrayList<String> cmds, String Tag) {
        this.TAG = Tag;
        resetWorkerList(cmds, TAG);
    }

    public ConCurCountDownWorker() {
        // do nothing
    }

    /**
     * 
     * @param cmds
     * @param Tag
     */
    public void resetWorkerList(ArrayList<String> cmds, String Tag) {
        this.TAG = Tag;
        this.cmds = cmds;
        WORKER_NUMBER = cmds.size();
        countDownLatch = new CountDownLatch(WORKER_NUMBER);
    }

    /**
     * this class is to exec a cmd by a process
     * 
     * @author zhouhao2
     * 
     */
    abstract class Workable implements Runnable {
        private int cmdIndex = 0;

        public Workable(int cmdIndex) {
            this.cmdIndex = cmdIndex;
        }

        @Override
        public void run() {
            isRunning = true ;
            onRun();
            // countdown by one when the work is done
            try {
                MyLog.printLog(TAG, "the worker " + cmdIndex + " is done!!!");
            } catch (Exception e) {
                e.printStackTrace();
            }
            countDownLatch.countDown();
            isRunning = false ;
        }

        abstract void onRun();

        public int getIndex() {
            return cmdIndex;
        }
    }

    /**
     * this method should run in the main thread
     * 
     * @param resultType
     *            0 is errorcheck,1 is inputcheck,3 is return value
     * @return
     */
    public boolean run(final int resultType) {

        if (WORKER_NUMBER == 0) {
            return false;
        }

        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < WORKER_NUMBER; i++) {
            Workable runnable = new Workable(i) {
                @Override
                void onRun() {
                    Process p = null;
                    try {
                        p = Runtime.getRuntime()
                                .exec(new String[] { "sh", "-c",
                                        cmds.get(getIndex()) });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    int res=checkResult(p, resultType);
                    
                    try {
                        p.waitFor();
                        if(res == RESULT_RETURN){
                            String exitStr = null;
                            int exitValue = p.exitValue();
                            Log.i(TAG, "exit value:"+exitValue);
                            if(exitValue == 0){
                                exitStr="return_value:"+exitValue+"\n"+STRING_RETURN_OK+"\n";
                            }else if(exitValue == 1){
                                exitStr="return_value:"+exitValue+"\n"+STRING_RETURN_FAIL+"\n";
                            }else{
                                exitStr="return_value:"+exitValue+"\n"+STRING_RETURN_FAIL+"\n";
                            }
                            try {
                                MyLog.printLog(TAG,exitStr);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                /**
                 * check the error of press's result 1 stand for normal input 0
                 * stand for error information
                 * 
                 * @param p
                 */
                private int checkResult(Process p, int resultType) {

                    int res = resultType;

                    InputStreamReader in = null;

                    switch (resultType) {
                    case RESULT_IN :
                        in = new InputStreamReader(p.getInputStream());
                        break;
                    case RESULT_ERR:
                        in = new InputStreamReader(p.getErrorStream());
                        break;
                    case RESULT_RETURN:
                        return RESULT_RETURN;
                    default:
                        break;
                    }

                    BufferedReader bf = new BufferedReader(in);

                    char[] charBuffer = new char[1024];
                    try {
                        int count = 0;
                        while ((count = bf.read(charBuffer)) != -1) {
                            MyLog.printLog(TAG, new String(charBuffer));
                        }
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    if (bf != null) {
                        try {
                            bf.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    if (in != null) {
                        try {
                            in.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }

                    return res;
                }
            };

            service.execute(runnable);
        }

        try {
            // when countdown to zero continue otherwise stay here
            countDownLatch.await();
            MyLog.printLog(TAG, "all work is down");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public void setCmds(ArrayList<String> cmds) {
        this.cmds = cmds;
        resetWorkerList(cmds, TAG);
    }

    public void setOneCmd(String cmd) {
        ArrayList<String> cmdlist = new ArrayList<>();
        cmdlist.add(cmd);
        resetWorkerList(cmdlist, TAG);
    }
}
