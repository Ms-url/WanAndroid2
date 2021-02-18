package com.example.wanandroid.fragment.coin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wanandroid.R;
import com.example.wanandroid.adapter.CoinRankAdapter;
import com.example.wanandroid.adapter.MyCoinAdapter;
import com.example.wanandroid.dataClass.CoinData;
import com.example.wanandroid.log_and_register.LogInActivity;
import com.example.wanandroid.tools.GETConnection_2;
import com.example.wanandroid.tools.JsonAnalyze;
import com.example.wanandroid.tools.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class CoinRankFragment extends Fragment {
    private View view;
    private RecyclerView recyclerView;
    private List<CoinData> list = new ArrayList<>();
    private CoinRankAdapter dataAdapter = new CoinRankAdapter(list);
    GETConnection_2 get_connection = new GETConnection_2();
    JsonAnalyze jsonAnalyze = new JsonAnalyze();
    private String responseData;
    private ProgressBar progressBar;
    private TextView textView_logIn;
    String cook;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(dataAdapter);
                    progressBar.setVisibility(View.GONE);
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
        view = inflater.inflate(R.layout.fragment_coin_rank, container, false);
        recyclerView = view.findViewById(R.id.coin_rank_re);
        recyclerView.addItemDecoration(new SpacesItemDecoration(0));
        textView_logIn = view.findViewById(R.id.c_r_log_in_text);
        progressBar = view.findViewById(R.id.coin_rank_bar);

        SharedPreferences save_da = this.getActivity().getSharedPreferences("cook_data", MODE_PRIVATE);
        cook = save_da.getString("cookie", "");
        if (TextUtils.isEmpty(cook)) {
            textView_logIn.setVisibility(View.VISIBLE);
            showResponse(3);
        } else {
            new Thread(() -> {
                responseData = get_connection.sendGetNetRequest("https://www.wanandroid.com/coin/rank/1/json", cook);
                if (responseData.equals("1")) {
                    showResponse(2);
                } else {
                    jsonAnalyze.JsonDataGet_coin_rank(responseData, list);
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
