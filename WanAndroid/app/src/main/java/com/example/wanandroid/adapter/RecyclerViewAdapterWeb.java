package com.example.wanandroid.adapter;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wanandroid.R;
import com.example.wanandroid.dataClass.WebData;
import com.example.wanandroid.main_activitise.WebActivity;

import java.util.List;

public class RecyclerViewAdapterWeb extends RecyclerView.Adapter<RecyclerViewAdapterWeb.ViewHolder> {
    private List<WebData> mdata;

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView_name;
        TextView textView_category;
        LinearLayout linearLayout;

        public ViewHolder(View view) {
            super(view);
            textView_category = view.findViewById(R.id.web_item_category);
            textView_name = view.findViewById(R.id.web_item_name);
            linearLayout = view.findViewById(R.id.web_item_whole);
        }
    }

    public RecyclerViewAdapterWeb(List<WebData> mdata) {
        this.mdata = mdata;
    }

    @NonNull
    @Override
    public RecyclerViewAdapterWeb.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_web_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                WebData webData = mdata.get(position);
                String link = webData.getLink();
                Log.e("mesge_link",link);

                Intent intent=new Intent(view.getContext(), WebActivity.class);
                intent.putExtra("links",link);
                view.getContext().startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterWeb.ViewHolder holder, int position) {
        WebData webData=mdata.get(position);
        holder.textView_name.setText(webData.getName());
        holder.textView_category.setText(webData.getCategory());
    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }


}
