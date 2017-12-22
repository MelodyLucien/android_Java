package android.os;

import android.util.Log;

/**
 * Created by zhouhao2 on 17-11-22.
 */
public class KeysProhibitorManager implements IKeysProhibitor {
    private static final String TAG = KeysProhibitorManager.class.getSimpleName();
    private static final String GIEM_SERVICE_NAME = "android.os.IKeysProhibitor";
    private static KeysProhibitorManager mKeysProhibitorManager = null;

    private Binder mBinder = null;
    private static IKeysProhibitor  mIKeysProhibitorProxy = null;
    private boolean isRestored = false;
    private boolean mIsServiceAvaliable = false;

    //process flag
    private static int FLAG_PROHIBIT = 0;
    private static int FLAG_RELEASE_ONLY = 1;

    private KeysProhibitorManager() {
        mBinder = new MyBinder();
        mIsServiceAvaliable=isNewGiemAvaliable();
        isRestored = true;
    }

    public static synchronized KeysProhibitorManager getInstance(){
        if(mKeysProhibitorManager == null){
            mKeysProhibitorManager = new KeysProhibitorManager();
            return mKeysProhibitorManager;
        }
        return mKeysProhibitorManager;
    }

    private static IKeysProhibitor getKeysProhibitorService(){
        Log.d(TAG, "getKeysProhibitorService() called with: name = [" + GIEM_SERVICE_NAME + "]");
        IBinder mBinder=null;
        mBinder= (IBinder)ServiceManager.getService(GIEM_SERVICE_NAME);
        return Stub.asInterface(mBinder);
    }

    public synchronized void prohibitKeys(int[] keys){
        try {
            processKeysByFlag(mKeysProhibitorManager.getBinder(),FLAG_PROHIBIT,keys);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public synchronized void releaseKeysOnly(int[] keys){
        try {
            processKeysByFlag(mKeysProhibitorManager.getBinder(),FLAG_RELEASE_ONLY,keys);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void processKeysByFlag(IBinder mBinder, int action, int[] keys) throws RemoteException {
        if(isRestored ) {
            isRestored = false;
            if (keys != null && mIKeysProhibitorProxy != null) {
                try {
                    mIKeysProhibitorProxy.processKeysByFlag(mBinder,action, keys);
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
            restoreKeys(mKeysProhibitorManager.getBinder());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void restoreKeys(IBinder b) throws RemoteException {
        if(!isRestored ) {
            isRestored = true;
            if (b != null && mIKeysProhibitorProxy != null) {
                try {
                    mIKeysProhibitorProxy.restoreKeys(mBinder);
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

    public boolean isServiceAvaiable(){
        return mIsServiceAvaliable;
    }

    private  boolean isNewGiemAvaliable(){
        mIKeysProhibitorProxy = getKeysProhibitorService();
        if(mIKeysProhibitorProxy != null){
            Log.i(TAG, "isNewGiemAvaliable: true");
            return true;
        }else{
            Log.i(TAG, "isNewGiemAvaliable: false");
            return false;
        }
    }
}
