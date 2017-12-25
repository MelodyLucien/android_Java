/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: /home/zhouhao2/myGit/android_Java/Android/AidlDemoASVirus/app/src/main/aidl/com/archermind/aidl/VerifyObserver.aidl
 */
package com.archermind.aidl;
public interface VerifyObserver extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.archermind.aidl.VerifyObserver
{
private static final java.lang.String DESCRIPTOR = "com.archermind.aidl.VerifyObserver";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.archermind.aidl.VerifyObserver interface,
 * generating a proxy if needed.
 */
public static com.archermind.aidl.VerifyObserver asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.archermind.aidl.VerifyObserver))) {
return ((com.archermind.aidl.VerifyObserver)iin);
}
return new com.archermind.aidl.VerifyObserver.Stub.Proxy(obj);
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
case TRANSACTION_onVerify:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
boolean _result = this.onVerify(_arg0);
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.archermind.aidl.VerifyObserver
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
@Override public boolean onVerify(java.lang.String path) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(path);
mRemote.transact(Stub.TRANSACTION_onVerify, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
}
static final int TRANSACTION_onVerify = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
}
public boolean onVerify(java.lang.String path) throws android.os.RemoteException;
}
