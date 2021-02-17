package com.example.wanandroid.fragment.search;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.wanandroid.R;
import com.example.wanandroid.adapter.KnowledgeTreeListAdapter;
import com.example.wanandroid.dataClass.TreeData;
import com.example.wanandroid.activitise.function.SearchActivity;
import com.example.wanandroid.tools.GETConnection;
import com.example.wanandroid.tools.JsonAnalyze;

import java.util.ArrayList;
import java.util.List;


public class SearchForHotWordFragment extends Fragment {
    View view;
    private ListView listView;
    private List<TreeData> list = new ArrayList<>();
    GETConnection get_connection = new GETConnection();
    JsonAnalyze jsonAnalyze = new JsonAnalyze();
    private String responseData;
    SearchResultFragment1 searchResultFragment1 = new SearchResultFragment1();
    SearchActivity searchActivity = (SearchActivity) getActivity();
    private String name;

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    KnowledgeTreeListAdapter adapter = new KnowledgeTreeListAdapter(getActivity(), R.layout.simple_list_item, list);
                    listView.setAdapter(adapter);
                    break;
                case 2:
                //    searchActivity.Transaction(name);
                    break;
                case 3:
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
        view = inflater.inflate(R.layout.fragment_search_for_hot_word, container, false);
        listView = view.findViewById(R.id.search_for_hot_list);
        new Thread(() -> {
            responseData = get_connection.sendGetNetRequest("https://www.wanandroid.com/hotkey/json");
            if (responseData.equals("1")) {
                showResponse(3);
            } else {
                list.clear();
                jsonAnalyze.JsonDataGet_hotkey(responseData, list);
                showResponse(1);
            }
        }).start();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TreeData treeData = list.get(position);
                name = treeData.getName();
                showResponse(2);
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