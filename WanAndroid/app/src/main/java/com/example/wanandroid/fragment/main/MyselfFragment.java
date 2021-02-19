package com.example.wanandroid.fragment.main;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wanandroid.R;
import com.example.wanandroid.activitise.function.AboutActivity;
import com.example.wanandroid.activitise.function.CoinActivity;
import com.example.wanandroid.activitise.function.MyCollectActivity;

import static android.content.Context.MODE_PRIVATE;

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

        textView_logOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor save_data = getActivity().getSharedPreferences("user_data", MODE_PRIVATE).edit();
                SharedPreferences.Editor cookie_data = getActivity().getSharedPreferences("cook_data", MODE_PRIVATE).edit();

                AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                dialog.setTitle("Warning");
                dialog.setMessage("你确定要退出登录吗？");
                dialog.setCancelable(false);
                dialog.setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        save_data.clear();
                        cookie_data.clear();
                        save_data.apply();
                        cookie_data.apply();
                        Toast.makeText(getActivity(),"已退出登录",Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.setNegativeButton("否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.show();
            }
        });

        textView_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AboutActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}