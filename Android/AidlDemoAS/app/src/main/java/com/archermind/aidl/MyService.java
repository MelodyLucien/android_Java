package com.archermind.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.util.ArrayMap;
import android.util.Log;


public class MyService extends Service {
    public final String TAG="AidlDemo.MyService";
	private final ArrayMap<IBinder,ITaskCallBack> mBinderTasks = new ArrayMap<IBinder,ITaskCallBack>();
    private MyHandler mH = null;

    @Override
	public void onCreate(){
		Log.v(TAG,"onCreate()...");
        HandlerThread serviceThread = new HandlerThread(TAG,Process.THREAD_PRIORITY_BACKGROUND);
        serviceThread.start();
        mH = new MyHandler(serviceThread.getLooper());
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
    
	private final ITaskBinder.Stub mBinder=new ITaskBinder.Stub() {
		
		@Override
		public void unregisterCallBack(IBinder b) throws RemoteException {
            b.unlinkToDeath(deathRecepient,0);
			Log.v(TAG,"unregisterCallBack...");
            mBinderTasks.remove(b);
		}
		
		@Override
		public void registerCallBack(IBinder b, ITaskCallBack cb) throws RemoteException {
			b.linkToDeath(deathRecepient,0);
			Log.v(TAG,"registerCallBack...");
            Log.i(TAG, "registerCallBack: "+b.toString());
            mBinderTasks.put(b,cb);
		}
		
		@Override
		public void fuc01() throws RemoteException {
			Log.v(TAG,"fuc01...");
		}

		@Override
		public void fuc02() throws RemoteException {
			Log.v(TAG,"fuc02...");
		}

		@Override
		public String fuc03(Person person) throws RemoteException {
			Log.i(TAG, "fuc03: ");
			String name=person.getName();
			String descrip=person.getDescrip();
			int sex=person.getSex();
			String ret="";
			if(sex==0){
				ret="Hello "+name+",you are handsome"+"("+descrip+")";
			}else{
				ret="Hello "+name+",you are beautiful"+"("+descrip+")";
			}
			return ret;
		}
	};


	private IBinder.DeathRecipient deathRecepient = new IBinder.DeathRecipient(){

        @Override
        public void binderDied() {
            Log.i(TAG, "binderDied: i am dying!!!");
        }
    };

    class MyHandler extends  Handler{

        public MyHandler(Looper looper){
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {


        }
    };

}
