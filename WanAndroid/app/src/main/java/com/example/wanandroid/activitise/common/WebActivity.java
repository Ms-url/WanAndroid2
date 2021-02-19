package com.example.wanandroid.activitise.common;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.wanandroid.R;
import com.example.wanandroid.tools.JsonAnalyze;
import com.example.wanandroid.tools.POSTConnection_1;
import com.example.wanandroid.tools.POSTConnection_2;

import java.util.HashMap;

public class WebActivity extends AppCompatActivity {
    private String link;
    private int id;
    private String cid;
    private String title;
    private String cook;
    private String data1;
    private String errorMsg1;
    private int errorCode1;
    private String data2;
    private String errorMsg2;
    private int errorCode2;
    private String data3;
    private String errorMsg3;
    private int errorCode3;
    private String re;
    private String re2;
    private String re3;
    JsonAnalyze jsonAnalyze = new JsonAnalyze();
    POSTConnection_2 post_connection_2 = new POSTConnection_2();
    POSTConnection_1 post_connection = new POSTConnection_1();

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    Toast.makeText(WebActivity.this, errorMsg1, Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    Toast.makeText(WebActivity.this, errorMsg2, Toast.LENGTH_SHORT).show();
                    break;
                case 3:
                    Toast.makeText(WebActivity.this, "收藏成功", Toast.LENGTH_SHORT).show();
                    break;
                case 4:
                    Toast.makeText(WebActivity.this, "分享成功", Toast.LENGTH_SHORT).show();
                    break;
                case 5:
                    Toast.makeText(WebActivity.this, errorMsg3, Toast.LENGTH_SHORT).show();


            }
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_collect:
                new Thread(() -> {
                    re = post_connection_2.sendGetNetRequest("https://www.wanandroid.com/lg/collect/" + cid + "/json", cook);
                    jsonAnalyze.JsonDataGet_share_web(re, data1, errorMsg1, errorCode1);
                    if (errorCode1 == 0) {
                        showResponse(1);
                    } else {
                        showResponse(3);
                    }
                    Log.e("re", re);
                }).start();
                break;
            case R.id.add_share:
                new Thread(() -> {
                    HashMap<String, String> map = new HashMap<>();
                    map.put("title", title);
                    map.put("link", link);
                    re2 = post_connection.sendGetNetRequest("https://www.wanandroid.com/lg/user_article/add/json", map, cook);
                    jsonAnalyze.JsonDataGet_share_web(re2, data1, errorMsg2, errorCode2);
                    if (errorCode2 == 0) {
                        showResponse(2);
                    } else {
                        showResponse(4);
                    }
                    Log.e("re2", re2);
                }).start();
                break;
            case R.id.web_collect:
                new Thread(() -> {
                    HashMap<String, String> map = new HashMap<>();
                    map.put("name", title);
                    map.put("link", link);
                    re3 = post_connection.sendGetNetRequest("https://www.wanandroid.com/lg/collect/addtool/json", map, cook);
                    jsonAnalyze.JsonDataGet_share_web(re3, data3, errorMsg3, errorCode3);
                    if (errorCode3 == 0) {
                        showResponse(5);
                    } else {
                        showResponse(3);
                    }
                    Log.e("re3", re3);
                }).start();
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_activity);

        SharedPreferences save_da = getSharedPreferences("cook_data", MODE_PRIVATE);
        cook = save_da.getString("cookie", "");
        Log.e("cookie", cook);

        Intent intent = getIntent();
        link = intent.getStringExtra("links");
        id = intent.getIntExtra("id", 0);
        title = intent.getStringExtra("title");
        cid = String.valueOf(id);

        WebView webView = (WebView) findViewById(R.id.web_view);
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(link);

    }


    private void showResponse(int num) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Message message = new Message();
                message.what = num;
                handler.sendMessage(message);
            }
        }).start();
    }
}