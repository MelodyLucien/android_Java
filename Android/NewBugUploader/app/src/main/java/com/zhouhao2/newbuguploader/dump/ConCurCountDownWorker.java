package com.zhouhao2.newbuguploader.dump;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.zhouhao2.newbuguploader.mainflow.MainConstants;

import android.util.Log;

public class ConCurCountDownWorker {

    // default number is 10
    private int WORKER_NUMBER = 10;
    private CountDownLatch countDownLatch = null;
    private ArrayList<String> cmds = null;

    public ConCurCountDownWorker(ArrayList<String> cmds) {
        resetWorkerList(cmds);
    }

    // reset the infomation for resuing
    public void resetWorkerList(ArrayList<String> cmds) {
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
            onRun();
            // countdown by one when the work is done
            Log.i(MainConstants.TAG, "the worker " + cmdIndex + " is done!!!");
            countDownLatch.countDown();
        }

        abstract void onRun();

        public int getIndex() {
            return cmdIndex;
        }
    }

    /**
     * this method should run in the main thread
     * 
     * @return
     */
    public boolean run() {

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
                    checkError(p);
                    try {
                        p.waitFor();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                /**
                 * check the error of press's result
                 * 
                 * @param p
                 */
                private void checkError(Process p) {

                    InputStreamReader in = new InputStreamReader(
                            p.getErrorStream());
                    BufferedReader bf = new BufferedReader(in);
                    String line = null;
                    try {
                        while ((line = bf.readLine()) != null) {
                            Log.i(MainConstants.TAG, line);
                        }
                    } catch (IOException e1) {
                        e1.printStackTrace();
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
                }
            };
            service.execute(runnable);
        }

        try {
            // when countdown to zero continue otherwise stay here
            countDownLatch.await();
            Log.i(MainConstants.TAG, "all work is down");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }

    public void setCmds(ArrayList<String> cmds) {
        this.cmds = cmds;
    }
}
