package com.example.wanandroid.recyclerview;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.wanandroid.tools.GETConnection;
import com.example.wanandroid.tools.JsonAnalyze;
import com.example.wanandroid.activitise.function.KnowledgeActivity;
import com.example.wanandroid.adapter.KnowledgeTreeListAdapter;
import com.example.wanandroid.R;
import com.example.wanandroid.dataClass.TreeData;

import java.util.ArrayList;
import java.util.List;


public class KnowledgeTreeListFragment extends Fragment {
    private View view;
    private ListView listView;
    private ListView listView2;
    private List<TreeData> list = new ArrayList<>();
    private List<TreeData> list2 = new ArrayList<>();
    GETConnection get_connection = new GETConnection();
    JsonAnalyze jsonAnalyze = new JsonAnalyze();
    private String responseData;
    private ProgressBar progressBar;

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    KnowledgeTreeListAdapter adapter = new KnowledgeTreeListAdapter(getActivity(), R.layout.simple_list_item, list);
                    listView.setAdapter(adapter);
                    progressBar.setVisibility(View.GONE);
                    break;
                case 2:
                    KnowledgeTreeListAdapter adapter2 = new KnowledgeTreeListAdapter(getActivity(), R.layout.simple_list_item, list2);
                    listView2.setAdapter(adapter2);
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
        view = inflater.inflate(R.layout.fragment_knowledge_tree_list, container, false);
        listView = view.findViewById(R.id.tree_list);
        listView2 = view.findViewById(R.id.tree_item);
        progressBar = view.findViewById(R.id.ls_tree_bar);

            new Thread(() -> {
                responseData = get_connection.sendGetNetRequest("https://www.wanandroid.com/tree/json");
                if (responseData.equals("1")) {
                    showResponse(3);
                } else {
                    list.clear();
                    jsonAnalyze.JsonDataGet_knowledge_tree(responseData, list);
                    showResponse(1);
                }
            }).start();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TreeData treeData = list.get(position);
                String name = treeData.getName();
                list2.clear();
                jsonAnalyze.JsonDataGet_knowledge_tree_item(responseData, list2, name);
                showResponse(2);
            }
        });

        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TreeData treeData = list2.get(position);
                String name = treeData.getName();
                int iid = treeData.getId();

                Intent intent = new Intent(getActivity(), KnowledgeActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("id", iid);
                view.getContext().startActivity(intent);

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