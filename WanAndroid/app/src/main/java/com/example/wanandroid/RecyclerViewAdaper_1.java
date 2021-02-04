package com.example.wanandroid;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdaper_1 extends RecyclerView.Adapter<RecyclerViewAdaper_1.ViewHolder> {
    private List<UsefulData> mdata;

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView_title;
        TextView textView_superChapterName;
        TextView textView_chapterName;
        TextView textView_niceTime;
        TextView textView_top;

        public ViewHolder(View view) {
            super(view);
            textView_chapterName = view.findViewById(R.id.article_chapterName);
            textView_niceTime = view.findViewById(R.id.article_niceTime);
            textView_superChapterName = view.findViewById(R.id.article_superChapterName);
            textView_title = view.findViewById(R.id.article_title);
            textView_top = view.findViewById(R.id.top_article);

        }
    }

    public RecyclerViewAdaper_1(List<UsefulData> mdata) {
        this.mdata = mdata;
    }

    @NonNull
    @Override
    public RecyclerViewAdaper_1.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);

        holder.textView_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                UsefulData usefulData = mdata.get(position);
                String link = usefulData.getLink();
                Log.e("mesge_link",link);

                Intent intent=new Intent(view.getContext(),web_activity.class);
                intent.putExtra("links",link);
                view.getContext().startActivity(intent);
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdaper_1.ViewHolder holder, int position) {
        UsefulData usefulData = mdata.get(position);
        holder.textView_title.setText(usefulData.getTitle());
        holder.textView_superChapterName.setText(usefulData.getSuperChapterName());
        holder.textView_niceTime.setText(usefulData.getNiceDate());
        holder.textView_chapterName.setText(usefulData.getChapterName());
        holder.textView_top.setText(usefulData.getTop());

    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

}
