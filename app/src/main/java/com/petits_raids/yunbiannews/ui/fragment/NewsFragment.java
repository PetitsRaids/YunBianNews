package com.petits_raids.yunbiannews.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.petits_raids.yunbiannews.R;
import com.petits_raids.yunbiannews.data.model.News;
import com.petits_raids.yunbiannews.support.adapter.NewsAdapter;
import com.petits_raids.yunbiannews.viewmodel.NewsViewModel;

import java.util.ArrayList;
import java.util.List;

public class NewsFragment extends Fragment {

//    private static final String TAG = "NewsFragment";

    private NewsViewModel newsViewModel;
    private NewsAdapter adapter;
    private List<News> newsList = new ArrayList<>();
    private SwipeRefreshLayout swipeRefresh;
    private boolean isRefreshing;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.news_recycler_view);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(manager);
        DividerItemDecoration decoration =
                new DividerItemDecoration(getContext(), manager.getOrientation());
        recyclerView.addItemDecoration(decoration);

        newsViewModel = ViewModelProviders.of(this).get(NewsViewModel.class);
        Observer<List<News>> observable = newsList1 -> {
            newsList.clear();
            if (newsList1 != null) {
                newsList.addAll(newsList1);
            }
            if (adapter == null) {
                adapter = new NewsAdapter(getContext(), newsList);
                recyclerView.setAdapter(adapter);
            } else {
                adapter.notifyDataSetChanged();
            }
            if (isRefreshing) {
                swipeRefresh.setRefreshing(false);
                isRefreshing = false;
                Toast.makeText(getContext(), R.string.data_updated, Toast.LENGTH_SHORT).show();
            }
        };
        newsViewModel.liveNewsList.observe(this, observable);

        swipeRefresh = view.findViewById(R.id.swipe_refresh);
        swipeRefresh.setColorSchemeResources(R.color.colorAccent);
        swipeRefresh.setOnRefreshListener(() -> {
            newsViewModel.updateNews();
            isRefreshing = true;
            swipeRefresh.setRefreshing(true);
        });
        return view;
    }
}
