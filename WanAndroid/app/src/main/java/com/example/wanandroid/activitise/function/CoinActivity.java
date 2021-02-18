package com.example.wanandroid.activitise.function;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.wanandroid.R;
import com.example.wanandroid.fragment.coin.MyCoinGetFragment;
import com.example.wanandroid.recyclerview.RecyclerViewFragmentWeb;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class CoinActivity extends AppCompatActivity {

    private ViewPager coin_content;
    TabLayout tabLayout;
    List<Fragment> fragmentList = new ArrayList<>();
    List<String> fragmentTitle = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coin);

        coin_content = findViewById(R.id.coin_content);
        tabLayout = findViewById(R.id.tabs_coin);


        fragmentList.clear();
        fragmentTitle.clear();
        fragmentTitle.add("我的积分");
        fragmentTitle.add("积分排行");
        fragmentList.add(new MyCoinGetFragment());
        fragmentList.add(new RecyclerViewFragmentWeb());

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

}