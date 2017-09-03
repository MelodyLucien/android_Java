package com.my.zip;

import java.io.File;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

/**
 * 将一个文件夹下的文件压缩到另外的一个文件夹下
 * @author zhouhao2
 *
 */
public class ZipSingleFile {

	public ZipSingleFile() {
		
		zipFile("files1\\alawaysreboot","files\\my.zip");
	}
	
	/**
	 * before you zip ensure your parentpath exists and you can acess them
	 * @param srcFile
	 * @param destFile
	 */
	public boolean zipFile(String srcFile,String destFile){
		
		boolean result=false;
		try {
			ZipFile zipFile = new ZipFile(destFile);
			File file=new File(srcFile);
			ZipParameters parameters = new ZipParameters();
			parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE); 
			parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL); 
			zipFile.addFile(file, parameters);
			result=true;
		} catch (ZipException e) {
			e.printStackTrace();
		} 
		return result;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new ZipSingleFile();
		System.out.println("successful!!!!!");
	}

}
