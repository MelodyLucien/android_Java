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
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;


public class MyService extends Service {
    public final String TAG="AidlDemo.MyService";
    public final static int PASS_TO_USER = 0;
    public final static int NOT_PASS_TO_USER = 1;

    /**
     * two list for managing client binding info
     */
    private final ConcurrentHashMap<Integer,IKeyEventCallBack> mKeyEventCallBacks = new ConcurrentHashMap<Integer,IKeyEventCallBack>();
    private final ConcurrentHashMap<Integer,MyDeathRecipient> mMyDeathRecipents = new ConcurrentHashMap<Integer,MyDeathRecipient>();


    private MyHandler mH = null;

    @Override
	public void onCreate(){
		Log.v(TAG,"onCreate()...");
        HandlerThread serviceThread = new HandlerThread(TAG,Process.THREAD_PRIORITY_BACKGROUND);
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

	private final IKeyBinder.Stub mBinder=new IKeyBinder.Stub() {

		@Override
		public void unregisterCallBack(IBinder b) throws RemoteException {
            Log.i(TAG, "existing CallBacks: "+mKeyEventCallBacks.toString());
            // b.unlinkToDeath((MyDeathRecipient)mMyDeathRecipents.get(Binder.getCallingPid()),0);
            removeRegisterInfo(Binder.getCallingPid());
            Log.i(TAG, "existing CallBacks: "+mKeyEventCallBacks.toString());
            Log.i(TAG, "unregisterCallBack: callingpid :"+Binder.getCallingPid());
		}

		@Override
		public void registerCallBack(IBinder b, IKeyEventCallBack cb) throws RemoteException {
            Log.i(TAG, "existing CallBacks: "+mKeyEventCallBacks.toString());
            MyDeathRecipient recipent = new MyDeathRecipient(Binder.getCallingPid(),b);
			b.linkToDeath(recipent,0);
            addRegisterInfo(Binder.getCallingPid(),recipent,cb);
            Log.i(TAG, "existing CallBacks: "+mKeyEventCallBacks.toString());
            Log.i(TAG, "registerCallBack: callingpid :"+Binder.getCallingPid());
		}
	};

	private void
    removeRegisterInfo(int pid){
        if(mKeyEventCallBacks.containsKey(pid)) {
            mKeyEventCallBacks.remove(pid);
        }

        if(mMyDeathRecipents.containsKey(pid))
            mMyDeathRecipents.remove(pid);
    }


    private void addRegisterInfo(int pid,MyDeathRecipient myDeathRecipient,IKeyEventCallBack keyEventCallBack){
        if(mKeyEventCallBacks.get(pid) == null)
            mKeyEventCallBacks.put(pid,keyEventCallBack);
        if(mMyDeathRecipents.get(pid) == null)
            mMyDeathRecipents.put(pid,myDeathRecipient);
    }

    class MyDeathRecipient implements IBinder.DeathRecipient{

        private int mPid = 0;
        private IBinder mBinder = null;

        public MyDeathRecipient(int pid,IBinder b){
            mPid = pid;
            mBinder = b;
        }

        @Override
        public void binderDied() {
            Log.i(TAG, "binderDied: i am dying!!!");
            Log.i(TAG, "binderDied: callingprocessid :"+Binder.getCallingPid());
            Log.i(TAG, "binderDied: dying bind pid :"+getBindId());
            removeRegisterInfo(mPid);
        }

        public int getBindId(){
            return mPid;
        }
    }

    class MyHandler extends  Handler{

		public static final int LOOP = 0;

        public MyHandler(Looper looper){
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
               switch (msg.what){
				   case LOOP:
                       processKey();
                       sendEmptyMessageDelayed(LOOP,2000l);
				   	break;
			   }
        }
        
        private void processKey(){
            Log.i(TAG, "processKey: start ");
            if(interQ()== PASS_TO_USER){
                interD();
            }
            Log.i(TAG, "processKey: end ");
        }

        private int interQ(){
            Log.i(TAG, "interQ: ");
            return  dispatch2Tasks();
        }

        private int interD(){
            Log.i(TAG, "interD: ");
            return PASS_TO_USER;
        }

        private int dispatch2Tasks(){
            Log.i(TAG, "dispatch2Tasks: ");
            // I got an error trying to cast hashMap.values() to List,but fail.
            ArrayList<IKeyEventCallBack> tasks = new ArrayList<IKeyEventCallBack>(mKeyEventCallBacks.values());
            if(tasks != null && tasks.size() != 0) {
                for (IKeyEventCallBack tmpCallBack : tasks) {
                    if (tmpCallBack != null) {
                        try {
                            int result = tmpCallBack.onKeyEventCallBack(1, true, 1, 1, 1, 1);
                            if (result == PASS_TO_USER) {
                                Log.i(TAG, "continue pass to user: ");
                                continue;
                            } else if (result == NOT_PASS_TO_USER) {
                                Log.i(TAG, "not pass to user: ");
                                return NOT_PASS_TO_USER;
                            }
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            return PASS_TO_USER;
        }
    };

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy: ");
        super.onDestroy();
    }
}
