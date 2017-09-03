package com.my.arrays;

import java.util.ArrayList;
import java.util.Collection;

import com.my.member.People;
import com.my.member.Shape;
import com.my.member.Square;
import com.my.member.Student;

/**
 * @author zhouhao
 *
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		/**
		 * 测试数组的协变性，即编译可以通过，但运行时会报异常
		 */
             
		People[] peoples=new Student[10];
		
		for (int i = 0; i < peoples.length; i++) {
			
			peoples[i]=new Student("name"+i, i);
			System.out.println(peoples[i]);
		}
		
	//	peoples[5]=new Teacher("fdsf", 34);
		/**
		 * Exception in thread "main" java.lang.ArrayStoreException: com.my.member.Teacher
	at com.my.arrays.Test.main(Test.java:26)
		 */
		
		
		/**
		 * 测试泛型集合的协变性
		 */
		ArrayList<Shape> shapes=new ArrayList<>();
		shapes.add(new Shape(51));
		shapes.add(new Shape(52));
		shapes.add(new Shape(53));
		shapes.add(new Shape(54));
		
		
		System.out.println(totalArea(shapes));
		
		//above is correct
		
		
		ArrayList<Square> squares=new ArrayList<>();
		squares.add(new Square(51));
		squares.add(new Square(52));
		squares.add(new Square(53));
		squares.add(new Square(54));
		
		//System.out.println(totalArea(squares));
		/**
		 * when use the first totalArea() line 62 can not be compiled
		 */
		
		System.out.println(totalArea(squares));
		/**
		 * when use the second totalArea() anything is well
		 * note: the two methods can not be used at the same time
		 * because they both have the same erased method
		 */
		
		Square[] squares2=new Square[3];
		squares2[0]=new Square(10, 20);
		squares2[1]=new Square(11, 20);
		squares2[1]=new Square(12, 20);
		
		
		System.out.println(contans(squares2, new Square(11,20)));
		
	}
	
	/**
	 * generic collection do not have  协变性，因此在编译的时候无法兼容
	 * @param shapes
	 * @return
	 */
//	public static double totalArea(Collection<Shape> shapes){
//		
//		double total=0;
//		for (Shape shape:shapes) {
//			total+=shape.area();
//		}
//		return total;
//	}
	
	/**
	 * we can make them have it by this method
	 * @param shapes
	 * @return
	 */
   public static double totalArea(Collection<? extends Shape> shapes){
		
		double total=0;
		for (Shape shape:shapes) {
			total+=shape.area();
		}
		return total;
	}
   
   
   /**
    * generic method
    * notes:before the return type we should declare the generic type AnyType
    * @param arrs
    * @param e
    * @return
    */
   public static <AnyType> boolean contans(AnyType[] arrs,AnyType e){
	   for (int i = 0; i < arrs.length; i++) {
		if(arrs[i].equals(e));
		return true;
	}
	   return false;
   }
   
   
   
   /**
    * the best generic method  类型限界
    * note: think  why we should do this way why not others???????  very important!!!!!
    * @param arr
    * @return
    */
   public static <AnyType extends Comparable<? super AnyType>> AnyType findMax(AnyType[]arr){
	   int maxIndex=0;
	   for (int i = 1; i < arr.length; i++) {
		if(arr[i].compareTo(arr[maxIndex])>0)
			maxIndex=i;
	}
	   return arr[maxIndex];
   }
}

