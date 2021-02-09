package com.example.wanandroid;

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
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewFragment extends Fragment {
    private View view;
    private RecyclerView recyclerView;
    private List<UsefulData> list = new ArrayList<>();
    private RecyclerViewAdaper_1 dataAdapter = new RecyclerViewAdaper_1(list);
    GET_Connection get_connection = new GET_Connection();
    JsonAnalyze jsonAnalyze = new JsonAnalyze();
    private String top_responseData;
    private String responseData;

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(dataAdapter);
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

        new Thread(() -> {
            top_responseData = get_connection.sendGetNetRequest("https://www.wanandroid.com/article/top/json");
            responseData = get_connection.sendGetNetRequest("https://www.wanandroid.com/article/list/0/json");
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
