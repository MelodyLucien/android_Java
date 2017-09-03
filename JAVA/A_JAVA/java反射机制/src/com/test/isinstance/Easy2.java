package com.test.isinstance;

public class Easy2 extends Easy1 {
public static void main(String[] args) {
	Easy2 easy2=new Easy2();
	
	try {
		Class class1=Class.forName("com.test.isinstance.Easy1");
		
		if(easy2 instanceof Easy2){
			System.out.println("easy2 is instance of Easy2");
		}
		
		if (easy2 instanceof Easy1) {
			System.out.println("easy2 is instance of Easy1");
		}
		
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	}
	
}

}
