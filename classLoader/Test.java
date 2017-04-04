package com.zhouhao2.strings;

import java.net.URL;
import java.net.URLClassLoader;

public class Test {
	
	public static void main(String[] args) {
		
		System.out.println("BootstrapClassLoader path: -------------------------------------- ");
		
		
		System.out.println("appClassLoader path: -------------------------------------- ");
		System.out.println(ClassLoader.getSystemClassLoader().getClass().getSimpleName());
		printPathes((URLClassLoader)ClassLoader.getSystemClassLoader());
		
		
		System.out.println("extClassLoader path:--------------------------------------  ");
		System.out.println(ClassLoader.getSystemClassLoader().getParent().getClass().getSimpleName());
		printPathes((URLClassLoader)ClassLoader.getSystemClassLoader().getParent());

	}
	
	public static void printPathes(URLClassLoader loader){
		URL[]  urls= loader.getURLs();
		for (int i = 0; i < urls.length; i++) {
			System.out.println(urls[i]);
		}
		
		System.out.println();
		System.out.println();
	}
	
}
