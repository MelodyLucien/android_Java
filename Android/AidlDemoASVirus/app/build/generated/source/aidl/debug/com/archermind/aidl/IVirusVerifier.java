/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: /home/zhouhao2/myGit/android_Java/Android/AidlDemoASVirus/app/src/main/aidl/com/archermind/aidl/IVirusVerifier.aidl
 */
package com.archermind.aidl;
public interface IVirusVerifier extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.archermind.aidl.IVirusVerifier
{
private static final java.lang.String DESCRIPTOR = "com.archermind.aidl.IVirusVerifier";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.archermind.aidl.IVirusVerifier interface,
 * generating a proxy if needed.
 */
public static com.archermind.aidl.IVirusVerifier asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.archermind.aidl.IVirusVerifier))) {
return ((com.archermind.aidl.IVirusVerifier)iin);
}
return new com.archermind.aidl.IVirusVerifier.Stub.Proxy(obj);
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
com.archermind.aidl.VerifyObserver _arg0;
_arg0 = com.archermind.aidl.VerifyObserver.Stub.asInterface(data.readStrongBinder());
this.registerCallBack(_arg0);
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.archermind.aidl.IVirusVerifier
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
@Override public void registerCallBack(com.archermind.aidl.VerifyObserver cb) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((cb!=null))?(cb.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_registerCallBack, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_registerCallBack = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
}
public void registerCallBack(com.archermind.aidl.VerifyObserver cb) throws android.os.RemoteException;
}
