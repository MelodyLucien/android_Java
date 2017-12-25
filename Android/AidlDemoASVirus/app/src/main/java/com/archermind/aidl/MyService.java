package com.archermind.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import android.os.Process;
import android.os.RemoteException;
import android.util.Log;

public class MyService extends Service {
    public static String TAG = MyService.class.getSimpleName();

    private VerifyObserver mVerifyObserver = null;

    private Handler mH = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            int i =10;
            while(i<10){
                if(mVerifyObserver!=null){
                    try {
                        mVerifyObserver.onVerify(" "+i);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
             i++;
            }
            this.sendEmptyMessageAtTime(0,1000);
        }
    };

    public MyService() {
        Log.v(TAG,"onCreate()...");
        HandlerThread handlerThread = new HandlerThread(TAG, Process.THREAD_PRIORITY_BACKGROUND);
        handlerThread.start();
        mH = new Handler(handlerThread.getLooper());
        mH.sendEmptyMessage(0);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    private boolean setVerifyObserver(VerifyObserver verifyObserver){
        if(verifyObserver != null){
            Log.i(TAG, "setVerifyObserver: "+verifyObserver);
        }else{
            Log.i(TAG, "setVerifyObserver as null");
        }
        mVerifyObserver = verifyObserver;
        return true;
    }

    IVirusVerifier.Stub mBinder = new IVirusVerifier.Stub() {
        @Override
        public void registerCallBack(VerifyObserver cb) throws RemoteException {
            if(cb instanceof Binder){
                ((Binder)cb).linkToDeath(new DeathRecipient() {
                    @Override
                    public void binderDied() {
                        Log.i(TAG, "binderDied: i am died :"+Binder.getCallingPid());
                        setVerifyObserver(null);
                    }
                },0);
            }else{
                setVerifyObserver(cb);
                Log.i(TAG, "registerCallBack: ");
                cb.onVerify("getPatch");
            }
        }
    };
}
