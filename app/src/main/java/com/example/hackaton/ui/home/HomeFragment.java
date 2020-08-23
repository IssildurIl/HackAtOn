package com.example.hackaton.ui.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private HomeViewModel homeViewModel;
    private ArrayList<ExampleItem> mExampleList;
    private RecyclerView mRecyclerView;
    private ExampleAdapter mAdapter;
    static final int GALLERY_REQUEST = 1;
    private RecyclerView.LayoutManager mLayoutManager;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final View root1 = inflater.inflate(R.layout.cust_dio_2, container, false);
        //------------------------>Новости<------------------------------------------//
        mRecyclerView = root.findViewById(R.id.RecView);
        Button crtnws= root.findViewById(R.id.addnews);
        createExampleList();
        buildRecyclerView();




        crtnws.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                //Получаем вид с файла prompt.xml, который применим для диалогового окна:
                LayoutInflater li = LayoutInflater.from(getContext());
                View promptsView = li.inflate(R.layout.cust_dio_2, null);
                //Создаем AlertDialog
                AlertDialog.Builder mDialogBuilder = new AlertDialog.Builder(getContext());

                //Настраиваем prompt.xml для нашего AlertDialog:
                mDialogBuilder.setView(promptsView);

                //Настраиваем отображение поля для ввода текста в открытом диалоге:
                final EditText titleInput = (EditText) promptsView.findViewById(R.id.title_new);
                final EditText bodyInput = (EditText) promptsView.findViewById(R.id.body_new);
                final String[] tI = new String[2];
                //Настраиваем сообщение в диалоговом окне:
                mDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        tI[0] = String.valueOf(titleInput.getText());
                                        tI[1] =String.valueOf(bodyInput.getText());

                                        mExampleList.add(new ExampleItem(R.drawable.vault,tI[0],tI[1]));
                                        mAdapter.notifyItemInserted(8);
                                        buildRecyclerView();
                                    }
                                })
                        .setNegativeButton("Отмена",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        dialog.cancel();
                                    }
                                });

                //Создаем AlertDialog:
                AlertDialog alertDialog = mDialogBuilder.create();

                //и отображаем его:
                alertDialog.show();

            }
        });










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
