/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: frameworks/base/core/java/android/os/GlobalInputEventMonitor.aidl
 */
package android.os;
public interface GlobalInputEventMonitor extends IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends Binder implements GlobalInputEventMonitor
{
private static final String DESCRIPTOR = "GlobalInputEventMonitor";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an android.os.GlobalInputEventMonitor interface,
 * generating a proxy if needed.
 */
public static GlobalInputEventMonitor asInterface(IBinder obj)
{
if ((obj==null)) {
return null;
}
IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof GlobalInputEventMonitor))) {
return ((GlobalInputEventMonitor)iin);
}
return new Proxy(obj);
}
@Override public IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_processMonitorRequest:
{
data.enforceInterface(DESCRIPTOR);
String token = data.readString();
GlobalInputEventMonitorRequest request = GlobalInputEventMonitorRequest.CREATOR
      .createFromParcel(data);
this.processMonitorRequest(token, request);
reply.writeNoException();
return true;
}
case TRANSACTION_processMonitorCancel:
{
data.enforceInterface(DESCRIPTOR);
String token = data.readString();
this.processMonitorCancel(token);
reply.writeNoException();
return true;
}
case TRANSACTION_processKeysByFlag:
{
data.enforceInterface(DESCRIPTOR);
IBinder _arg0;
_arg0 = data.readStrongBinder();
int _arg1;
_arg1 = data.readInt();
int[] _arg2;
_arg2 = data.createIntArray();
this.processKeysByFlag(_arg0, _arg1, _arg2);
reply.writeNoException();
return true;
}
case TRANSACTION_restoreKeys:
{
data.enforceInterface(DESCRIPTOR);
IBinder _arg0;
_arg0 = data.readStrongBinder();
this.restoreKeys(_arg0);
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements GlobalInputEventMonitor
{
private IBinder mRemote;
Proxy(IBinder remote)
{
mRemote = remote;
}
@Override public IBinder asBinder()
{
return mRemote;
}
public String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
@Override public void processMonitorRequest(String token, GlobalInputEventMonitorRequest request) throws RemoteException
{
Parcel _data = Parcel.obtain();
Parcel _reply = Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(token);
if ((request!=null)) {
request.writeToParcel(_data, 0);
}
mRemote.transact(Stub.TRANSACTION_processMonitorRequest, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void processMonitorCancel(String token) throws RemoteException
{
Parcel _data = Parcel.obtain();
Parcel _reply = Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(token);
mRemote.transact(Stub.TRANSACTION_processMonitorCancel, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void processKeysByFlag(IBinder b, int action, int[] keys) throws RemoteException
{
Parcel _data = Parcel.obtain();
Parcel _reply = Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder(b);
_data.writeInt(action);
_data.writeIntArray(keys);
mRemote.transact(Stub.TRANSACTION_processKeysByFlag, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void restoreKeys(IBinder b) throws RemoteException
{
Parcel _data = Parcel.obtain();
Parcel _reply = Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder(b);
mRemote.transact(Stub.TRANSACTION_restoreKeys, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_processMonitorRequest = (IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_processMonitorCancel = (IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_processKeysByFlag = (IBinder.FIRST_CALL_TRANSACTION + 2);
static final int TRANSACTION_restoreKeys = (IBinder.FIRST_CALL_TRANSACTION + 3);
}
public void processMonitorRequest(String token, GlobalInputEventMonitorRequest request) throws RemoteException;
public void processMonitorCancel(String token) throws RemoteException;
public void processKeysByFlag(IBinder b, int action, int[] keys) throws RemoteException;
public void restoreKeys(IBinder b) throws RemoteException;
}
