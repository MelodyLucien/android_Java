package com.example.zhouhao2;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import net.sf.json.JSONObject;

/**
 * Created by zhouhao2 on 17-9-17.
 */

public class HttpUploader {

	public static void main(String[] args) {
		File file = new File("dog.txt");
		if (file.exists()) {
			System.out.println("yes");
		} else {
			System.out.println("no");
		}
		new HttpUploader().apacheHttpPost("http://vodlog.hismarttv.com/admin/api/log/upload", "dog.txt");
		// new
		// HttpUploader().httpPost("http://vodlog.hismarttv.com/admin/api/log/upload","dog.txt","bug.zip");
	}

	private static boolean apacheHttpPost(String url, String filename) {
		try {
			boolean res = false;
			DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
			defaultHttpClient.getParams().setParameter("http.protocol.version", HttpVersion.HTTP_1_1);
			defaultHttpClient.getParams().setParameter("http.connection.timeout", Integer.valueOf(6000));
			defaultHttpClient.getParams().setParameter("http.socket.timeout", Integer.valueOf(12000));
			HttpPost httpPost = new HttpPost(url);
			File file = new File(filename);
			MultipartEntity multipartEntity = new MultipartEntity();
			multipartEntity.addPart("userfile", new FileBody(file));
			httpPost.setEntity(multipartEntity);
			HttpResponse httpResponse = null;

			httpResponse = (HttpResponse) defaultHttpClient.execute(httpPost);

			HttpEntity httpEntity = httpResponse.getEntity();

			if (httpEntity != null) {
				String result = null;

				result = EntityUtils.toString(httpEntity, "utf-8");
				
				System.out.println(result);

				if (httpEntity != null) {
					httpEntity.consumeContent();
				}

				if (defaultHttpClient != null) {
					defaultHttpClient.getConnectionManager().shutdown();
				}
			}

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return false;
	}

	/**
	 * 往服务器上上传文本 比如log日志
	 *
	 * @param urlstr
	 *            请求的url
	 * @param uploadFile
	 *            log日志的路径 /mnt/shell/emulated/0/LOG/LOG.log
	 * @param newName
	 *            log日志的名字 LOG.log
	 * @return
	 */
	public static void httpPost(String urlstr, String uploadFile, String newName) {
		String end = "\r\n";
		String twoHyphens = "--";
		String boundary = "*****";// 边界标识
		int TIME_OUT = 10 * 1000; // 超时时间
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
			con.setRequestMethod("POST");// 请求方式
			con.setRequestProperty("Connection", "Keep-Alive");// 在一次TCP连接中可以持续发送多份数据而不会断开连接
			con.setRequestProperty("Charset", "UTF-8");// 设置编码
			con.setRequestProperty("Content-Type", // multipart/form-data能上传文件的编码格式
					"multipart/form-data;boundary=" + boundary);

			ds = new DataOutputStream(con.getOutputStream());
			ds.writeBytes(twoHyphens + boundary + end);
			ds.writeBytes("Content-Disposition: form-data; " + "name=\"stblog\";filename=\"" + newName + "\"" + end);
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
			ds.writeBytes(twoHyphens + boundary + twoHyphens + end);// 结束

			fStream.close();
			ds.flush();
			/* 取得Response内容 */
			is = con.getInputStream();
			int ch;
			StringBuffer b = new StringBuffer();
			while ((ch = is.read()) != -1) {
				b.append((char) ch);
			}
		} catch (Exception e) {
			e.printStackTrace();
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
}