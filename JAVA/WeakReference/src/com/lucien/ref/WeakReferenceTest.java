package com.lucien.ref;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.LinkedList;

/**
 * new skill 
 * jps:like ps in unbuntu 
 * 
 * jstat:see state of process
 * 
	-class
	-compiler
	-gc
	-gccapacity
	-gccause
	-gcmetacapacity
	-gcnew
	-gcnewcapacity
	-gcold
	-gcoldcapacity
	-gcutil
	-printcompilation

 */

/**
 * in java there are four types of references
 * 
 * 1.Strong reference
 * 2.weak reference
 * 3.soft reference
 * 4.PhantomReference
 * 5.AtomicReference
 * 
 * @author lucien
 *
 */
public class WeakReferenceTest {

	public static void main(String[] args) {
		 //testWeak();
		 testStrong();
		 //testWeakOrStrong();
		 //testSoft();
	}
	
	public static void testSoft(){
		/**
		 * soft reference和weak reference一样, 但被GC回收的时候需要多一个条件: 
		 * 当系统内存不足时(GC是如何判定系统内存不足? 是否有参数可以配置这个threshold?), 
		 * soft reference指向的object才会被回收. 正因为有这个特性, 
		 * soft reference比weak reference更加适合做cache objects的reference. 
		 * 因为它可以尽可能的retain cached objects, 减少重建他们所需的时间和消耗.
		 */
        long beginTime = System.nanoTime();
        LinkedList<SoftReference<byte[]>> list = new LinkedList<>();
        for (int i = 0; i < 1024; i++) {
            list.add(new SoftReference<>(new byte[1024 * 1024]));
        }
        long endTime = System.nanoTime();
        System.out.println(endTime - beginTime);
	}
	
	
	public static void testStrong(){
	        LinkedList<byte[]> list = new LinkedList<>();
	        for(int i=0; i<1024; i++){
	            list.add(new byte[1024*1024]);
	        }
	}
	
	public static void testWeak(){
		/**
		 * object will be collect when 
		 *  1)没有任何引用指向它 2)GC被运行
		 *  via `java -verbose:gc ` : we can see the process of collecting
		 *  
		 *  if you see:
		 *      Object obj=new Object();
		 *  that means a object is referred by obj ,so the object is pointed by obj 
		 *  
		 *  
		 *  WeakReference的一个特点是它何时被回收是不可确定的, 
		 *  因为这是由GC运行的不确定性所确定的. 
		 *  所以, 一般用weak reference引用的对象是有价值被cache, 而且很容易被重新被构建, 
		 *  且很消耗内存的对象
		 */
        long beginTime = System.nanoTime();
        LinkedList<WeakReference<byte[]>> list = new LinkedList<>();
        for (int i = 0; i < 1024; i++) {
            list.add(new WeakReference<>(new byte[1024 * 1024]));
        }
        long endTime = System.nanoTime();
        System.out.println(endTime - beginTime);
	}

	
	public static void testWeakOrStrong(){
		
		Car car = new Car(22000,"silver");
		WeakReference<Car> weakCar = new WeakReference<Car>(car);
		
		int i=0;
		
		while(true){
			//comment out this line to see the output,when use weakRef,GC will collect car object when no longer use it 
		    //System.out.println("here is the strong reference 'car' "+car);
			if(weakCar.get()!=null){
				i++;
				System.out.println("Object is alive for "+i+" loops - "+weakCar);
			}else{
				System.out.println("Object has been collected.");
				break;
			}
		}
	}
}
