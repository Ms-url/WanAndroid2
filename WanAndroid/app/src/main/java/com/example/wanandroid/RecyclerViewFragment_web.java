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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewFragment_web extends Fragment {
    private View view;
    private RecyclerView recyclerView;
    private List<WebData> list=new ArrayList<>();
    private RecyclerViewApater_web dataAdapter=new RecyclerViewApater_web(list);
    GET_Connection get_connection=new GET_Connection();
    private String responseData;
    JsonAnalyze jsonAnalyze =new JsonAnalyze();
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_recycler_view_2, container, false);
        recyclerView=view.findViewById(R.id.recycler_web);
        recyclerView.addItemDecoration(new SpacesItemDecoration(14));

        new Thread(() ->{
            Log.e("线程web","begin");
            responseData = get_connection.sendGetNetRequest("https://www.wanandroid.com/friend/json");
            if (responseData.equals("1")){
                showResponse(2);
            }else {
                jsonAnalyze.JsonDataGet_web(responseData, list);
                showResponse(1);
            }
        }).start();

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