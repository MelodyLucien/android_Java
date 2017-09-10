package com.archermind.aidl;

import android.os.IBinder;
import com.archermind.aidl.ITaskCallBack;
import com.archermind.aidl.Person;
interface ITaskBinder {

   void fuc01();
   void fuc02();
   String fuc03(in Person person);
   void registerCallBack(IBinder b,ITaskCallBack cb);
   void unregisterCallBack(IBinder b);
}