package com.example.zhouhao2;

import java.awt.event.WindowStateListener;
import java.beans.FeatureDescriptor;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import javax.swing.plaf.metal.MetalIconFactory.FolderIcon16;

import com.example.test.WindowState;

public class Test {
	
	WindowState wm = new WindowState();
	
	public static final String DOG_MOUTH_DIR="/home/zhouhao2/test/";
	public static final String DOG_MOUTH_PATH="/home/zhouhao2/test/dog.txt";
	
	public static void main(String[] args) {
		//feedDog();
		int[] keys = new int[8];
		for (int i = 0; i < keys.length; i++) {
			keys[i] = 0;
		}
		

		
		int[] newKeys = new int[8];
		for (int i = 0; i < newKeys.length; i++) {
			newKeys[i]=0;
		}

		
		for (int i = 0; i < keys.length; i++) {
			System.out.println(keys[i]);
		}
		
		System.out.println(Arrays.equals(keys, newKeys));
		
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
