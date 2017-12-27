package android.os;
import android.app.Service;
import android.content.Intent;
import android.util.Log;

public class VirusVerifierService extends Service {
    public static String TAG = VirusVerifierService.class.getSimpleName();
    HandlerThread handlerThread = new HandlerThread(TAG, Process.THREAD_PRIORITY_FOREGROUND);

    private static IVerifyObserver mIVerifyObserver = null;

    private Handler mH = null;
    public VirusVerifierService() {
        Log.v(TAG,"onCreate()...");
        handlerThread.start();
        mH = new Handler(handlerThread.getLooper()){
            @Override
            public void handleMessage(Message msg) {
                Log.i(TAG, "handleMessage: ");
                int i = 0;
                while(i<10){
                    if(mIVerifyObserver!=null){
                        try {
                            mIVerifyObserver.onVerify(" "+i);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                    i++;
                }
                this.sendEmptyMessageDelayed(0,1000);
            }
        };
        mH.sendEmptyMessage(0);
        Log.i(TAG, "sendEmptyMessage: ");
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    private synchronized  boolean setIVerifyObserver(IVerifyObserver verifyObserver){
        if(verifyObserver != null){
            Log.i(TAG, "setIVerifyObserver: "+verifyObserver);
        }else{
            Log.i(TAG, "setIVerifyObserver as null");
        }
        mIVerifyObserver = verifyObserver;
        return true;
    }

    IVirusVerifier.Stub mBinder = new IVirusVerifier.Stub() {
        @Override
        public void registerCallBack(IBinder binder,IVerifyObserver cb) throws RemoteException {
                binder.linkToDeath(new MyDeathRecipient(),0);
                setIVerifyObserver(cb);
                cb.onVerify("service invoke onVerify");
        }
    };

    class MyDeathRecipient implements IBinder.DeathRecipient{
        @Override
        public void binderDied() {
            Log.i(TAG, "binderDied: "+Binder.getCallingPid());
        }
    }

    public static synchronized IVerifyObserver getIVerifyObserver(){
        Log.i(TAG, "getIVerifyObserver: "+mIVerifyObserver);
        if(mIVerifyObserver != null){
            return mIVerifyObserver;
        }
        return null;
    }
}
