package com.example.wanandroid.activitise.function;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wanandroid.R;
import com.example.wanandroid.fragment.coin.CoinRankFragment;
import com.example.wanandroid.fragment.coin.MyCoinGetFragment;
import com.example.wanandroid.tools.GETConnection;
import com.example.wanandroid.tools.JsonAnalyze;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class CoinActivity extends AppCompatActivity {

    private ViewPager coin_content;
    TabLayout tabLayout;
    List<Fragment> fragmentList = new ArrayList<>();
    List<String> fragmentTitle = new ArrayList<>();
    private TextView textView_name;
    private TextView textView_level;
    private TextView textView_rank;
    private TextView textView_coinCount;
    GETConnection get_connection = new GETConnection();
    JsonAnalyze jsonAnalyze = new JsonAnalyze();
    private String responseData;
    private String userId;
    private List<String> list2 = new ArrayList<>();
    private List<Integer> list3 = new ArrayList<>();

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    textView_rank.setText(" 排名" + list2.get(1) + " ");
                    textView_name.setText(list2.get(0));
                    textView_coinCount.setText("积分：" + String.valueOf(list3.get(0)));
                    textView_level.setText(" Lv" + String.valueOf(list3.get(1)) + " ");
                    break;
                case 2:
                    Toast.makeText(CoinActivity.this, "请求超时", Toast.LENGTH_SHORT).show();
                    break;
                case 3:
                    textView_rank.setText(" 排名 0 ");
                    textView_coinCount.setText("积分：0");
                    textView_level.setText(" Lv 0 ");
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coin);

        coin_content = findViewById(R.id.coin_content);
        tabLayout = findViewById(R.id.tabs_coin);
        textView_coinCount = findViewById(R.id.co_coinCount);
        textView_level = findViewById(R.id.co_level);
        textView_name = findViewById(R.id.co_name);
        textView_rank = findViewById(R.id.co_rank);

        SharedPreferences user_da = getSharedPreferences("user_data", MODE_PRIVATE);
        userId = String.valueOf(user_da.getInt("user_id", 0));

        if (userId.equals("0")) {
            textView_name.setText("未登录");
            showResponse(3);
        } else {
            new Thread(() -> {
                responseData = get_connection.sendGetNetRequest("https://www.wanandroid.com/user/" + userId + "/share_articles/0/json");
                Log.e("Thread", "begin");
                if (responseData.equals("1")) {
                    showResponse(2);
                } else {
                    jsonAnalyze.JsonDataGet_shareUser_data(responseData, list2, list3);
                    showResponse(1);
                }
            }).start();
        }

        fragmentList.clear();
        fragmentTitle.clear();
        fragmentTitle.add("我的积分");
        fragmentTitle.add("积分排行");
        fragmentList.add(new MyCoinGetFragment());
        fragmentList.add(new CoinRankFragment());

        coin_content.setAdapter(new ViewPagerAdapterCoin(getSupportFragmentManager(),
                ViewPagerAdapterCoin.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT));
        tabLayout.setupWithViewPager(coin_content);
        coin_content.setOffscreenPageLimit(2);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    class ViewPagerAdapterCoin extends FragmentPagerAdapter {
        public ViewPagerAdapterCoin(FragmentManager fm, int behavior) {
            super(fm, behavior);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentTitle.get(position);
        }
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
