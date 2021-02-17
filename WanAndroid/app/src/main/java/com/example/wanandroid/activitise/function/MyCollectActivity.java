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
import com.example.wanandroid.activitise.Common.ShareUserActivity;
import com.example.wanandroid.adapter.ShareUserListAdapter;
import com.example.wanandroid.dataClass.UsefulData;
import com.example.wanandroid.log_and_register.LogInActivity;
import com.example.wanandroid.tools.GETConnection;
import com.example.wanandroid.tools.JsonAnalyze;

import java.util.ArrayList;
import java.util.List;

public class MyCollectActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<UsefulData> list = new ArrayList<>();
    private List<String> list2 = new ArrayList<>();
    private List<Integer> list3 = new ArrayList<>();
    private ShareUserListAdapter dataAdapter = new ShareUserListAdapter(list);
    GETConnection get_connection = new GETConnection();
    JsonAnalyze jsonAnalyze = new JsonAnalyze();
    private String responseData;
    ProgressBar progressBar;
    private String userId;
    private TextView textView_name;
    private TextView textView_level;
    private TextView textView_rank;
    private TextView textView_coinCount;
    private TextView textView_login;

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MyCollectActivity.this);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(dataAdapter);
                    Log.e("list2", String.valueOf(list2.size()));
                    Log.e("list3", String.valueOf(list3.size()));
                    textView_rank.setText(" 排名" + list2.get(1) + " ");
                    textView_name.setText(list2.get(0));
                    textView_coinCount.setText("积分：" + String.valueOf(list3.get(0)));
                    textView_level.setText(" Lv" + String.valueOf(list3.get(1)) + " ");
                    progressBar.setVisibility(View.GONE);
                    break;
                case 2:
                    Toast.makeText(MyCollectActivity.this, "请求超时", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_collect);
        textView_coinCount = findViewById(R.id.my_coinCount);
        textView_level = findViewById(R.id.my_level);
        textView_name = findViewById(R.id.my_name);
        textView_rank = findViewById(R.id.my_rank);
        textView_login= findViewById(R.id.log_in_text);
        progressBar = findViewById(R.id.re_my_collect_bar);

        SharedPreferences user_da = getSharedPreferences("user_data", MODE_PRIVATE);
        userId = String.valueOf(user_da.getInt("user_id", 2));

        if (TextUtils.isEmpty(userId)) {
            new Thread(() -> {
                responseData = get_connection.sendGetNetRequest("https://www.wanandroid.com/user/" + userId + "/share_articles/0/json");
                Log.e("Thread", "begin");
                if (responseData.equals("1")) {
                    showResponse(2);
                } else {
                    jsonAnalyze.JsonDataGet_shareUser_list(responseData, list, list2, list3);
                    showResponse(1);
                }
            }).start();
        } else {
            textView_name.setText("未登录");
            progressBar.setVisibility(View.GONE);
            textView_login.setVisibility(View.VISIBLE);
            textView_rank.setText(" 排名 0 ");
            textView_name.setText(list2.get(0));
            textView_coinCount.setText("积分：0" );
            textView_level.setText(" Lv 0 ");
            Toast toast=Toast.makeText(MyCollectActivity.this,"请先登录",Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
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
