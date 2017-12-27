package android.os;

import android.util.Log;
import android.os.ServiceManager;

public class KeysVirusVerifierManager{
    private static final String TAG = KeysVirusVerifierManager.class.getSimpleName();
    private static final String VIRUS_SERVICE_NAME = "android.os.IVirusVerifier";
    private static KeysVirusVerifierManager mKeysVirusVerifierManager = null;

    private Binder mBinder = null;
    private static IVirusVerifier  mIVirusVerifierProxy = null;
    private boolean isRestored = false;
    private boolean mIsServiceAvaliable = false;

    private KeysVirusVerifierManager() {
        mBinder = new Binder();
        mIsServiceAvaliable=isVirusServiceAvaliable();
        isRestored = true;
    }

    public static synchronized KeysVirusVerifierManager getInstance(){
        if(mKeysVirusVerifierManager == null){
            mKeysVirusVerifierManager = new KeysVirusVerifierManager();
            return mKeysVirusVerifierManager;
        }
        return mKeysVirusVerifierManager;
    }

    private static IVirusVerifier getVirusVerifierService(){
        Log.d(TAG, "getKeysProhibitorService() called with: name = [" + VIRUS_SERVICE_NAME + "]");
        IBinder mBinder=null;
        mBinder= (IBinder)ServiceManager.getService(VIRUS_SERVICE_NAME);
        return IVirusVerifier.Stub.asInterface(mBinder);
    }


    public synchronized void registerCallBack(IVerifyObserver cb) {
        if(isServiceAvaiable()) {
            if ((mIVirusVerifierProxy != null) && (mBinder != null)) {
                try {
                    mIVirusVerifierProxy.registerCallBack(mBinder,cb);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public boolean isServiceAvaiable(){
        return mIsServiceAvaliable;
    }

    private  boolean isVirusServiceAvaliable(){
        mIVirusVerifierProxy = getVirusVerifierService();
        if(mIVirusVerifierProxy != null){
            Log.i(TAG, "isVirusServiceAvaliable: true");
            return true;
        }else{
            Log.i(TAG, "isVirusServiceAvaliable: false");
            return false;
        }
    }
}
