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
        mExampleList.add(new ExampleItem(R.drawable.fcb, "Футбольный клуб барселона", "Барселона – Бавария\n" +
                "Футбол, Лига чемпионов, 1/4\n" +
                "14 августа, 22:00, Завершён")); //Фоточка - заголовок - текст
        mExampleList.add(new ExampleItem(R.drawable.bal, "Сочи – Ростов", "Lorem Ipsum - это текст-\"рыба\", часто используемый в печати и вэб-дизайне. Lorem Ipsum является стандартной \"рыбой\" для текстов на латинице с начала"));
        mExampleList.add(new ExampleItem(R.drawable.shirt, "Спартак – Зенит", "Lorem Ipsum - это текст-\"рыба\", часто используемый в печати и вэб-дизайне. Lorem Ipsum является стандартной \"рыбой\" для текстов на латинице с начала"));
        mExampleList.add(new ExampleItem(R.drawable.sosser, "Сочи готовит Барселону","Lorem Ipsum - это текст часто используемый в печати и вэб-дизайне Lorem Ipsum является стандартной рыбой для текстов на латинице с начала"));
        mExampleList.add(new ExampleItem(R.drawable.strat, "Спартак – Зенит", "Lorem Ipsum - это текст-\"рыба\", часто используемый в печати и вэб-дизайне. Lorem Ipsum является стандартной \"рыбой\" для текстов на латинице с начала"));
        mExampleList.add(new ExampleItem(R.drawable.flatc, "Флатикон", "Флатикон, это сервис на котором мы берем иконки"));
        mExampleList.add(new ExampleItem(R.drawable.vault, "Сочи", "Lorem Ipsum - это текст-\"рыба\", часто используемый в печати и вэб-дизайне. Lorem Ipsum является стандартной \"рыбой\" для текстов на латинице с начала"));

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
