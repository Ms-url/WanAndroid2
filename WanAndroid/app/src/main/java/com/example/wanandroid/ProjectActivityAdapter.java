package com.example.wanandroid;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ProjectActivityAdapter extends RecyclerView.Adapter<ProjectActivityAdapter.ViewHolder> {
    private List<UsefulData> mdata;
    private Context mcontext;

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView_title;
        TextView textView_superChapterName;
        TextView textView_chapterName;
        TextView textView_desc;
        TextView textView_github_link;
        ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.p_image);
            textView_chapterName = view.findViewById(R.id.p_chapterName);
            textView_desc = view.findViewById(R.id.p_desc);
            textView_superChapterName = view.findViewById(R.id.p_superChapterName);
            textView_title = view.findViewById(R.id.p_title);
            textView_github_link = view.findViewById(R.id.p_github_link);
        }
    }

    public ProjectActivityAdapter(List<UsefulData> mdata) {
        this.mdata = mdata;
    }

    @NonNull
    @Override
    public ProjectActivityAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (mcontext == null) {
            mcontext = parent.getContext();
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_project_at_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);

        holder.textView_desc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                UsefulData usefulData = mdata.get(position);
                Boolean isExpend = usefulData.getaBoolean();
                if (isExpend) {
                    holder.textView_desc.setMinLines(0);
                    holder.textView_desc.setMaxLines(Integer.MAX_VALUE);
                    usefulData.setaBoolean(false);
                } else {
                    holder.textView_desc.setLines(2);
                    usefulData.setaBoolean(true);
                }
            }
        });

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                UsefulData usefulData = mdata.get(position);
                int id = usefulData.getId();
                String link = usefulData.getLink();

                Intent intent=new Intent(view.getContext(),web_activity.class);
                intent.putExtra("id",id);
                intent.putExtra("links",link);
                view.getContext().startActivity(intent);
            }
        });
        holder.textView_github_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                UsefulData usefulData = mdata.get(position);
                int id = usefulData.getId();
                String link = usefulData.getProjectLink();

                Intent intent=new Intent(view.getContext(),web_activity.class);
                intent.putExtra("id",id);
                intent.putExtra("links",link);
                view.getContext().startActivity(intent);
            }
        });
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull ProjectActivityAdapter.ViewHolder holder, int position) {
        UsefulData usefulData = mdata.get(position);
        holder.textView_title.setText(usefulData.getTitle());
        holder.textView_superChapterName.setText(usefulData.getSuperChapterName());
        ;
        holder.textView_desc.setText(usefulData.getDesc());
        holder.textView_chapterName.setText(usefulData.getChapterName());
        Glide.with(mcontext).load(usefulData.getEnvelopePic()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }


}
