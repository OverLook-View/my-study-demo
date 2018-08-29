package com.sy.test.wechat.utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.*;
import java.net.URL;

/**
 * http请求工具类
 *
 * @author pc
 */
public class HttpClientUtil {

    /**
     * 发起https请求并获取结果
     *
     * @param requestUrl    请求地址
     * @param requestMethod 请求方式（GET、POST）
     * @param outputStr     提交的数据
     * @return JSONObject(通过JSONObject.get ( key)的方式获取json对象的属性值)
     */
    public static JsonObject httpRequest(String requestUrl, String requestMethod, String outputStr) throws Exception {
        JsonObject jsonObject = null;
        StringBuffer buffer = new StringBuffer();
        OutputStream outputStream = null;
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        HttpsURLConnection httpUrlConn = null;
        try {
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = {new HttpsX509TrustManager()};
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();

            URL url = new URL(requestUrl);
            httpUrlConn = (HttpsURLConnection) url.openConnection();
            httpUrlConn.setSSLSocketFactory(ssf);

            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            httpUrlConn.setRequestMethod(requestMethod);

            if ("GET".equalsIgnoreCase(requestMethod))
                httpUrlConn.connect();

            // 当有数据需要提交时
            if (null != outputStr) {
                outputStream = httpUrlConn.getOutputStream();
                // 注意编码格式，防止中文乱码
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }

            // 将返回的输入流转换成字符串
            inputStream = httpUrlConn.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            bufferedReader = new BufferedReader(inputStreamReader);

            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            jsonObject = (JsonObject) new JsonParser().parse(buffer.toString());
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (inputStreamReader != null) {
                inputStreamReader.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
            if (httpUrlConn != null) {
                httpUrlConn.disconnect();
            }
        }
        return jsonObject;
    }

    /**
     * 模拟form表单的形式 ，上传文件 以输出流的形式把文件写入到url中，然后用输入流来获取url的响应
     *
     * @param requestUrl 请求地址 form表单url地址
     * @param file       文件路径
     * @return JSONObject url的响应信息返回值
     * @throws Exception
     */
    // public static JSONObject httpRequestPostForm(String requestUrl, File
    // file, String filename, String ContentType,String title,String
    // introduction)
    public static JsonObject httpRequestPostForm(String requestUrl, File file, JsonObject parameters) throws Exception {
        JsonObject jsonObject = null;
        StringBuffer buffer = new StringBuffer();
        OutputStream outputStream = null;
        BufferedReader bufferedReader = null;
        InputStreamReader inputStreamReader = null;
        InputStream inputStreamRequest = null;
        HttpsURLConnection httpUrlConn = null;
        try {
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = {new HttpsX509TrustManager()};
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();
            // 连接
            URL url = new URL(requestUrl);
            httpUrlConn = (HttpsURLConnection) url.openConnection();
            httpUrlConn.setSSLSocketFactory(ssf);
            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            httpUrlConn.setRequestMethod("POST");
            // 设置请求头信息
            httpUrlConn.setRequestProperty("Connection", "Keep-Alive");
            httpUrlConn.setRequestProperty("Charset", "UTF-8");

            String boundary = "-----------------------------" + System.currentTimeMillis();
            httpUrlConn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

            outputStream = httpUrlConn.getOutputStream();
            outputStream.write(("--" + boundary + "\r\n").getBytes("UTF-8"));
            outputStream.write(String.format("Content-Disposition: form-data; name=\"media\"; filename=\"%s\"\r\n", parameters.get("filename").getAsString()).getBytes("UTF-8"));
            outputStream.write(String.format("Content-Type:\"%s\" \r\n\r\n", parameters.get("contenttype").getAsString()).getBytes("UTF-8"));
            byte[] data = new byte[1024];
            int len = 0;
            FileInputStream inputStream = new FileInputStream(file);
            while ((len = inputStream.read(data)) > -1) {
                outputStream.write(data, 0, len);
            }
            inputStream.close();

            // 如果上传的是video第二个form
            String mediaType = parameters.get("type").getAsString();
            if (mediaType.equals("video")) {
                outputStream.write(("\r\n--" + boundary + "\r\n").getBytes("UTF-8"));
                outputStream.write("Content-Disposition: form-data; name=\"description\";\r\n\r\n".getBytes());
                outputStream.write(String.format("{\"title\":\"%s\", \"introduction\":\"%s\"}", parameters.get("title").getAsString(), parameters.get("introduction").getAsString()).getBytes("UTF-8"));
            }

            outputStream.write(("\r\n--" + boundary + "--\r\n\r\n").getBytes("UTF-8"));
            outputStream.flush();
            // 将返回的输入流转换成字符串
            inputStreamRequest = httpUrlConn.getInputStream();
            inputStreamReader = new InputStreamReader(inputStreamRequest, "utf-8");
            bufferedReader = new BufferedReader(inputStreamReader);

            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            jsonObject = (JsonObject) new JsonParser().parse(buffer.toString());
            return jsonObject;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw e;
        } finally {
            // 释放资源
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (inputStreamRequest != null) {
                    inputStreamRequest.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (httpUrlConn != null) {
                    httpUrlConn.disconnect();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}