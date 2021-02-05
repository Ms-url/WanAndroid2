package com.example.wanandroid;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class KnowledgeHierarchyFragment extends Fragment {

    private View view;
    private ViewPager knowledge;
    List<Fragment> fragmentList=new ArrayList<>();
    List<String> fragmentTitle=new ArrayList<>();
    TabLayout tabLayout;
    KnowledgeTreeListFragment knowledgeTreeListFragment = new KnowledgeTreeListFragment();
    ProjectRecyclerFragment projectRecyclerFragment = new ProjectRecyclerFragment();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_knowledge_hierarchy, container, false);
        knowledge=view.findViewById(R.id.knowledge_hierarchy_content);
        tabLayout= view.findViewById(R.id.tabs_2);

        fragmentList.clear();
        fragmentTitle.clear();
        fragmentTitle.add("体系");
        fragmentTitle.add("项目");
        fragmentList.add(knowledgeTreeListFragment);
        fragmentList.add(projectRecyclerFragment);

        knowledge.setAdapter(new ViewPagerAdapter(getChildFragmentManager(),
                ViewPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)) ;
        tabLayout.setupWithViewPager(knowledge);
        knowledge.setOffscreenPageLimit(2);

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