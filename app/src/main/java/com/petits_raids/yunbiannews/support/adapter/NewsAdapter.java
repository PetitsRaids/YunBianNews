package com.petits_raids.yunbiannews.support.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.petits_raids.yunbiannews.R;
import com.petits_raids.yunbiannews.data.model.news.News;
import com.petits_raids.yunbiannews.ui.activity.WebViewActivity;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private Context mContext;
    private List<News> newsList;

    public NewsAdapter(Context mContext, List<News> newsList) {
        this.mContext = mContext;
        this.newsList = newsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_news, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.newsTitle.setText(newsList.get(position).getTitle());
        if (newsList.get(position).getDescription() != null)
            holder.newsContent.setText(newsList.get(position).getDescription());
        else
            holder.newsContent.setText(R.string.without_description);
        holder.newsTime.setText(newsList.get(position).getTime());
        holder.itemView.setOnClickListener(l -> {
            Intent intent = new Intent(mContext, WebViewActivity.class);
            intent.putExtra("url", newsList.get(position).getLink());
            mContext.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView newsTitle;
        TextView newsContent;
        TextView newsTime;

        ViewHolder(View view) {
            super(view);
            newsTitle = view.findViewById(R.id.title_news);
            newsContent = view.findViewById(R.id.content_news);
            newsTime = view.findViewById(R.id.time_news);
        }
    }

}
