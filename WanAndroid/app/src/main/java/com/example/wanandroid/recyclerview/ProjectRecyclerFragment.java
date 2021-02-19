package com.example.wanandroid.recyclerview;

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
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.wanandroid.tools.GETConnection;
import com.example.wanandroid.tools.JsonAnalyze;
import com.example.wanandroid.R;
import com.example.wanandroid.dataClass.TreeData;
import com.example.wanandroid.adapter.ProjectTreeAdapter2;

import java.util.ArrayList;
import java.util.List;

public class ProjectRecyclerFragment extends Fragment {
    private View view;
    private RecyclerView recyclerView;
    private List<TreeData> list = new ArrayList<>();
    private ProjectTreeAdapter2 dataAdapter = new ProjectTreeAdapter2(list);
    GETConnection get_connection = new GETConnection();
    JsonAnalyze jsonAnalyze = new JsonAnalyze();
    private String responseData;
    private ProgressBar progressBar;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(dataAdapter);
                    progressBar.setVisibility(View.GONE);
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
        view = inflater.inflate(R.layout.fragment_project_recycler, container, false);
        recyclerView = view.findViewById(R.id.Project_recycler);
        progressBar = view.findViewById(R.id.re_pr_bar);
        list.clear();

        new Thread(() -> {
            responseData = get_connection.sendGetNetRequest("https://www.wanandroid.com/project/tree/json");
            if (responseData.equals("1")) {
                showResponse(2);
            } else {
                jsonAnalyze.JsonDataGet_project_tree(responseData, list);
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