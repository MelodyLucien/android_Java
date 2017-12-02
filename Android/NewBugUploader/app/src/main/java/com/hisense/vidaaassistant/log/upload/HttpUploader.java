package com.hisense.vidaaassistant.log.upload;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import com.hisense.vidaaassistant.log.mainflow.MainConstants;
import com.hisense.vidaaassistant.log.newuploader.BugReport2Ftp;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;

import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;

/**
 * Created by zhouhao2 on 17-9-17.
 */

public class HttpUploader implements IUploader{

    private final static String INTERNAL_URL = "http://vodlog.hismarttv.com/admin/api/log/upload";
    private final static String TAG = BugReport2Ftp.TAG + "--HttpUploader :";
    private final static int SOCKET_TIMEOUT = 12000;
    private final static int CONNECTION_TIMEOUT = 6000;
    private  Handler mMainFlowHandler = null;

    public HttpUploader(Handler h){
        mMainFlowHandler = h;
    }

    @Override
    public void uploadFile(String TAG,final String compressesFilePath, final Context context) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                apacheHttpPost(INTERNAL_URL,compressesFilePath,context);
            }
        }).start();
    }

    private boolean apacheHttpPost(String url,String filename,Context context) {
        boolean res = false;
        try {
            HttpClient defaultHttpClient = new DefaultHttpClient();
            defaultHttpClient.getParams().setParameter("http.protocol.version", HttpVersion.HTTP_1_1);
            defaultHttpClient.getParams().setParameter("http.connection.timeout", Integer.valueOf(CONNECTION_TIMEOUT));
            defaultHttpClient.getParams().setParameter("http.socket.timeout", Integer.valueOf(SOCKET_TIMEOUT));
            HttpPost httpPost = new HttpPost(url);

            //attach file
            File file = new File(filename);
            if(!file.exists()){
                Log.i(TAG, "apacheHttpPost: don't exists !");
            }else{
                Log.i(TAG, "apacheHttpPost: exists !");
            }
            MultipartEntity multipartEntity = new MultipartEntity();
            multipartEntity.addPart("userfile", new FileBody(file));
            httpPost.setEntity(multipartEntity);

/*            //new Method for upload
            MultipartEntityBuilder multipartentitybuilder = MultipartEntityBuilder.create();
            multipartentitybuilder.addPart("userfile",new FileBody(file));
            httpPost.setEntity(multipartentitybuilder.build())*/;

            //excute
            HttpResponse httpResponse = (HttpResponse) defaultHttpClient.execute(httpPost);

            //get result
            HttpEntity httpEntity = httpResponse.getEntity();

            if (httpEntity != null) {

                String result = null;

                result = EntityUtils.toString(httpEntity, "utf-8");

                res = new JSONObject(result).getBoolean("success");

                if (httpEntity != null) {
                    httpEntity.consumeContent();
                }

                if (defaultHttpClient != null) {
                    defaultHttpClient.getConnectionManager().shutdown();
                }

                if(res){
                    Log.i(TAG,"upload success!!!");
                    mMainFlowHandler.sendEmptyMessage(MainConstants.UPLOAD_SUCCESS);
                    return true;
                }else{
                    mMainFlowHandler.sendEmptyMessage(MainConstants.UPLOAD_ERROR);
                    return true;
                }
            }
        }catch(ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.i(TAG, "apacheHttpPost: error");
        mMainFlowHandler.sendEmptyMessage(MainConstants.UPLOAD_ERROR);
        return res;
    }

    /**
     * 往服务器上上传文本  比如log日志
     *
     * @param urlstr     请求的url
     * @param uploadFile log日志的路径
     *                   /mnt/shell/emulated/0/LOG/LOG.log
     * @param newName    log日志的名字 LOG.log
     * @return
     */
    public static void httpPost(Activity activity, Handler mainHandler, String urlstr, String uploadFile, String newName) {
        Log.i(TAG, "getEhttpPostt" + "  urlstr=" + urlstr + "   uploadFile=" + uploadFile + ";newName=" + newName);
        String end = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";//边界标识
        int TIME_OUT = 10 * 1000;   //超时时间
        HttpURLConnection con = null;
        DataOutputStream ds = null;
        InputStream is = null;
        try {
            URL url = new URL(urlstr);
            con = (HttpURLConnection) url.openConnection();
            con.setReadTimeout(TIME_OUT);
            con.setConnectTimeout(TIME_OUT);
            /* 允许Input、Output，不使用Cache */
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setUseCaches(false);

            // 设置http连接属性
            con.setRequestMethod("POST");//请求方式
            con.setRequestProperty("Connection", "Keep-Alive");//在一次TCP连接中可以持续发送多份数据而不会断开连接
            con.setRequestProperty("Charset", "UTF-8");//设置编码
            con.setRequestProperty("Content-Type",//multipart/form-data能上传文件的编码格式
                    "multipart/form-data;boundary=" + boundary);

            ds = new DataOutputStream(con.getOutputStream());
            ds.writeBytes(twoHyphens + boundary + end);
            ds.writeBytes("Content-Disposition: form-data; "
                    + "name=\"stblog\";filename=\"" + newName + "\"" + end);
            ds.writeBytes(end);

            // 取得文件的FileInputStream
            FileInputStream fStream = new FileInputStream(uploadFile);
            /* 设置每次写入1024bytes */
            int bufferSize = 1024;
            byte[] buffer = new byte[bufferSize];
            int length = -1;
            /* 从文件读取数据至缓冲区 */
            while ((length = fStream.read(buffer)) != -1) {
                /* 将资料写入DataOutputStream中 */
                ds.write(buffer, 0, length);
            }
            ds.writeBytes(end);
            ds.writeBytes(twoHyphens + boundary + twoHyphens + end);//结束

            fStream.close();
            ds.flush();
            /* 取得Response内容 */
            is = con.getInputStream();
            int ch;
            StringBuffer b = new StringBuffer();
            while ((ch = is.read()) != -1) {
                b.append((char) ch);
            }
            /* 将Response显示于Dialog */
            showDialog(activity, true, uploadFile, "上传成功" + b.toString().trim());
        } catch (Exception e) {
            showDialog(activity, false, uploadFile, "上传失败" + e);
        } finally {
             /* 关闭DataOutputStream */
            if (ds != null) {
                try {
                    ds.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (con != null) {
                con.disconnect();
            }
        }
    }

    /* 显示Dialog的method */
    private static void showDialog(final Activity activity, final Boolean isSuccess, final String uploadFile, final String mess) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new AlertDialog.Builder(activity).setTitle("Message")
                        .setMessage(mess)
                        .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                File file = new File(uploadFile);
                                if (file.exists() && isSuccess) {//日志文件存在且上传日志成功
                                    file.delete();
                                    Toast.makeText(activity, "log日志已删除", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }).show();
            }
        });

    }
}
