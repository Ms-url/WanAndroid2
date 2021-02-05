package com.example.wanandroid;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class ProjectRecyclerFragment extends Fragment {
    private View view;
    private RecyclerView recyclerView;
    private List<TreeData> list = new ArrayList<>();
    private ProjectTreeAdapter2 dataAdapter = new ProjectTreeAdapter2(list);
    GET_Connection get_connection = new GET_Connection();
    JsonAnalyze jsonAnalyze = new JsonAnalyze();
    private String responseData;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),2);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(dataAdapter);
                    Log.e("UIchange", "ui");
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
        view = inflater.inflate(R.layout.fragment_project_recycler, container, false);
        recyclerView = view.findViewById(R.id.Project_recycler);

        new Thread(() ->{
            responseData = get_connection.sendGetNetRequest("https://www.wanandroid.com/project/tree/json");
            jsonAnalyze.JsonDataGet_project_tree(responseData, list);
            showResponse();
        }).start();

        return view;
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