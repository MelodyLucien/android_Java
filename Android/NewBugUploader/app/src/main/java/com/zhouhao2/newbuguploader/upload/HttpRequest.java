package com.zhouhao2.newbuguploader.upload;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;

import com.zhouhao2.newbuguploader.newuploader.BugReport2Ftp;
public class HttpRequest {

    private static final String TAG = BugReport2Ftp.class.getSimpleName();
    private static final int SOCKET_CONNECT_TIMEOUT = 60 * 1000;

    public static String Get(String url) {

        HttpGet req = new HttpGet(url);
        BasicHttpParams httpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParams,
                SOCKET_CONNECT_TIMEOUT);
        HttpConnectionParams.setSoTimeout(httpParams, SOCKET_CONNECT_TIMEOUT);
        DefaultHttpClient client = new DefaultHttpClient(httpParams);
        try {
            HttpResponse response = client.execute(req);
            StatusLine status = response.getStatusLine();
            if (status.getStatusCode() == 200) {
                HttpEntity rspEntity = response.getEntity();
                String responseString = EntityUtils.toString(rspEntity);
                return responseString;

            } else {
                Log.d(TAG,
                        "execCNDCommand failure result="
                                + status.getStatusCode());
                return "HTTP���󷵻�״̬����" + status.getStatusCode();
            }

        } catch (Exception ex) {
            Log.e(TAG, " HTTP request exception:" + ex.getMessage());
            return ex.getMessage();
        }
    }

    public static String Login(String url) {

        HttpGet req = new HttpGet(url);
        BasicHttpParams httpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParams,
                SOCKET_CONNECT_TIMEOUT);
        HttpConnectionParams.setSoTimeout(httpParams, SOCKET_CONNECT_TIMEOUT);
        DefaultHttpClient client = new DefaultHttpClient(httpParams);
        try {
            HttpResponse response = client.execute(req);
            StatusLine status = response.getStatusLine();
            if (status.getStatusCode() == 200) {
                HttpEntity rspEntity = response.getEntity();
                String responseString = EntityUtils.toString(rspEntity);
                Log.i(TAG, " login response is:" + responseString);
                return responseString;
            } else {
                Log.d(TAG,
                        "execCNDCommand failure result="
                                + status.getStatusCode());
                return String.valueOf(status.getStatusCode());
            }

        } catch (Exception ex) {
            String e = ex.getMessage();
            Log.e(TAG, "Unable to execute CNTV command: " + url
                    + " and exception:" + e + " cause:" + ex.getCause());
            return "-1";
        }
    }

    public static String GetId(String url) {

        HttpGet req = new HttpGet(url);
        BasicHttpParams httpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParams,
                SOCKET_CONNECT_TIMEOUT);
        HttpConnectionParams.setSoTimeout(httpParams, SOCKET_CONNECT_TIMEOUT);
        DefaultHttpClient client = new DefaultHttpClient(httpParams);
        try {
            HttpResponse response = client.execute(req);
            StatusLine status = response.getStatusLine();
            if (status.getStatusCode() == 200) {
                HttpEntity rspEntity = response.getEntity();
                String responseString = EntityUtils.toString(rspEntity);
                return responseString;
            } else {
                Log.d(TAG,
                        "execCNDCommand failure result="
                                + status.getStatusCode());
                return "-1";
            }
        } catch (Exception ex) {
            Log.e(TAG, " HTTP request exception:" + ex.getMessage());
            return "-2";
        }
    }
}
