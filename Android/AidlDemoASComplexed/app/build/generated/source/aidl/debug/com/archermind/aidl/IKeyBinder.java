/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: /home/zhouhao2/myGit/android_Java/Android/AidlDemoASComplexed/app/src/main/aidl/com/archermind/aidl/IKeyBinder.aidl
 */
package com.archermind.aidl;
public interface IKeyBinder extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.archermind.aidl.IKeyBinder
{
private static final java.lang.String DESCRIPTOR = "com.archermind.aidl.IKeyBinder";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.archermind.aidl.IKeyBinder interface,
 * generating a proxy if needed.
 */
public static com.archermind.aidl.IKeyBinder asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.archermind.aidl.IKeyBinder))) {
return ((com.archermind.aidl.IKeyBinder)iin);
}
return new com.archermind.aidl.IKeyBinder.Stub.Proxy(obj);
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
case TRANSACTION_registerCallBack:
{
data.enforceInterface(DESCRIPTOR);
android.os.IBinder _arg0;
_arg0 = data.readStrongBinder();
com.archermind.aidl.IKeyEventCallBack _arg1;
_arg1 = com.archermind.aidl.IKeyEventCallBack.Stub.asInterface(data.readStrongBinder());
this.registerCallBack(_arg0, _arg1);
reply.writeNoException();
return true;
}
case TRANSACTION_unregisterCallBack:
{
data.enforceInterface(DESCRIPTOR);
android.os.IBinder _arg0;
_arg0 = data.readStrongBinder();
this.unregisterCallBack(_arg0);
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.archermind.aidl.IKeyBinder
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
@Override public void registerCallBack(android.os.IBinder b, com.archermind.aidl.IKeyEventCallBack cb) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder(b);
_data.writeStrongBinder((((cb!=null))?(cb.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_registerCallBack, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void unregisterCallBack(android.os.IBinder b) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder(b);
mRemote.transact(Stub.TRANSACTION_unregisterCallBack, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_registerCallBack = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_unregisterCallBack = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
}
public void registerCallBack(android.os.IBinder b, com.archermind.aidl.IKeyEventCallBack cb) throws android.os.RemoteException;
public void unregisterCallBack(android.os.IBinder b) throws android.os.RemoteException;
}
