package com.example.wanandroid;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewApater_web extends RecyclerView.Adapter<RecyclerViewApater_web.ViewHolder> {
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

    public RecyclerViewApater_web(List<WebData> mdata) {
        this.mdata = mdata;
    }

    @NonNull
    @Override
    public RecyclerViewApater_web.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_web_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                WebData webData = mdata.get(position);
                String link = webData.getLink();
                Log.e("mesge_link",link);

                Intent intent=new Intent(view.getContext(),web_activity.class);
                intent.putExtra("links",link);
                view.getContext().startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewApater_web.ViewHolder holder, int position) {
        WebData webData=mdata.get(position);
        holder.textView_name.setText(webData.getName());
        holder.textView_category.setText(webData.getCategory());
    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }


}
