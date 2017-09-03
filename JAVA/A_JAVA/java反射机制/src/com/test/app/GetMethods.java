package com.test.app;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class GetMethods {

    private int f1(Object p, int x) throws NullPointerException {
        if (p == null)
            throw new NullPointerException();
        System.out.println("fi is invoked!!!");
        return x;
    }
    
    private int f3(Object p, int x){
        System.out.println("f3 is invoked!!!");
        return x;
    }

    public static void main(String args[]) {
        Class cls = null;
        try {
            cls = Class.forName("com.test.app.GetMethods");
            // get all method in the class
            Method methlist[] = cls.getDeclaredMethods();
            for (int i = 0; i < methlist.length; i++) {
                Method m = methlist[i];
                System.out.println("name = " + m.getName());
                System.out.println("decl class = " + m.getDeclaringClass());
                Class pvec[] = m.getParameterTypes();
                for (int j = 0; j < pvec.length; j++)
                    System.out.println("param #" + j + " " + pvec[j]);
                Class evec[] = m.getExceptionTypes();
                for (int j = 0; j < evec.length; j++)
                    System.out.println("exc #" + j + " " + evec[j]);
                System.out.println("return type = " + m.getReturnType());
                System.out.println("-----");
            }
        } catch (Throwable e) {
            System.err.println(e);
        }

        // if there is a specific method in the class
        try {
            Method method=cls.getDeclaredMethod("f1",Object.class,int.class);
            
            GetMethods getMethods = new GetMethods();
            if(method!=null){
                System.out.println("ok!! it is exist!!!");
            }
            
            //invoke method
            Object o = new Object();
            int i =9;
            
            try {
                method.invoke(getMethods, o,i);
            } catch (IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            System.out.println("no such method!!!");
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        
       
        
    }
    
    private void f2(){
        System.out.println("f2 is invoked!!");
    }
}
