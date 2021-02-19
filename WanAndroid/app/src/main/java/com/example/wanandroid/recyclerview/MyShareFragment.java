package com.example.wanandroid.recyclerview;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wanandroid.R;
import com.example.wanandroid.activitise.function.MyCollectActivity;
import com.example.wanandroid.adapter.PublicSquareAdapter;
import com.example.wanandroid.dataClass.UsefulData;
import com.example.wanandroid.log_and_register.LogInActivity;
import com.example.wanandroid.tools.GETConnection;
import com.example.wanandroid.tools.GETConnection_2;
import com.example.wanandroid.tools.JsonAnalyze;
import com.example.wanandroid.tools.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class MyShareFragment extends Fragment {
    private View view;
    private RecyclerView recyclerView;
    private List<UsefulData> list = new ArrayList<>();
    private PublicSquareAdapter dataAdapter = new PublicSquareAdapter(list);
    GETConnection_2 get_connection = new GETConnection_2();
    JsonAnalyze jsonAnalyze = new JsonAnalyze();
    private String responseData;
    private ProgressBar progressBar;
    private TextView textView_logIn;
    private TextView textView_nan;
    String cook;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(dataAdapter);
                    progressBar.setVisibility(View.GONE);
                    if (list.size()==0){
                        textView_nan.setVisibility(View.VISIBLE);
                    }
                    Log.e("UIchange", "ui");
                    break;
                case 2:
                    Toast.makeText(getActivity(), "请求超时", Toast.LENGTH_SHORT).show();
                    break;
                case 3:
                    Toast toast = Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT);
                    progressBar.setVisibility(View.GONE);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my_share, container, false);
        recyclerView = view.findViewById(R.id.my_share_re_v);
        recyclerView.addItemDecoration(new SpacesItemDecoration(14));
        textView_logIn = view.findViewById(R.id.s_log_in_text);
        textView_nan = view.findViewById(R.id.nan_article);
        progressBar = view.findViewById(R.id.re_my_bar);

        SharedPreferences save_da = this.getActivity().getSharedPreferences("cook_data", MODE_PRIVATE);
        cook = save_da.getString("cookie", "");

        list.clear();
        if (TextUtils.isEmpty(cook)) {
            textView_logIn.setVisibility(View.VISIBLE);
            showResponse(3);
        } else {
            new Thread(() -> {
                responseData = get_connection.sendGetNetRequest("https://www.wanandroid.com/user/lg/private_articles/1/json", cook);
                if (responseData.equals("1")) {
                    showResponse(2);
                } else {
                    jsonAnalyze.JsonDataGet_shareUser_list(responseData, list);
                    showResponse(1);
                }
            }).start();
        }

        textView_logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LogInActivity.class);
                startActivity(intent);
            }
        });


        return view;
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
