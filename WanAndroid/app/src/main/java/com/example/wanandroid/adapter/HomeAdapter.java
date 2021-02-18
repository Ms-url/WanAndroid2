package com.example.wanandroid.adapter;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wanandroid.R;
import com.example.wanandroid.dataClass.UsefulData;
import com.example.wanandroid.activitise.common.ShareUserActivity;
import com.example.wanandroid.activitise.common.WebActivity;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    private List<UsefulData> mdata;

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView_title;
        TextView textView_superChapterName;
        TextView textView_chapterName;
        TextView textView_niceTime;
        TextView textView_top;
        TextView textView_shareUser;
        TextView textView_author;

        public ViewHolder(View view) {
            super(view);
            textView_chapterName = view.findViewById(R.id.article_chapterName);
            textView_niceTime = view.findViewById(R.id.article_niceTime);
            textView_superChapterName = view.findViewById(R.id.article_superChapterName);
            textView_title = view.findViewById(R.id.article_title);
            textView_top = view.findViewById(R.id.top_article);
            textView_shareUser = view.findViewById(R.id.article_shareUser);
            textView_author = view.findViewById(R.id.article_ath);

        }
    }

    public HomeAdapter(List<UsefulData> mdata) {
        this.mdata = mdata;
    }

    @NonNull
    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);

        holder.textView_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                UsefulData usefulData = mdata.get(position);
                String link = usefulData.getLink();
                String title = usefulData.getTitle();
                int iid= usefulData.getId();
                Log.e("mesge_link",link);

                Intent intent=new Intent(view.getContext(), WebActivity.class);
                intent.putExtra("links",link);
                intent.putExtra("id",iid);
                intent.putExtra("title",title);
                view.getContext().startActivity(intent);
            }
        });

        holder.textView_author.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                UsefulData usefulData = mdata.get(position);
                int userId = usefulData.getUserId();
                if (userId == -1) {
                    Toast.makeText(v.getContext(), "非wanAndroid用户", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(view.getContext(), ShareUserActivity.class);
                    intent.putExtra("userId", userId);
                    view.getContext().startActivity(intent);
                }
            }
        });

        holder.textView_shareUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                UsefulData usefulData = mdata.get(position);
                int userId = usefulData.getUserId();
                if (userId == -1) {
                    Toast.makeText(v.getContext(), "非wanAndroid用户", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(view.getContext(), ShareUserActivity.class);
                    intent.putExtra("userId", userId);
                    view.getContext().startActivity(intent);
                }
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.ViewHolder holder, int position) {
        UsefulData usefulData = mdata.get(position);
        holder.textView_title.setText(usefulData.getTitle());
        holder.textView_superChapterName.setText(usefulData.getSuperChapterName());
        holder.textView_niceTime.setText(usefulData.getNiceDate());
        holder.textView_chapterName.setText(usefulData.getChapterName());
        holder.textView_top.setText(usefulData.getTop());
        holder.textView_shareUser.setText(usefulData.getShareUser());
        holder.textView_author.setText(usefulData.getAuthor());

    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

}
