package com.example.wanandroid.activitise.function;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wanandroid.R;
import com.example.wanandroid.adapter.CommonsAdapter;
import com.example.wanandroid.dataClass.UsefulData;
import com.example.wanandroid.tools.GETConnection;
import com.example.wanandroid.tools.JsonAnalyze;
import com.example.wanandroid.tools.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class KnowledgeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<UsefulData> list = new ArrayList<>();
    private CommonsAdapter dataAdapter = new CommonsAdapter(list);
    GETConnection get_connection = new GETConnection();
    JsonAnalyze jsonAnalyze = new JsonAnalyze();
    private String responseData;
    private ProgressBar progressBar;
    private TextView textView;
    private ImageView imageView;

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(KnowledgeActivity.this);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(dataAdapter);
                    progressBar.setVisibility(View.GONE);
                    Log.e("UIchange", "ui");
                    break;
                case 2:
                    Toast.makeText(KnowledgeActivity.this, "请求超时", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_knowledge);
        recyclerView = findViewById(R.id.knowledge_at);
        recyclerView.addItemDecoration(new SpacesItemDecoration(14));
        progressBar = findViewById(R.id.re_kn_bar);
        textView = findViewById(R.id.kn_title);
        imageView = findViewById(R.id.kn_back);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        int id = intent.getIntExtra("id", 0);
        String cid = String.valueOf(id);
        Log.e("ididid", cid);
        textView.setText(name);

        new Thread(() -> {
            responseData = get_connection.sendGetNetRequest("https://www.wanandroid.com/article/list/0/json?cid=" + id);
            if (responseData.equals("1")) {
                showResponse(2);
            } else {
                jsonAnalyze.JsonDataGet_article(responseData, list);
                showResponse(1);
            }
        }).start();

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