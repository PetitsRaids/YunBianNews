package com.petits_raids.yunbiannews.ui.fragment.guokr;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.petits_raids.yunbiannews.R;
import com.petits_raids.yunbiannews.data.model.guokr.GuokrItem;
import com.petits_raids.yunbiannews.support.adapter.GuokrAdapter;
import com.petits_raids.yunbiannews.viewmodel.GuokrItemViewModel;

import java.util.ArrayList;
import java.util.List;

public class GuokrFragment extends Fragment {

    private SwipeRefreshLayout refreshLayout;
    private List<GuokrItem> guokrItemList = new ArrayList<>();
    private GuokrAdapter adapter;
    private boolean isFirst = true;
    private int guokrType;
    private GuokrItemViewModel guokrItemViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_guokr, container, false);

        RecyclerView guokrRecyclerView = view.findViewById(R.id.guokr_recycler_view);
        adapter = new GuokrAdapter(getContext(), guokrItemList);
        guokrRecyclerView.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(RecyclerView.VERTICAL);
        guokrRecyclerView.setLayoutManager(manager);
        DividerItemDecoration divider = new DividerItemDecoration(getContext(), manager.getOrientation());
        guokrRecyclerView.addItemDecoration(divider);

        if (getArguments() != null) {
            guokrType = getArguments().getInt("type");
        }
        guokrItemViewModel = ViewModelProviders.of(this).get(GuokrItemViewModel.class);
        guokrItemViewModel.setType(guokrType);
        guokrItemViewModel.liveGuokrItemList.observe(this, guokrItems -> {
            guokrItemList.clear();
            guokrItemList.addAll(guokrItems);
            adapter.notifyDataSetChanged();
            guokrRecyclerView.smoothScrollToPosition(0);
            if (isFirst)
                return;
            refreshLayout.setRefreshing(false);
            Toast.makeText(getContext(), R.string.data_updated, Toast.LENGTH_SHORT).show();
        });

        refreshLayout = view.findViewById(R.id.guokr_swipe_refresh);
        refreshLayout.setColorSchemeResources(R.color.colorAccent);
        refreshLayout.setOnRefreshListener(this::refreshData);
        return view;
    }

    void refreshData() {
        guokrItemViewModel.updateGuokrItem();
        refreshLayout.setRefreshing(true);
        isFirst = false;
    }

}
