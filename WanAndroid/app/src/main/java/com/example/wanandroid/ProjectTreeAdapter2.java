package com.example.wanandroid;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProjectTreeAdapter2 extends RecyclerView.Adapter<ProjectTreeAdapter2.ViewHolder> {
    private List<ProjectTreeData> mdata;

    static class ViewHolder extends RecyclerView.ViewHolder {
        Button bt_Name;


        public ViewHolder(View view) {
            super(view);
            bt_Name = view.findViewById(R.id.project_tree_name);
        }
    }

    public ProjectTreeAdapter2(List<ProjectTreeData> mdata) {
        this.mdata = mdata;
    }

    @NonNull
    @Override
    public ProjectTreeAdapter2.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_project_tree_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.bt_Name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                ProjectTreeData projectTreeData=mdata.get(position);
                String name = projectTreeData.getName();
                int id = projectTreeData.getId();

               /* Intent intent=new Intent(view.getContext(),    );
                intent.putExtra("id",id);
                intent.putExtra("name",name);
                view.getContext().startActivity(intent);*/
            }
        });


        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectTreeAdapter2.ViewHolder holder, int position) {
        ProjectTreeData projectTreeData = mdata.get(position);
        holder.bt_Name.setText(projectTreeData.getName());

    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }


}
