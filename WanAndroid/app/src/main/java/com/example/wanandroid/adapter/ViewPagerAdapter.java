package com.example.wanandroid.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;

import java.util.List;

public class ViewPagerAdapter extends PagerAdapter {
    private List<View> mViewList;

    public ViewPagerAdapter(List<View> mViewList) {
        this.mViewList = mViewList;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mViewList.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mViewList.get(position));
        return (mViewList.get(position));
    }

    @Override
    public int getCount() {
        if (mViewList == null)
            return 0;
        return mViewList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}

