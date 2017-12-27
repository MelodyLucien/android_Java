package android.os;
import android.os.IVerifyObserver;
import android.os.IBinder;
interface IVirusVerifier {
   void registerCallBack(IBinder binder,IVerifyObserver cb);
}