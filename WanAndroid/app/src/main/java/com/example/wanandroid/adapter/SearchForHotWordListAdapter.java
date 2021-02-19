package com.example.wanandroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.wanandroid.R;
import com.example.wanandroid.dataClass.TreeData;

import java.util.List;

public class SearchForHotWordListAdapter extends ArrayAdapter<TreeData> {
    private int srid;

    public SearchForHotWordListAdapter(Context context, int sid, List<TreeData> list){
        super(context,sid,list);
        srid = sid;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TreeData treeData = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(srid,parent,false);
        TextView textView = view.findViewById(R.id.system_name);
        textView.setText(treeData.getName());
        return view;
    }
}
