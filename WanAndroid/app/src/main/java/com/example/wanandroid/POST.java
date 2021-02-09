package com.example.wanandroid;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Handler;

import static android.content.Context.MODE_PRIVATE;

public class POST {
    private static Context mContext;

    public static boolean sendPOSTRequest(Context context, String baseUrl, HashMap<String, String> params) throws Exception{

       mContext = context;
        // 设置参数
        byte[] entity = onParams(params);

        // 设置请求链接
        HttpURLConnection conn = onSetConn(context, baseUrl, entity);

        // 得到返回值
        int responseCode = conn.getResponseCode();

        if (HttpURLConnection.HTTP_OK == responseCode) {
            saveRequestResult(context, conn);
        } else {
          /*  Message msg = new Message();
            msg.what = UpsHttpConstant.REQUEST_CODE_FAILED;
            msg.obj = "Post wrong!";
            handler.sendMessage(msg);*/
        }

        if(conn!=null){
            conn.disconnect();
        }
        return false;
    }


    private static byte[] onParams(HashMap<String, String> params) throws UnsupportedEncodingException {
        if (params == null || params.isEmpty()){
            params = new HashMap<>();
        }
        // 添加必须携带的参数信息


        //StringBuilder是用来组拼请求参数
        StringBuilder sb = new StringBuilder();
        if(params != null && params.size() != 0){
            for (Map.Entry<String, String> entry : params.entrySet()) {
                sb.append(entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue(), "utf-8"));
                sb.append("&");
            }
            sb.deleteCharAt(sb.length()-1);
        }
        return sb.toString().getBytes();
    }

    private static HttpURLConnection onSetConn(Context context, String baseUrl, byte[] entity) throws IOException {
        URL url = new URL(baseUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5000);
        // 设置以POST方式
        conn.setRequestMethod("POST");
        //要向外输出数据，要设置这个
        conn.setDoOutput(true);
        // 1、POST请求这个一定要设置
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Content-Length", entity.length + "");
        // 2、添加cookie信息
        SharedPreferences save_da = mContext.getSharedPreferences("user_cookie", MODE_PRIVATE);
        String temp_cookie = save_da.getString("cookie","");
        Log.e("test_cookie","temp_cookie is " + temp_cookie);
        if (!TextUtils.isEmpty(temp_cookie)){
            conn.setRequestProperty("cookie",temp_cookie);
        }
        // 3、参数信息
        OutputStream out = conn.getOutputStream();
        //写入参数值
        out.write(entity);
        //刷新、关闭
        out.flush();
        out.close();
        return conn;
    }

    private static void saveRequestResult(Context context, HttpURLConnection connection) throws IOException {
        //获取cookie
        Map<String,List<String>> map = connection.getHeaderFields();
        Set<String> set = map.keySet();
        for (Iterator iterator = set.iterator(); iterator.hasNext();) {
            String key = (String) iterator.next();
            // 截取 Cookie
            if ("Set-Cookie".equals(key)) {
                List<String> list = map.get(key);
                StringBuilder builder = new StringBuilder();
                builder.append(list.get(0));
                String firstCookie = builder.toString().split(";")[0];
                SharedPreferences.Editor save_data = mContext.getSharedPreferences("user_data", MODE_PRIVATE).edit();
                save_data.putString("cookie",firstCookie);
                Log.e("test_cookie","save firstcookie is " + firstCookie);
            }
        }

        // 得到返回值
        String result = getResultString(connection.getInputStream(),"UTF-8");
    }

    private static String getResultString(InputStream inputStream, String encode) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] data = new byte[1024];
        int len = 0;
        String result = "";
        if (inputStream != null) {
            try {
                while ((len = inputStream.read(data)) != -1) {
                    outputStream.write(data, 0, len);
                }
                result = new String(outputStream.toByteArray(), encode);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }


}
