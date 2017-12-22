// IKeysProhibitor.aidl
package android.os;
import android.os.Binder;
interface IKeysProhibitor {
   void processKeysByFlag(IBinder b,int action,in int[] keys);
   void restoreKeys(IBinder b);
}
