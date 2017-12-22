package com.example.zhouhao2.androidtvgiem;

import android.content.Context;
import android.os.KeysProhibitorManager;
import android.util.Log;

import java.lang.ref.WeakReference;

public class NewInvokerExample {
    private static final String TAG = NewInvokerExample.class.getSimpleName();

    private KeysProhibitorManager mKeysProhibitorManager = KeysProhibitorManager.getInstance();

    private static final boolean DEBUG = true;

    private int[] testKeys= new int[]{4, 19, 21, 22, 24, 25};

    private WeakReference<Context> mContextRef;
    private Context getContext() {
        if (mContextRef != null) {
            return mContextRef.get();
        }
        return null;
    }


    public static class SingletonHolder {
        public static NewInvokerExample sInstance = new NewInvokerExample();
    }

    private NewInvokerExample(){
        //doNothing
    }


    public static NewInvokerExample getInstance(Context context) {
        Log.i(TAG,"NewInvokerExample getInstance");
        if (context != null
                && (NewInvokerExample.SingletonHolder.sInstance.mContextRef == null || NewInvokerExample.SingletonHolder.sInstance.mContextRef.get() == null)) {
            NewInvokerExample.SingletonHolder.sInstance.mContextRef = new WeakReference<Context>(context.getApplicationContext());
        }
        return NewInvokerExample.SingletonHolder.sInstance;
    }

    // stop monitoring input event
    public  void startProhibit() {
        if (DEBUG) {
            Log.d(TAG, "startProhibit");
        }
        if(mKeysProhibitorManager.isServiceAvaiable()){
            mKeysProhibitorManager.prohibitKeys(testKeys);
        }
    }
    // stop monitoring input event
    public  void startRestore() {
        if (DEBUG) {
            Log.d(TAG, "startRestore");
        }
        if(mKeysProhibitorManager.isServiceAvaiable()) {
            mKeysProhibitorManager.restoreKeys();
        }
    }
    // stop monitoring input event
    public  void startRelease() {
        if (DEBUG) {
            Log.d(TAG, "startRelease");
        }
        if(mKeysProhibitorManager.isServiceAvaiable()) {
            mKeysProhibitorManager.releaseKeysOnly(testKeys);
        }
    }
}