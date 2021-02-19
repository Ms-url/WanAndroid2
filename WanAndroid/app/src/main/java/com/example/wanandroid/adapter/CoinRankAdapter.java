package com.example.wanandroid.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wanandroid.R;
import com.example.wanandroid.dataClass.CoinData;

import java.util.List;

public class CoinRankAdapter extends RecyclerView.Adapter<CoinRankAdapter.ViewHolder> {
    private List<CoinData> mdata;

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView_rank;
        TextView textView_name;
        TextView textView_coin;
        TextView textView_level;

        public ViewHolder(View view) {
            super(view);
            textView_rank = view.findViewById(R.id.c_r_rank);
            textView_name = view.findViewById(R.id.c_r_name);
            textView_coin = view.findViewById(R.id.c_r_coin);
            textView_level = view.findViewById(R.id.c_r_level);

        }
    }

    public CoinRankAdapter(List<CoinData> mdata) {
        this.mdata = mdata;
    }

    @NonNull
    @Override
    public CoinRankAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.coin_rank_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CoinRankAdapter.ViewHolder holder, int position) {
        CoinData usefulData = mdata.get(position);
        holder.textView_rank.setText(usefulData.getRank());
        holder.textView_name.setText(usefulData.getUsername());
        holder.textView_coin.setText(usefulData.getCoinCount());
        holder.textView_level.setText("Lv"+usefulData.getLevel());

    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

}
