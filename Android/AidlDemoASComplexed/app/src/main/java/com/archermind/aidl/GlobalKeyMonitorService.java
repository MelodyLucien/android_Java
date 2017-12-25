package com.archermind.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

public class GlobalKeyMonitorService extends Service {
    public final String TAG = "GlobalKeyMonitorService";
    public final static int PASS_TO_USER = 0;
    public final static int NOT_PASS_TO_USER = 1;
    //process flag
    public static final int FLAG_PROHIBIT = 0;
    public static final int FLAG_RELEASE_ONLY = 1;

    /**
     * two list for managing client binding info
     */
    private final ConcurrentHashMap<Integer, MyDeathRecipient> mMyDeathRecipents = new ConcurrentHashMap<Integer, MyDeathRecipient>();

    class MyDeathRecipient implements IBinder.DeathRecipient {

        private int mPid = 0;
        private IBinder mBinder = null;
        private ArrayList<Integer> mKeyEventLists = null;
        private int action = -1;

        public MyDeathRecipient(int pid, IBinder b,int action,ArrayList<Integer> keyEventLists) {
            mPid = pid;
            mBinder = b;
            mKeyEventLists = keyEventLists;
            this.action = action;
        }

        public ArrayList<Integer> getKeyEventLists(){
            if(mKeyEventLists != null){
                return mKeyEventLists;
            }
            return null;
        }
        @Override
        public void binderDied() {
            Log.i(TAG, "binderDied: i am dying!!!");
            Log.i(TAG, "binderDied: dying bind pid :" + getBindId());
            removeRegisterInfo(mPid);
        }

        public int getBindId() {
            return mPid;
        }

        public IBinder getBinder(){
            if(mBinder != null){
                return mBinder;
            }
            return null;
        }

        public int getAction(){
            if(action != -1){
               return action;
            }
            return -1;
        }

        @Override
        public String toString() {
            return "[ Pid :"+mPid+" Binder :"+mBinder.toString()+" Keys: "+mKeyEventLists.toString()+"]";
        }
    }

    private MyHandler mH = null;

    @Override
    public void onCreate() {
        Log.v(TAG, "GlobalKeyMonitorService onCreate()...");
        HandlerThread serviceThread = new HandlerThread(TAG, Process.THREAD_PRIORITY_BACKGROUND);
        serviceThread.start();
        mH = new MyHandler(serviceThread.getLooper());
        mH.sendEmptyMessage(MyHandler.LOOP);
    }

    @Override
    public void onStart(Intent intent, int startId) {
        Log.i(TAG, "onStart: ");
        super.onStart(intent, startId);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand: ");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent arg0) {
        return mBinder;
    }

    private final IGlobalKeyMonitor.Stub mBinder = new IGlobalKeyMonitor.Stub() {

        @Override
        public void processKeysByFlag(IBinder b, int action, int[] keys) throws RemoteException {
            {
                Log.i(TAG, "existing binders: " + mMyDeathRecipents.toString());
                ArrayList<Integer> tmpKeys = new ArrayList<>();
                if (keys != null && keys.length != 0) {
                    for (int i = 0; i < keys.length; i++) {
                        tmpKeys.add(keys[i]);
                        Log.i(TAG, "prohibit keys:" + keys[i]);
                    }
                }
                MyDeathRecipient recipient = new MyDeathRecipient(Binder.getCallingPid(),b,action,tmpKeys);
                b.linkToDeath(recipient, 0);
                addRegisterInfo(Binder.getCallingPid(),recipient);
                Log.i(TAG, "existing binders: " + mMyDeathRecipents.toString());
                Log.i(TAG, "prohibitKeys: callingpid :" + Binder.getCallingPid());
            }
        }

        @Override
        public void restoreKeys(IBinder b) throws RemoteException {
            Log.i(TAG, "existing binders: " + mMyDeathRecipents.toString());
            b.unlinkToDeath((MyDeathRecipient)mMyDeathRecipents.get(Binder.getCallingPid()),0);
            removeRegisterInfo(Binder.getCallingPid());
            Log.i(TAG, "existing binders: " + mMyDeathRecipents.toString());
            Log.i(TAG, "restore binders: pid :" + Binder.getCallingPid());
        }
    };

    private void removeRegisterInfo(int pid) {
        if (mMyDeathRecipents.containsKey(pid))
            mMyDeathRecipents.remove(pid);
    }


    private void addRegisterInfo(int pid, MyDeathRecipient myDeathRecipient) {
        if (mMyDeathRecipents.get(pid) == null)
            mMyDeathRecipents.put(pid, myDeathRecipient);
    }

    class MyHandler extends Handler {

        public static final int LOOP = 0;

        public MyHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case LOOP:
                    processKey();
                    sendEmptyMessageDelayed(LOOP, 2000l);
                    break;
            }
        }

        private void processKey() {
            Log.i(TAG, "processKey: start ");
            if (interQ() == PASS_TO_USER) {
                interD();
            }
            Log.i(TAG, "processKey: end ");
        }

        private int interQ() {
            Log.i(TAG, "interQ: ");
            return dispatch2Tasks();
        }

        private int interD() {
            Log.i(TAG, "interD: ");
            return PASS_TO_USER;
        }

        private int dispatch2Tasks() {
            Log.i(TAG, "dispatch2Tasks: ");
            // I got an error trying to cast hashMap.values() to List,but fail.
            if(mMyDeathRecipents.size()!=0) {
                Iterator<MyDeathRecipient> iterator = mMyDeathRecipents.values().iterator();
                while(iterator.hasNext()) {
                    MyDeathRecipient recipient = iterator.next();
                    if(recipient != null){
                        ArrayList<Integer> keys = recipient.getKeyEventLists();
                        switch (recipient.getAction()){
                            case FLAG_PROHIBIT:
                                Log.i(TAG, "dispatch2Tasks: FLAG_PROHIBIT  "+keys.toString());
                                break;
                            case FLAG_RELEASE_ONLY:
                                Log.i(TAG, "dispatch2Tasks: FLAG_RELEASE_ONLY "+keys.toString());
                                break;
                            default:
                                break;
                        }
                        return NOT_PASS_TO_USER;
                    }
                }
            }
            return PASS_TO_USER;
        }
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy: ");
        super.onDestroy();
    }
}

