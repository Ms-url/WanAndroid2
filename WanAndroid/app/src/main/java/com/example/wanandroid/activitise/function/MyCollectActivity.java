package com.example.wanandroid.activitise.function;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wanandroid.R;
import com.example.wanandroid.adapter.CollectArticleAdapter;
import com.example.wanandroid.dataClass.CollectData;
import com.example.wanandroid.log_and_register.LogInActivity;
import com.example.wanandroid.tools.GETConnection_2;
import com.example.wanandroid.tools.JsonAnalyze;

import java.util.ArrayList;
import java.util.List;

public class MyCollectActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<CollectData> list_collect_article = new ArrayList<>();
    private List<String> list2 = new ArrayList<>();
    private List<Integer> list3 = new ArrayList<>();
    private CollectArticleAdapter dataAdapter = new CollectArticleAdapter(list_collect_article);
    GETConnection_2 get_connection = new GETConnection_2();
    JsonAnalyze jsonAnalyze = new JsonAnalyze();
    private String responseData;
    ProgressBar progressBar;
    private String userId;
    private String cook;
    private TextView textView_name;
    private TextView textView_level;
    private TextView textView_rank;
    private TextView textView_coinCount;
    private TextView textView_login;

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    textView_rank.setText(" 排名" + list2.get(1) + " ");
                    textView_name.setText(list2.get(0));
                    textView_coinCount.setText("积分：" + String.valueOf(list3.get(0)));
                    textView_level.setText(" Lv" + String.valueOf(list3.get(1)) + " ");
                    break;
                case 2:
                    Toast.makeText(MyCollectActivity.this, "请求超时", Toast.LENGTH_SHORT).show();
                    break;
                case 3:
                    textView_rank.setText(" 排名 0 ");
                    textView_coinCount.setText("积分：0");
                    textView_level.setText(" Lv 0 ");
                    break;
                case 4:
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MyCollectActivity.this);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(dataAdapter);
                    progressBar.setVisibility(View.GONE);
                    Log.e("list_collect", String.valueOf(list_collect_article.size()));
                    Log.e("uichange_collect","collect_entry");
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_collect);
        recyclerView = findViewById(R.id.my_collect_list_re);
        textView_coinCount = findViewById(R.id.c_my_coinCount);
        textView_level = findViewById(R.id.c_my_level);
        textView_name = findViewById(R.id.c_my_name);
        textView_rank = findViewById(R.id.c_my_rank);
        textView_login = findViewById(R.id.log_in_text);
        progressBar = findViewById(R.id.re_my_collect_bar);

        SharedPreferences user_da = getSharedPreferences("user_data", MODE_PRIVATE);
        userId = String.valueOf(user_da.getInt("user_id", 2));
        SharedPreferences save_da = getSharedPreferences("cook_data", MODE_PRIVATE);
        cook = save_da.getString("cookie", "");

        if (TextUtils.isEmpty(userId)) {
            textView_name.setText("未登录");
            progressBar.setVisibility(View.GONE);
            textView_login.setVisibility(View.VISIBLE);
            showResponse(3);
            Toast toast = Toast.makeText(MyCollectActivity.this, "请先登录", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        } else {
            new Thread(() -> {
                responseData = get_connection.sendGetNetRequest("https://www.wanandroid.com/user/" + userId + "/share_articles/0/json", cook);
                Log.e("Thread my data", "begin");
                if (responseData.equals("1")) {
                    showResponse(2);
                } else {
                    jsonAnalyze.JsonDataGet_shareUser_data(responseData, list2, list3);
                    showResponse(1);
                }
            }).start();

            new Thread(() -> {
                responseData = get_connection.sendGetNetRequest("https://www.wanandroid.com/lg/collect/list/0/json", cook);
                Log.e("Thread collect", "begin");
                if (responseData.equals("1")) {
                    showResponse(2);
                } else {
                    jsonAnalyze.JsonDataGet_collect(responseData, list_collect_article);
                    showResponse(4);
                }
            }).start();
        }

        textView_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyCollectActivity.this, LogInActivity.class);
                startActivity(intent);
            }
        });
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
