package com.zhouhao.zip;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


public class ZipUtil {
	
	 /** 
     * @param srcPath Ҫѹ����Դ�ļ�·�������ѹ��һ���ļ�����Ϊ���ļ���ȫ·�������ѹ��һ��Ŀ¼����Ϊ��Ŀ¼�Ķ���Ŀ¼·�� 
     * @param zipPath ѹ���ļ������·����ע�⣺zipPath������srcPath·���µ����ļ��� 
     * @param zipFileName ѹ���ļ��� 
     * @throws Exception 
     */  
    public void zip(String srcPath, String zipPath, String zipFileName) throws Exception
    {  
        if (isEmpty(srcPath) || isEmpty(zipPath) ||isEmpty(zipFileName))  
        {  
        	 throw new Exception("parameter must not be null or empty.");  
        }  
        CheckedOutputStream cos = null;  
        ZipOutputStream zos = null;                       
        try  
        {  
            File srcFile = new File(srcPath);  
              
            //�ж�ѹ���ļ������·���Ƿ�ΪԴ�ļ�·�������ļ��У�����ǣ����׳��쳣����ֹ���޵ݹ�ѹ���ķ�����  
            if (srcFile.isDirectory() && zipPath.indexOf(srcPath)!=-1)   
            {  
                throw new Exception("zipPath must not be the child directory of srcPath.");  
            }  
              
            //�ж�ѹ���ļ������·���Ƿ���ڣ���������ڣ��򴴽�Ŀ¼  
            File zipDir = new File(zipPath);  
            if (!zipDir.exists() || !zipDir.isDirectory())  
            {  
                zipDir.mkdirs();  
            }  
              
            //����ѹ���ļ�������ļ�����  
            String zipFilePath = zipPath + File.separator + zipFileName;  
            File zipFile = new File(zipFilePath);             
            /*   if (zipFile.exists())  
            {  
                //����ļ��Ƿ�����ɾ�������������ɾ���������׳�SecurityException  
                SecurityManager securityManager = new SecurityManager();  
                securityManager.checkDelete(zipFilePath);  
                //ɾ���Ѵ��ڵ�Ŀ���ļ�  
                zipFile.delete();                 
            }  */
              
            cos = new CheckedOutputStream(new FileOutputStream(zipFile), new CRC32());  
            zos = new ZipOutputStream(cos);  
              
            //���ֻ��ѹ��һ���ļ�������Ҫ��ȡ���ļ��ĸ�Ŀ¼  
            String srcRootDir = srcPath;  
            if (srcFile.isFile())  
            {  
                int index = srcPath.lastIndexOf(File.separator);  
                if (index != -1)  
                {  
                    srcRootDir = srcPath.substring(0, index);  
                }  
            }  
            //���õݹ�ѹ����������Ŀ¼���ļ�ѹ��  
            zip(srcRootDir, srcFile, zos);  
            zos.flush();  
        }  
        catch (Exception e)   
        {  
            throw e;  
        }  
        finally   
        {             
            try  
            {  
                if (zos != null)  
                {  
                    zos.close();  
                }                 
            }  
            catch (Exception e)  
            {  
                e.printStackTrace();  
            }             
        }  
    }  
      
    /** 
     * �ݹ�ѹ���ļ��� 
     * @param srcRootDir ѹ���ļ��и�Ŀ¼����·�� 
     * @param file ��ǰ�ݹ�ѹ�����ļ���Ŀ¼���� 
     * @param zos ѹ���ļ��洢���� 
     * @throws Exception 
     */  
    private void zip(String srcRootDir, File file, ZipOutputStream zos) throws Exception  
    {  
        if (file == null)   
        {  
            return;  
        }                 
          
        //������ļ�����ֱ��ѹ�����ļ�  
        if (file.isFile())  
        {             
            int count, bufferLen = 1024;  
            byte data[] = new byte[bufferLen];  
              
            //��ȡ�ļ������ѹ���ļ��и�Ŀ¼����·��  
            String subPath = file.getAbsolutePath();  
            int index = subPath.indexOf(srcRootDir);  
            if (index != -1)   
            {  
                subPath = subPath.substring(srcRootDir.length() + File.separator.length());  
            }  
            ZipEntry entry = new ZipEntry(subPath);  
            zos.putNextEntry(entry);  
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));  
            while ((count = bis.read(data, 0, bufferLen)) != -1)   
            {  
                zos.write(data, 0, count);  
            }  
            bis.close();  
            zos.closeEntry();  
        }  
        //�����Ŀ¼����ѹ������Ŀ¼  
        else   
        {  
            //ѹ��Ŀ¼�е��ļ�����Ŀ¼  
            File[] childFileList = file.listFiles();  
            for (int n=0; n<childFileList.length; n++)  
            {  
                childFileList[n].getAbsolutePath().indexOf(file.getAbsolutePath());  
                zip(srcRootDir, childFileList[n], zos);  
            }  
        }  
    }  
      
      
    public static void main(String[] args)   
    {  
    	ZipUtil zu =new ZipUtil();
        String zipPath = "files\\";  
        String dir = "files1\\alawaysreboot";  
        String zipFileName = "my.zip";  
        try  
        {  
        	zu.zip(dir, zipPath, zipFileName);  
        }   
        catch (Exception e)  
        {  
            e.printStackTrace();  
        }  
    }  
    
    public boolean isEmpty(String str){
    	if(str==null||str.equals("")){
    		return true;
    	}
    	return false;
    }

}
