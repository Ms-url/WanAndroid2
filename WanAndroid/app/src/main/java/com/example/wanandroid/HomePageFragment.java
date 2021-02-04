package com.example.wanandroid;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class HomePageFragment extends Fragment {
    private View view;
    private ViewPager home_banner;
    private ViewPager home_content;
    private List<View> list=new ArrayList<>();
    private view_PagerAdapter viewpageradapter=new view_PagerAdapter(list);
    TabLayout tabLayout;
    List<Fragment> fragmentList=new ArrayList<>();
    List<String> fragmentTitle=new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home_page, container, false);
        home_banner = view.findViewById(R.id.home_banner);
        home_content = view.findViewById(R.id.home_content);
        tabLayout=view.findViewById(R.id.tabs_1);

        list.add(LayoutInflater.from(getContext()).inflate(R.layout.view_pager_item_1,null,false));
        list.add(LayoutInflater.from(getContext()).inflate(R.layout.view_pager_item_2,null,false));
        home_banner.setAdapter(viewpageradapter);

        fragmentList.clear();
        fragmentTitle.clear();
        fragmentTitle.add("文章");
        fragmentTitle.add("常用网站");
        fragmentList.add(new RecyclerViewFragment());
        fragmentList.add(new RecyclerViewFragment_web());

        home_content.setAdapter(new ViewPagerAdapter(getChildFragmentManager(),
                ViewPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)) ;
        tabLayout.setupWithViewPager(home_content);
        home_content.setOffscreenPageLimit(2);

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
        return view;
    }


    class ViewPagerAdapter extends FragmentPagerAdapter {
        public ViewPagerAdapter(FragmentManager fm, int behavior) {
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