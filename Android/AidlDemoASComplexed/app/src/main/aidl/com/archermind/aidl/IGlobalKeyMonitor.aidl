package com.archermind.aidl;

import android.os.Binder;
interface IGlobalKeyMonitor {
   void processKeysByFlag(IBinder b,int action,in int[] keys);
   void restoreKeys(IBinder b);
}