package com.archermind.aidl;

interface IKeyEventCallBack {
    int onKeyEventCallBack(int keycode,boolean isDown,int repeatCount,int scancode,long downTime,long eventTime);
}