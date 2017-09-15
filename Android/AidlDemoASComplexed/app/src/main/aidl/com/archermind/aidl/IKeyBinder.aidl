package com.archermind.aidl;

import android.os.Binder;
import com.archermind.aidl.IKeyEventCallBack;
interface IKeyBinder {
   void registerCallBack(IBinder b,IKeyEventCallBack cb);
   void unregisterCallBack(IBinder b);
}