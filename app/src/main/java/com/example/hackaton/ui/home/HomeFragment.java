package com.example.hackaton.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hackaton.IntentActivity;
import com.example.hackaton.R;
import com.example.hackaton.model.ExampleAdapter;
import com.example.hackaton.model.ExampleItem;


import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private HomeViewModel homeViewModel;
    private ArrayList<ExampleItem> mExampleList;
    private RecyclerView mRecyclerView;
    private ExampleAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        //------------------------>Новости<------------------------------------------//
        mRecyclerView = root.findViewById(R.id.RecView);
        createExampleList();
        buildRecyclerView();
        return root;
    }
    public void createExampleList() {
        mExampleList = new ArrayList<>();
        mExampleList.add(new ExampleItem(R.drawable.vault, "Line 1", "Line 2")); //Фоточка - заголовок - текст
        mExampleList.add(new ExampleItem(R.drawable.vault, "Line 3", "Line 4"));
        mExampleList.add(new ExampleItem(R.drawable.vault, "Line 5", "Line 6"));
        mExampleList.add(new ExampleItem(R.drawable.vault, "Line 7", "Line 8"));
        mExampleList.add(new ExampleItem(R.drawable.vault, "Line 9", "Line 10"));
        mExampleList.add(new ExampleItem(R.drawable.vault, "Line 11", "Line 12"));
        mExampleList.add(new ExampleItem(R.drawable.vault, "Line 13", "Line 14"));

    }
    public void buildRecyclerView() { //НЕ ТРОГАТЬ

        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mAdapter = new ExampleAdapter(mExampleList);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new ExampleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getContext(), IntentActivity.class);
                intent.putExtra("Example Item", mExampleList.get(position));
                startActivity(intent);
            }
        });
    }
}
