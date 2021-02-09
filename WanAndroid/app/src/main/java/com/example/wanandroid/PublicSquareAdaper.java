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

public class PublicSquareAdaper extends RecyclerView.Adapter<PublicSquareAdaper.ViewHolder>{
    private List<UsefulData> mdata;

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView_title;
        TextView textView_chapterName;
        TextView textView_niceTime;
        TextView textView_shareUser;

        public ViewHolder(View view) {
            super(view);
            textView_chapterName = view.findViewById(R.id.s_article_chapterName);
            textView_niceTime = view.findViewById(R.id.s_article_niceTime);
            textView_title = view.findViewById(R.id.s_article_title);
            textView_shareUser = view.findViewById(R.id.s_shareUser);

        }
    }

    public PublicSquareAdaper(List<UsefulData> mdata) {
        this.mdata = mdata;
    }

    @NonNull
    @Override
    public PublicSquareAdaper.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_square_item, parent, false);
        final PublicSquareAdaper.ViewHolder holder = new PublicSquareAdaper.ViewHolder(view);

        holder.textView_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                UsefulData usefulData = mdata.get(position);
                String link = usefulData.getLink();
                String title = usefulData.getTitle();
                int iid= usefulData.getId();
                Log.e("item_link",link);

                Intent intent=new Intent(view.getContext(),web_activity.class);
                intent.putExtra("links",link);
                intent.putExtra("id",iid);
                intent.putExtra("title",title);
                view.getContext().startActivity(intent);
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PublicSquareAdaper.ViewHolder holder, int position) {
        UsefulData usefulData = mdata.get(position);
        holder.textView_title.setText(usefulData.getTitle());
        holder.textView_niceTime.setText(usefulData.getNiceDate());
        holder.textView_chapterName.setText(usefulData.getSuperChapterName());
        holder.textView_shareUser.setText(usefulData.getShareUser());

    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

}
