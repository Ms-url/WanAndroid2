package com.example.wanandroid.fragment.main;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wanandroid.R;
import com.example.wanandroid.recyclerview.MyShareFragment;
import com.example.wanandroid.recyclerview.PublicSquareRecyclerViewFragment;

public class PublicSquareFragment extends Fragment {
    private View view;
    private TextView textView_square;
    private TextView textView_my_share;
    private LinearLayout linearLayout_1;
    private LinearLayout linearLayout_2;
    PublicSquareRecyclerViewFragment publicSquareRecyclerViewFragment = new PublicSquareRecyclerViewFragment();
    MyShareFragment myShareFragment = new MyShareFragment();

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    textView_square.setTextColor(Color.parseColor("#FF000000"));
                    break;
                case 2:
                    textView_my_share.setTextColor(Color.parseColor("#303030"));
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
        view = inflater.inflate(R.layout.fragment_public_square, container, false);
        textView_square = view.findViewById(R.id.public_square_button);
        textView_my_share = view.findViewById(R.id.my_share_button);
        linearLayout_1 = view.findViewById(R.id.public_square_button_bt);
        linearLayout_2 = view.findViewById(R.id.my_share_button_bt);

        linearLayout_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView_my_share.setTextColor(Color.parseColor("#7A7A7A"));
                showResponse(1);

                FragmentManager manager = getChildFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.public_square_frame_layout, publicSquareRecyclerViewFragment);
                transaction.commit();
            }
        });
        linearLayout_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView_square.setTextColor(Color.parseColor("#7A7A7A"));
                showResponse(2);
                FragmentManager manager = getChildFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.public_square_frame_layout,myShareFragment);
                transaction.commit();
            }
        });
        linearLayout_1.callOnClick();

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