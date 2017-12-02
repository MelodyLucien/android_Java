package com.example.zhouhao2.testaidl2;

import android.os.Binder;
interface IKeyBinder {
   void registerCallBack(IBinder b,in int[] keys);
   void unregisterCallBack( IBinder b);
}