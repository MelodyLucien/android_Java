package com.example;

public class MyClass {

    private static Object obj = new Object();
    private static boolean isBoolean = false;

    public static void main(String[] args) {

        System.out.println( -0X000000001);

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread1 start!!");
                synchronized (obj) {
                    try {
                        System.out.println("thread1 in sync !!");
                        obj.wait(18000);
                        System.out.println("hello woorld2");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

       new Thread(new Runnable() {
           @Override
           public void run() {
               try {
                   System.out.println("thread2 start!!");
                   synchronized (obj) {
                       System.out.println("thread2 in sync !!");
                       Thread.sleep(6000);
                       isBoolean = true;
                       obj.notify();
                   }
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
       }).start();
    }
}
