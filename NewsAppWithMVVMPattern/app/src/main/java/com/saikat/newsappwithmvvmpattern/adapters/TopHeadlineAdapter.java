package com.saikat.newsappwithmvvmpattern.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.saikat.newsappwithmvvmpattern.R;
import com.saikat.newsappwithmvvmpattern.models.Article;

import java.util.List;

public class TopHeadlineAdapter extends RecyclerView.Adapter<TopHeadlineAdapter.ViewHolder> {
    List<Article> list;
    Context context;

    public TopHeadlineAdapter(List<Article> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getUrlToImage()).into(holder.imageView);
        holder.title.setText(list.get(position).getTitle());
        holder.publishAt.setText(list.get(position).getPublishedAt());
        holder.description.setText(list.get(position).getDescription());
        holder.name.setText(list.get(position).getSource().getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title, publishAt, description, name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img);
            title = itemView.findViewById(R.id.title);
            publishAt = itemView.findViewById(R.id.publish_date);
            description = itemView.findViewById(R.id.des);
            name = itemView.findViewById(R.id.name);
        }
    }
}
