package com.example.wanandroid.adapter;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wanandroid.R;
import com.example.wanandroid.activitise.common.ShareUserActivity;
import com.example.wanandroid.activitise.common.WebActivity;
import com.example.wanandroid.dataClass.CollectData;
import com.example.wanandroid.dataClass.UsefulData;

import java.util.List;

public class CollectArticleAdapter extends RecyclerView.Adapter<CollectArticleAdapter.ViewHolder> {
    private List<CollectData> mdata;

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView_title;
        TextView textView_chapterName;
        TextView textView_niceTime;
        ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            textView_chapterName = view.findViewById(R.id.collect_article_chapterName);
            textView_niceTime = view.findViewById(R.id.collect_article_niceTime);
            textView_title = view.findViewById(R.id.collect_article_title);
            imageView = view.findViewById(R.id.collect_like);

        }
    }

    public CollectArticleAdapter(List<CollectData> mdata) {
        this.mdata = mdata;
    }

    @NonNull
    @Override
    public CollectArticleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.re_collect_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);

        holder.textView_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                CollectData usefulData = mdata.get(position);
                String link = usefulData.getLink();
                String title = usefulData.getTitle();
                int iid = usefulData.getId();
                Log.e("mesge_link", link);

                Intent intent = new Intent(view.getContext(), WebActivity.class);
                intent.putExtra("links", link);
                intent.putExtra("id", iid);
                intent.putExtra("title", title);
                view.getContext().startActivity(intent);
            }
        });

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //取消收藏
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CollectArticleAdapter.ViewHolder holder, int position) {
        CollectData usefulData = mdata.get(position);
        holder.textView_title.setText(usefulData.getTitle());
        holder.textView_niceTime.setText(usefulData.getNiceDate());
        holder.textView_chapterName.setText(usefulData.getChapterName());

    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

}
