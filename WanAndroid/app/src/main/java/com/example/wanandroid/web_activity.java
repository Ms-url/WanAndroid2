package com.example.wanandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.HashMap;

public class web_activity extends AppCompatActivity {
    private String link;
    private int id;
    private String cid;
    private String title;
    POST_Connection_2 post_connection_2 = new POST_Connection_2();
    POST_Connection post_connection = new POST_Connection();

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
                    String re = post_connection_2.sendGetNetRequest("https://www.wanandroid.com/lg/collect/" + cid + "/json");
                    Log.e("re", re);
                }).start();
                break;
            case R.id.add_share:
                new Thread(() -> {
                    HashMap<String, String> map = new HashMap<>();
                    map.put("title", title);
                    map.put("link", link);
                    String re = post_connection.sendGetNetRequest("https://www.wanandroid.com/lg/user_article/add/json", map);
                    Log.e("re",re);
                }).start();

        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_activity);
        Log.e("web_begin", "begin");
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
}