package com.archermind.aidl;
import com.archermind.aidl.VerifyObserver;
interface IVirusVerifier {
   void registerCallBack(VerifyObserver cb);
}