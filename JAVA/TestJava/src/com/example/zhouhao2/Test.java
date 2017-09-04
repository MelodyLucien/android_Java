package com.example.zhouhao2;

import java.awt.event.WindowStateListener;
import java.beans.FeatureDescriptor;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.example.test.WindowState;

public class Test {
	
	WindowState wm = new WindowState();
	
	public static final String DOG_MOUTH_DIR="/home/zhouhao2/test/";
	public static final String DOG_MOUTH_PATH="/home/zhouhao2/test/dog.txt";
	
	public static void main(String[] args) {
		feedDog();
	}
	
	private static void feedDog(){
           write2File();
	}

	private static void write2File() {
		try {
			 File f=new File(DOG_MOUTH_PATH);
			while(true){
				if(f.exists()){
					f.delete();
					System.out.println("delete f");
				}else if(!f.exists()){
					f.createNewFile();
					System.out.println("create f");
				}

				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
