/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: /home/zhouhao2/myGit/android_Java/Android/AidlDemoASVirus/app/src/main/aidl/android/os/IVirusVerifier.aidl
 */
package android.os;
public interface IVirusVerifier extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements android.os.IVirusVerifier
{
private static final java.lang.String DESCRIPTOR = "android.os.IVirusVerifier";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an android.os.IVirusVerifier interface,
 * generating a proxy if needed.
 */
public static android.os.IVirusVerifier asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof android.os.IVirusVerifier))) {
return ((android.os.IVirusVerifier)iin);
}
return new android.os.IVirusVerifier.Stub.Proxy(obj);
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
android.os.IVerifyObserver _arg1;
_arg1 = android.os.IVerifyObserver.Stub.asInterface(data.readStrongBinder());
this.registerCallBack(_arg0, _arg1);
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements android.os.IVirusVerifier
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
@Override public void registerCallBack(android.os.IBinder binder, android.os.IVerifyObserver cb) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder(binder);
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
public void registerCallBack(android.os.IBinder binder, android.os.IVerifyObserver cb) throws android.os.RemoteException;
}
