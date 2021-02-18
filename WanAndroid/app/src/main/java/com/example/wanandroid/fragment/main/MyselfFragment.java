package com.example.wanandroid.fragment.main;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wanandroid.R;
import com.example.wanandroid.activitise.function.CoinActivity;
import com.example.wanandroid.activitise.function.MyCollectActivity;

public class MyselfFragment extends Fragment {
    private View view;
    private TextView textView_myCollect;
    private TextView textView_myCoin;
    private TextView textView_about;
    private TextView textView_logOff;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_myself, container, false);
        textView_about = view.findViewById(R.id.about_bt);
        textView_logOff = view.findViewById(R.id.log_off_bt);
        textView_myCoin= view.findViewById(R.id.my_coin_bt);
        textView_myCollect = view.findViewById(R.id.my_collect_bt);

        textView_myCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyCollectActivity.class);
                startActivity(intent);
            }
        });

        textView_myCoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CoinActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}