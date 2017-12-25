package com.archermind.aidl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

/**
 * Created by zhouhao2 on 17-11-22.
 */

public class GlobalKeyMonitorManager extends IGlobalKeyMonitor.Stub {
    public static final String TAG = GlobalKeyMonitorManager.class.getSimpleName();
    private static GlobalKeyMonitorManager mGlobalKeyMonitorManager = null;

    private Binder mBinder = null;
    private IGlobalKeyMonitor mService = null;
    private Context mContext = null;
    private boolean isRestored = false;

    //process flag
    public static int FLAG_PROHIBIT = 0;
    public static int FLAG_RELEASE_ONLY = 1;

    private GlobalKeyMonitorManager(Context mContext) {
        this.mContext = mContext;
        mBinder = new MyBinder();
        isRestored = true;
    }

    public static synchronized GlobalKeyMonitorManager getInstance(Context mContext){
        if(mGlobalKeyMonitorManager == null){
            mGlobalKeyMonitorManager = new GlobalKeyMonitorManager(mContext);
            return mGlobalKeyMonitorManager;
        }
        return mGlobalKeyMonitorManager;
    }

    public synchronized void prohibitKeys(int[] keys){
        try {
            processKeysByFlag(mGlobalKeyMonitorManager.getBinder(),FLAG_PROHIBIT,keys);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public synchronized void releaseKeysOnly(int[] keys){
        try {
            processKeysByFlag(mGlobalKeyMonitorManager.getBinder(),FLAG_RELEASE_ONLY,keys);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void processKeysByFlag(IBinder mBinder,int action, int[] keys) throws RemoteException {
        if(isRestored ) {
            isRestored = false;
            if (keys != null && mService != null) {
                try {
                    mService.processKeysByFlag(mBinder,action, keys);
                    Log.i(TAG, "prohibitKeys: callingPid ：" + Binder.getCallingPid());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }else{
            Log.i(TAG, "please restore before prohibit!!");
        }
    }

    public synchronized void restoreKeys(){
        try {
            restoreKeys(mGlobalKeyMonitorManager.getBinder());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void restoreKeys(IBinder b) throws RemoteException {
        if(!isRestored ) {
            isRestored = true;
            if (b != null && mService != null) {
                try {
                    mService.restoreKeys(mBinder);
                    Log.i(TAG, "restoreKeys: callingPid ：" + Binder.getCallingPid());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }else{
            Log.i(TAG, "can't restore before prohibit!!");
        }
    }

    public IBinder getBinder(){
        if(mBinder != null){
            return mBinder;
        }
        return null;
    }


    private class MyBinder extends Binder{
        public MyBinder(){
        }
    }

    public synchronized void bind(){
        Intent intent=new Intent();
        intent.setClass(mContext.getApplicationContext(),GlobalKeyMonitorService.class);
        mContext.bindService(intent,mConnection,Context.BIND_AUTO_CREATE);
    }

    public synchronized void unBind(){
        if(mService != null) {
            mContext.unbindService(mConnection);
        }
    }

    private ServiceConnection mConnection=new ServiceConnection(){
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mService=IGlobalKeyMonitor.Stub.asInterface(service);
            Log.v(TAG,"onServiceConnected");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.v(TAG,"onServiceDisconnected");
        }
    };
}
