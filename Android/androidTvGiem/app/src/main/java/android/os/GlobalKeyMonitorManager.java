package android.os;

import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by zhouhao2 on 17-11-22.
 */
public class GlobalKeyMonitorManager implements GlobalInputEventMonitor {
    private static final String TAG = GlobalKeyMonitorManager.class.getSimpleName();
    private static final String GIEM_SERVICE_NAME = "GlobalInputEventMonitor";
    private static GlobalKeyMonitorManager mGlobalKeyMonitorManager = null;

    private Binder mBinder = null;
    private GlobalInputEventMonitor  mService = null;
    private boolean isRestored = false;

    //process flag
    private static int FLAG_PROHIBIT = 0;
    private static int FLAG_RELEASE_ONLY = 1;

    private static ConnectionListenner mConnectListenner = null;

    private GlobalKeyMonitorManager() {
        mBinder = new MyBinder();
        bind();
        isRestored = true;
    }

    public static synchronized GlobalKeyMonitorManager getInstance(ConnectionListenner listenner){
        if(mGlobalKeyMonitorManager == null){
            mConnectListenner = listenner;
            mGlobalKeyMonitorManager = new GlobalKeyMonitorManager();
            return mGlobalKeyMonitorManager;
        }
        return mGlobalKeyMonitorManager;
    }

    private static GlobalInputEventMonitor getGIEMServiceManager(){
        Log.d(TAG, "getGIEMServiceManager() called with: name = [" + GIEM_SERVICE_NAME + "]");
        Log.i(TAG, "Build.VERSION.SDK_INT :"+Build.VERSION.SDK_INT);

        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.N){
           return null;
        }
        IBinder mBinder=null;
        try {
            Class<?> serviceManager = Class.forName("android.os.ServiceManager");
            Method method=serviceManager.getDeclaredMethod("getService",String.class);
            mBinder= (IBinder) method.invoke(serviceManager,GIEM_SERVICE_NAME);
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return GlobalInputEventMonitor.Stub.asInterface(mBinder);
    }

    @Override
    public synchronized void processMonitorRequest(String token, GlobalInputEventMonitorRequest request) throws RemoteException {
        if(mService != null){
            mService.processMonitorRequest(token,request);
        }
    }

    @Override
    public synchronized void processMonitorCancel(String token) throws RemoteException {
        if(mService != null){
            mService.processMonitorCancel(token);
        }
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
    public synchronized void processKeysByFlag(IBinder mBinder, int action, int[] keys) throws RemoteException {
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

    @Override
    public IBinder asBinder() {
        return null;
    }

    private class MyBinder extends Binder {
        public MyBinder(){
        }
    }

    private synchronized boolean bind(){
        mService = getGIEMServiceManager();
        if(mConnectListenner == null){
            try {
                throw new Exception("not define Connecttionlistenner");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(mService != null){
            mConnectListenner.onConnectedOK();
            Log.i(TAG, "bind: connect successful");
            return true;
        }else{
            mConnectListenner.onConnectedFail();
            Log.i(TAG, "bind: connect fail");
            return false;
        }
    }

    public interface ConnectionListenner{
        void onConnectedOK();
        void onConnectedFail();
    }
}
