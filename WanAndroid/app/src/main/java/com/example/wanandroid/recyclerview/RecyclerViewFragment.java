package com.example.wanandroid.recyclerview;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.wanandroid.tools.GETConnection;
import com.example.wanandroid.tools.GETConnection_2;
import com.example.wanandroid.tools.JsonAnalyze;
import com.example.wanandroid.R;
import com.example.wanandroid.tools.SpacesItemDecoration;
import com.example.wanandroid.dataClass.UsefulData;
import com.example.wanandroid.adapter.CommonsAdapter;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class RecyclerViewFragment extends Fragment {
    private View view;
    private RecyclerView recyclerView;
    private List<UsefulData> list = new ArrayList<>();
    private CommonsAdapter dataAdapter = new CommonsAdapter(list);
    GETConnection_2 get_connection = new GETConnection_2();
    JsonAnalyze jsonAnalyze = new JsonAnalyze();
    private String top_responseData;
    private String responseData;
    private String cook;
    ProgressBar progressBar ;

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(dataAdapter);
                    progressBar.setVisibility(View.GONE);
                    Log.e("UIchange", "ui");
                    break;
                case 2:
                    Toast.makeText(getActivity(), "请求超时", Toast.LENGTH_SHORT).show();
                    break;

            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_recycler_view, container, false);
        recyclerView = view.findViewById(R.id.recycler_v);
        recyclerView.addItemDecoration(new SpacesItemDecoration(14));
        progressBar = view.findViewById(R.id.re_bar1);

        SharedPreferences save_da = getActivity().getSharedPreferences("cook_data", MODE_PRIVATE);
        cook = save_da.getString("cookie", "");

        new Thread(() -> {
            top_responseData = get_connection.sendGetNetRequest("https://www.wanandroid.com/article/top/json",cook);
            responseData = get_connection.sendGetNetRequest("https://www.wanandroid.com/article/list/0/json",cook);
            if (top_responseData.equals("1")||responseData.equals("1")){
                showResponse(2);
            }else {
            jsonAnalyze.JsonDataGet_top_article(top_responseData, list);
            jsonAnalyze.JsonDataGet_article(responseData, list);
            showResponse(1);
            }
            Log.e("list2", String.valueOf(list));
        }).start();

        return view;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
