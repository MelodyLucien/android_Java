package com.example.zhouhao2.androidtvgiem;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Parcel;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;

import com.jamdeo.system_server.library.GlobalInputEventMonitorRequest;
import com.jamdeo.system_server.library.GlobalInputEventMonitorRequest.*;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * Created by Brljefso on 2017-4-1 0001.
 */

public class AIKeyEventSource {
    private static final String TAG = AIKeyEventSource.class.getSimpleName();

    //GlobalInputEventMonitoringService
    private static final String GIEM_SERVICE_NAME = "GlobalInputEventMonitor";
    private static final String GIEM_DESCRIPTOR = "GlobalInputEventMonitor";
    private static final int TRANSACTION_START_MONITOR = IBinder.FIRST_CALL_TRANSACTION + 0;
    private static final int TRANSACTION_CANCEL_MONITOR = IBinder.FIRST_CALL_TRANSACTION + 1;

    //Keys (broadcasts) Actions related
    private static final String MONITOR_TOKEN = "com.hisense.tv.aivirtualassistant.key_monitor";
    private static final String INPUT_EVENT_ACTION = MONITOR_TOKEN;
    private static final String EXTRAS_KEY = "keycode";

    private static final boolean DEBUG = true;


    private MessageHandler mHandler = null;
    private class MessageHandler extends Handler {

        public static  final int ACTION_KEY_EVENT = 1;

        public MessageHandler(Looper looper){
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            Log.d(TAG, "handleMessage: ");
            switch (msg.what){
                case ACTION_KEY_EVENT:
                    Intent intent =(Intent)msg.obj;
                    Log.i(TAG,intent.getAction().toString());
                    Log.i(TAG,"keycode= "+(int)intent.getIntExtra(EXTRAS_KEY,-1));
                    break;
                default:
                    break;
            }
        }
    }

