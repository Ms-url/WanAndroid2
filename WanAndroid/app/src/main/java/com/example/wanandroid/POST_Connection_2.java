package com.example.wanandroid;

import android.content.SharedPreferences;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import static android.content.Context.MODE_PRIVATE;

public class POST_Connection_2 {

    private String responseData;

    public String getResponseData() {
        return responseData;
    }

    public void setResponseData(String responseData) {
        this.responseData = responseData;
    }

    protected String sendGetNetRequest(String murl,String cook) {
        POST_Connection_2 post_connection = new POST_Connection_2();
        try {
            URL url = new URL(murl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");

         // connection.setRequestProperty("cookie","loginUserName_wanandroid_com=hash;token_pass_wanandroid_com=4e2bae5cebc948db2201c8496f31f385;JSESSIONID=DEFC17548A8D31E44B58C7C423394B8B;loginUserName=hash;token_pass=4e2bae5cebc948db2201c8496f31f385");
            connection.setRequestProperty("cookie",cook);

            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            connection.connect();
            InputStream in = connection.getInputStream();
            Log.e("send", "ok");
            post_connection.setResponseData(StreamToString(in));

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
            post_connection.setResponseData("1");
            Log.e("time1", "请求超时");
        } catch (IOException e) {
            e.printStackTrace();
            post_connection.setResponseData("1");
            Log.e("time2", "请求超时");
        }
        String finally_responseData = post_connection.getResponseData();
        return finally_responseData;
    }

    private String StreamToString(InputStream in) {
        StringBuilder sb = new StringBuilder();
        String oneLine;
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        try {
            while ((oneLine = reader.readLine()) != null) {
                sb.append(oneLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

}
