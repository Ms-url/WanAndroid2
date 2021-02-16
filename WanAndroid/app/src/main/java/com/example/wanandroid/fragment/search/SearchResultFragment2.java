package com.example.wanandroid.fragment.search;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wanandroid.R;
import com.example.wanandroid.adapter.SearchResultAdapter;
import com.example.wanandroid.dataClass.UsefulData;
import com.example.wanandroid.tools.JsonAnalyze;
import com.example.wanandroid.tools.POSTConnection;
import com.example.wanandroid.tools.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SearchResultFragment2 extends Fragment {
    View view;
    POSTConnection postConnection = new POSTConnection();
    private RecyclerView recyclerView;
    private List<UsefulData> list = new ArrayList<>();
    private SearchResultAdapter dataAdapter = new SearchResultAdapter(list);
    JsonAnalyze jsonAnalyze = new JsonAnalyze();
    private String responseData = null;
    HashMap<String, String> map = new HashMap<>();
    TextView textView;
    private String key;

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(dataAdapter);
                    textView.setText("搜索词：" + key);
                    Log.e("key3", map.get("k"));
                    Log.e("UIchange", "search hot key");
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
        view = inflater.inflate(R.layout.fragment_search_result_2, container, false);
        recyclerView = view.findViewById(R.id.search_result_v_2);
        recyclerView.addItemDecoration(new SpacesItemDecoration(14));
        textView = view.findViewById(R.id.search_key_2);

        list.clear();
        map.clear();
        key = getArguments().getString("k");
        Log.e("keypast",key);
        map.put("k",key);

        new Thread(() -> {
            responseData = postConnection.sendGetNetRequest("https://www.wanandroid.com/article/query/0/json", map);
            if (responseData.equals("1")) {
                showResponse(2);
            } else {
                jsonAnalyze.JsonDataGet_article(responseData, list);
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