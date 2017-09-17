package com.zhouhao2.newbuguploader.zip;

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
     * @param srcPath
                      the path of the file needed to be compressed,if a file offer its full path including file name otherwise the dir path
     * @param zipPath
                      the path the file compressed into. note: can not be a sub dir of srcpath
     * @param zipFileName
                       the filename we want to make compression
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
            //if the save path is the sub dir of srcpath throw a exception
            if (srcFile.isDirectory() && zipPath.indexOf(srcPath)!=-1)
            {
                throw new Exception("zipPath must not be the child directory of srcPath.");
            }
            //if the save path do not exists ,create it
            File zipDir = new File(zipPath);
            if (!zipDir.exists() || !zipDir.isDirectory())
            {
                zipDir.mkdirs();
            }
            //create compression result file
            String zipFilePath = zipPath + File.separator + zipFileName;
            File zipFile = new File(zipFilePath);
            /*if (zipFile.exists())
            {
                //check out the permission of delete,if not allowed throw  SecurityException
                SecurityManager securityManager = new SecurityManager();
                securityManager.checkDelete(zipFilePath);
                //delete the file already exists
                zipFile.delete();
            }  */
            cos = new CheckedOutputStream(new FileOutputStream(zipFile), new CRC32());
            zos = new ZipOutputStream(cos);
            //if only a file , get the parent dir
            String srcRootDir = srcPath;
            if (srcFile.isFile())
            {
                int index = srcPath.lastIndexOf(File.separator);
                if (index != -1)
                {
                    srcRootDir = srcPath.substring(0, index);
                }
            }
            //invoke the compress method
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
     * recursively zip the file
     * @param srcRootDir
                         the dir's root path
     * @param file
                   the current file or dir needed to recursively compress
     * @param zos
                  the save result of compressed files
     * @throws Exception
     */
    private void zip(String srcRootDir, File file, ZipOutputStream zos) throws Exception
    {
        if (file == null)
        {
            return;
        }
        // if a file ,compress directly
        if (file.isFile())
        {
            int count, bufferLen = 1024;
            byte data[] = new byte[bufferLen];
            //get the subpath the current file relative to the srcRootDir
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
        //if a dir ,compress all the content
        else
        {
            //compress every file or dir
            File[] childFileList = file.listFiles();
            for (int n=0; n<childFileList.length; n++)
            {
                childFileList[n].getAbsolutePath().indexOf(file.getAbsolutePath());
                zip(srcRootDir, childFileList[n], zos);
            }
        }
    }
    public boolean isEmpty(String str){
        if(str==null||str.equals("")){
            return true;
        }
        return false;
    }
}