    private final BroadcastReceiver mKeyReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(final Context context, Intent intent) {
            if (context == null || intent == null) return;
            String action = intent.getAction();
            if (TextUtils.isEmpty(action)) return;

            Log.i(TAG, "BroadcastReceiver(), Intent received with action = " + action);
            switch (action) {
                case INPUT_EVENT_ACTION: {
                    // account changed
                    if (mHandler != null) {
                        mHandler.obtainMessage(MessageHandler.ACTION_KEY_EVENT,intent).sendToTarget();
                    }
                    break;
                }

                default: {
                    Log.w(TAG, "handleMessage received unexpected msg = " + action);
                    break;
                }
            }
        }
    };

    private WeakReference<Context> mContextRef;
    private Context getContext() {
        if (mContextRef != null) {
            return mContextRef.get();
        }
        return null;
    }

    private void init() {
        Log.i(TAG,"key event service init()");
        final HandlerThread thread = new HandlerThread(TAG);
        thread.start();
        mHandler = new MessageHandler(thread.getLooper());

        registerBroadcastReceivers();

        initKeysList();

        Log.i(TAG, "init: thread GIEM TEST start()");
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    startMonitoringKeyEvent();
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    stopMonitoringInputEvent();
                }
            }
        }).start();

        //startMonitoringKeyEvent();
    }

    private void registerBroadcastReceivers() {
        Log.d(TAG, "registerBroadcastReceivers() called");
        IntentFilter filter = new IntentFilter();
        filter.addAction(INPUT_EVENT_ACTION);
        getContext().registerReceiver(mKeyReceiver, filter);
    }

    private void unRegisterBroadcastReceivers(){
        getContext().unregisterReceiver(mKeyReceiver);
    }

    public static class SingletonHolder {
        public static AIKeyEventSource sInstance = new AIKeyEventSource();
    }

    private AIKeyEventSource(){
        //doNothing
    }


    public static AIKeyEventSource getInstance(Context context) {
        Log.i(TAG,"AIKeyEventSource getInstance");
        if (context != null
                && (AIKeyEventSource.SingletonHolder.sInstance.mContextRef == null || AIKeyEventSource.SingletonHolder.sInstance.mContextRef.get() == null)) {
            AIKeyEventSource.SingletonHolder.sInstance.mContextRef = new WeakReference<Context>(context.getApplicationContext());
            //start Monitoring keys
            AIKeyEventSource.SingletonHolder.sInstance.init();
        }
        return AIKeyEventSource.SingletonHolder.sInstance;
    }


    //keys for monitoring
    public static ArrayList<Integer> mKeys= new ArrayList<>();
    public static int KEYCODE_VOICE = 300;
    public static int KEYCODE_IR_VOICE = 4355;

    public static int KEYCODE_SILO_A = KeyEvent.KEYCODE_MUSIC;
    public static int KEYCODE_SILO_B = KeyEvent.KEYCODE_F2;
    public static int KEYCODE_SILO_C = KeyEvent.KEYCODE_TV;
    public static int KEYCODE_SILO_D = KeyEvent.KEYCODE_F1;

    public static int KEYCODE_TOUCH_UI = KeyEvent.KEYCODE_F4;
    public static int KEYCODE_FAVOURITE = 4316;
    public static int KEYCODE_TOUCH_RING = 4317;

    private void initKeysList(){
        Log.d(TAG, "initKeysList() called");
/*        mKeys.add(KeyEvent.KEYCODE_POWER);

        //silos and touch_ui
        mKeys.add(KEYCODE_SILO_A);
        mKeys.add(KEYCODE_SILO_B);
        mKeys.add(KEYCODE_SILO_C);
        mKeys.add(KEYCODE_SILO_D);
        mKeys.add(KEYCODE_TOUCH_UI);

        //Directions
        mKeys.add(KeyEvent.KEYCODE_DPAD_CENTER);
        mKeys.add(KeyEvent.KEYCODE_DPAD_DOWN);
        mKeys.add(KeyEvent.KEYCODE_DPAD_LEFT);
        mKeys.add(KeyEvent.KEYCODE_DPAD_RIGHT);

        mKeys.add(KeyEvent.KEYCODE_VOLUME_UP);
        mKeys.add(KeyEvent.KEYCODE_VOLUME_DOWN);
        mKeys.add(KeyEvent.KEYCODE_VOLUME_MUTE);

        mKeys.add(KeyEvent.KEYCODE_BACK);
        mKeys.add(KeyEvent.KEYCODE_F12);//HISENSE HOME*/

        mKeys.add(KeyEvent.KEYCODE_MENU);

/*      mKeys.add(KEYCODE_VOICE);
        mKeys.add(KEYCODE_IR_VOICE);
        mKeys.add(KeyEvent.KEYCODE_VOICE_ASSIST);

        mKeys.add(KeyEvent.KEYCODE_CHANNEL_UP);
        mKeys.add(KeyEvent.KEYCODE_CHANNEL_DOWN);

        mKeys.add(KEYCODE_FAVOURITE);
        mKeys.add(KEYCODE_TOUCH_RING);*/

    }

    //
    public static void startMonitoringKeyEvent() {
        if (DEBUG) {
            Log.d(TAG, "startMonitoringKeyEvent");
        }

        //monitor keys that we want
        int keysLength=mKeys.size();
        for (int i = 0;i< keysLength;i++){
            int keycode=mKeys.get(i);
            GlobalInputEventMonitorRequest request = new GlobalInputEventMonitorRequest(
                    GlobalInputEventMonitorRequest.RequestType.MONITOR_EVENT);
            RequestItem[] items = new RequestItem[1];
            final Intent intent = new Intent();
            intent.setAction(INPUT_EVENT_ACTION);
            intent.putExtra(EXTRAS_KEY, keycode);
            items[0] = new RequestItem(InputEventType.KEY_EVENT,keycode ,
                    Action.FILTER_KEEP_MONITOR, intent);
            request.setRequestItems(items);
            sendMonitorRequestToGIEMService(request, MONITOR_TOKEN+keycode);
        }
    }

    public static IBinder getGIEMServiceManager(String name){
        Log.d(TAG, "getGIEMServiceManager() called with: name = [" + name + "]");
        IBinder mBinder=null;
        try {
            Class<?> serviceManager = Class.forName("android.os.ServiceManager");
            Method method=serviceManager.getDeclaredMethod("getService",String.class);
            mBinder= (IBinder) method.invoke(serviceManager,name);
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return mBinder;
    }

    //Send request item with monitor token to GIEM service
    public static void sendMonitorRequestToGIEMService(GlobalInputEventMonitorRequest request, String monitorToken) {
        Log.d(TAG, "sendMonitorRequestToGIEMService() called with: request = [" + request + "], monitorToken = [" + monitorToken + "]");
        IBinder service =getGIEMServiceManager(GIEM_SERVICE_NAME);
        if (service == null) {
            Log.w(TAG, "global input event monitoring service is not available!!");
            return;
        }
        Parcel data = Parcel.obtain();
        data.writeInterfaceToken(GIEM_DESCRIPTOR);
        data.writeString(monitorToken);
        request.writeToParcel(data, 0);
        Parcel reply = Parcel.obtain();

        try {
            if (!service.transact(TRANSACTION_START_MONITOR, data, reply, 0)) {
                Log.w(TAG, "Binder transact on global input event monitoring service failed for: "
                        + TRANSACTION_START_MONITOR);
                return;
            }
        } catch (RemoteException e) {
            Log.e(TAG, "Exception occured on starting monitoring input event.", e);
            return;
        } finally {
            // recycle the parcels
            data.recycle();
            reply.recycle();
        }
    }
    // stop monitoring input event
    public static void stopMonitoringInputEvent() {
        if (DEBUG) {
            Log.d(TAG, "stopMonitoringInputEvent");
        }
        IBinder service = getGIEMServiceManager(GIEM_SERVICE_NAME);

        if (service == null) {
            Log.w(TAG, "global input event monitoring service is not available!!");
            return;
        }
        Parcel data = Parcel.obtain();
        data.writeString(MONITOR_TOKEN);
        Parcel reply = Parcel.obtain();
        data.writeInterfaceToken(GIEM_DESCRIPTOR);

        try {
            if (!service.transact(TRANSACTION_CANCEL_MONITOR, data, reply, 0)) {
                Log.w(TAG, "Binder transact on global input event monitoring service failed for: "
                        + TRANSACTION_CANCEL_MONITOR);
                return;
            }
        } catch (RemoteException e) {
            Log.e(TAG, "Exception occured on start monitoring input event.", e);
            return;
        } finally {
            // recycle the parcels
            data.recycle();
            reply.recycle();
        }
    }
}