package com.archermind.aidl;

import android.os.Binder;
import com.archermind.aidl.GlobalInputEventMonitorRequest;
interface IGlobalKeyMonitor {
   void processMonitorRequest(String token,in GlobalInputEventMonitorRequest request);
   void processMonitorCancel(String token);
   void processKeysByFlag(IBinder b,int action,in int[] keys);
   void restoreKeys(IBinder b);
}