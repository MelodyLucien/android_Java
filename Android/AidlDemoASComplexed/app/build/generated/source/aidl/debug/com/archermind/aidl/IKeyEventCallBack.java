/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: /home/zhouhao2/myGit/android_Java/Android/AidlDemoASComplexed/app/src/main/aidl/com/archermind/aidl/IKeyEventCallBack.aidl
 */
package com.archermind.aidl;
public interface IKeyEventCallBack extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.archermind.aidl.IKeyEventCallBack
{
private static final java.lang.String DESCRIPTOR = "com.archermind.aidl.IKeyEventCallBack";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.archermind.aidl.IKeyEventCallBack interface,
 * generating a proxy if needed.
 */
public static com.archermind.aidl.IKeyEventCallBack asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.archermind.aidl.IKeyEventCallBack))) {
return ((com.archermind.aidl.IKeyEventCallBack)iin);
}
return new com.archermind.aidl.IKeyEventCallBack.Stub.Proxy(obj);
}
@Override public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_onKeyEventCallBack:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
boolean _arg1;
_arg1 = (0!=data.readInt());
int _arg2;
_arg2 = data.readInt();
int _arg3;
_arg3 = data.readInt();
long _arg4;
_arg4 = data.readLong();
long _arg5;
_arg5 = data.readLong();
int _result = this.onKeyEventCallBack(_arg0, _arg1, _arg2, _arg3, _arg4, _arg5);
reply.writeNoException();
reply.writeInt(_result);
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.archermind.aidl.IKeyEventCallBack
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
@Override public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
@Override public int onKeyEventCallBack(int keycode, boolean isDown, int repeatCount, int scancode, long downTime, long eventTime) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(keycode);
_data.writeInt(((isDown)?(1):(0)));
_data.writeInt(repeatCount);
_data.writeInt(scancode);
_data.writeLong(downTime);
_data.writeLong(eventTime);
mRemote.transact(Stub.TRANSACTION_onKeyEventCallBack, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
}
static final int TRANSACTION_onKeyEventCallBack = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
}
public int onKeyEventCallBack(int keycode, boolean isDown, int repeatCount, int scancode, long downTime, long eventTime) throws android.os.RemoteException;
}
