package com.petits_raids.yunbiannews.support.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.petits_raids.yunbiannews.R;
import com.petits_raids.yunbiannews.data.model.guokr.GuokrItem;
import com.petits_raids.yunbiannews.ui.activity.GuokrArticleActivity;

import java.util.List;

public class GuokrAdapter extends RecyclerView.Adapter<GuokrAdapter.ViewHolder> {

    private List<GuokrItem> guokrItemList;
    private Context mContext;

    public GuokrAdapter(Context mContext, List<GuokrItem> guokrItemList) {
        this.guokrItemList = guokrItemList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_guokr, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.titleGuokr.setText(guokrItemList.get(position).getTitle());
        holder.summaryGuokr.setText(guokrItemList.get(position).getSummary());
        Glide.with(mContext)
                .load(guokrItemList.get(position).getSmall_image())
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.imageGuokr);
        holder.itemView.setOnClickListener(l -> {
            Intent intent = new Intent(mContext, GuokrArticleActivity.class);
            intent.putExtra("article_id", guokrItemList.get(position).getId());
            mContext.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return guokrItemList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleGuokr;
        TextView summaryGuokr;
        ImageView imageGuokr;

        ViewHolder(View view) {
            super(view);
            titleGuokr = view.findViewById(R.id.title_guokr);
            summaryGuokr = view.findViewById(R.id.summary_guokr);
            imageGuokr = view.findViewById(R.id.image_guokr);
        }
    }

}
