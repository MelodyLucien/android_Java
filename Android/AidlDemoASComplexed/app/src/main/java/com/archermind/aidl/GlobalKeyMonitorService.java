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

    /**
     * two list for managing client binding info
     */
    private final ConcurrentHashMap<Integer, MyDeathRecipient> mMyDeathRecipents = new ConcurrentHashMap<Integer, MyDeathRecipient>();

    class MyDeathRecipient implements IBinder.DeathRecipient {

        private int mPid = 0;
        private IBinder mBinder = null;
        private ArrayList<Integer> mKeyEventLists = null;

        public MyDeathRecipient(int pid, IBinder b,ArrayList<Integer> keyEventLists) {
            mPid = pid;
            mBinder = b;
            mKeyEventLists = keyEventLists;
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

        @Override
        public String toString() {
            return "[ Pid :"+mPid+" Binder :"+mBinder.toString()+" Keys: "+mKeyEventLists.toString()+"]";
        }
    }

    private MyHandler mH = null;

    @Override
    public void onCreate() {
        Log.v(TAG, "onCreate()...");
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
        public void processMonitorRequest(String token,GlobalInputEventMonitorRequest request) throws RemoteException {
            Log.i(TAG, "processMonitorRequest: ");
        }

        @Override
        public void processMonitorCancel(String token) throws RemoteException {
            Log.i(TAG, "processMonitorCancel: ");
        }

        @Override
        public void releaseKeysOnly(IBinder b, int[] cb)throws RemoteException {

        }

        @Override
        public void restoreKeys(IBinder b) throws RemoteException {
            Log.i(TAG, "existing binders: " + mMyDeathRecipents.toString());
            b.unlinkToDeath((MyDeathRecipient)mMyDeathRecipents.get(Binder.getCallingPid()),0);
            removeRegisterInfo(Binder.getCallingPid());
            Log.i(TAG, "existing binders: " + mMyDeathRecipents.toString());
            Log.i(TAG, "restore binders: pid :" + Binder.getCallingPid());
        }

        @Override
        public void prohibitKeys(IBinder b, int[] cb) throws RemoteException {
            Log.i(TAG, "existing binders: " + mMyDeathRecipents.toString());
            ArrayList<Integer> keys = new ArrayList<>();
            if (cb != null && cb.length != 0) {
                for (int i = 0; i < cb.length; i++) {
                    keys.add(cb[i]);
                    Log.i(TAG, "prohibit keys:" + cb[i]);
                }
            }
            MyDeathRecipient recipient = new MyDeathRecipient(Binder.getCallingPid(), b,keys);
            b.linkToDeath(recipient, 0);
            addRegisterInfo(Binder.getCallingPid(),recipient);
            Log.i(TAG, "existing binders: " + mMyDeathRecipents.toString());
            Log.i(TAG, "prohibitKeys: callingpid :" + Binder.getCallingPid());
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
                    ArrayList<Integer> keys = recipient.getKeyEventLists();
                    Log.i(TAG, "dispatch2Tasks: "+keys.toString());
                    return NOT_PASS_TO_USER;
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

