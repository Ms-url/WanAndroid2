package com.example.wanandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class ProjectActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<UsefulData> list = new ArrayList<>();
    private ProjectActivityAdapter dataAdapter = new ProjectActivityAdapter(list);
    GET_Connection get_connection = new GET_Connection();
    JsonAnalyze jsonAnalyze = new JsonAnalyze();
    private String responseData;

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(dataAdapter);
                    Log.e("UIchange", "ui");
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);
        recyclerView = findViewById(R.id.project_at);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        int id = intent.getIntExtra("id",0);
        String cid = String.valueOf(id);
        Log.e("ididid",cid);

        list.clear();

        new Thread(() ->{
            responseData = get_connection.sendGetNetRequest("https://www.wanandroid.com/project/list/0/json?cid="+id);
            jsonAnalyze.JsonDataGet_project_list(responseData, list);
            showResponse();
        }).start();

    }

    private void showResponse() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Message message = new Message();
                message.what = 1;
                handler.sendMessage(message);
            }
        }).start();
    }
}