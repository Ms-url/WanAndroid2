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
import com.example.wanandroid.dataClass.CoinData;
import com.example.wanandroid.dataClass.UsefulData;

import java.util.List;

public class MyCoinAdapter extends RecyclerView.Adapter<MyCoinAdapter.ViewHolder> {
    private List<CoinData> mdata;

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView_reason;
        TextView textView_desc;

        public ViewHolder(View view) {
            super(view);
            textView_desc = view.findViewById(R.id.my_coin_desc);
            textView_reason = view.findViewById(R.id.my_coin_reason);

        }
    }

    public MyCoinAdapter(List<CoinData> mdata) {
        this.mdata = mdata;
    }

    @NonNull
    @Override
    public MyCoinAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_coin_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyCoinAdapter.ViewHolder holder, int position) {
        CoinData usefulData = mdata.get(position);
        holder.textView_reason.setText(usefulData.getReason());
        holder.textView_desc.setText(usefulData.getDesc());
    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

}
