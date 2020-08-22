package com.example.hackaton.ui.person;

import android.app.AlertDialog;
import android.app.Person;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ImageWriter;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.hackaton.LogInActivity;
import com.example.hackaton.MapActivity;
import com.example.hackaton.R;
import com.example.hackaton.model.User;
import com.example.hackaton.ui.person.PersonViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.io.IOException;


public class PersonFragment extends Fragment {
    private PersonViewModel PersonViewModel;
    static final int GALLERY_REQUEST = 1;
    ImageView chsImg;
    private FirebaseAuth auth;
    String playerName= "";
    FirebaseUser firebaseUser;
    DatabaseReference reference;
    private SharedPreferences mSettings;
    public static final String APP_PREFERENCES = "mysettings";
    public static final String APP_PREFERENCES_PASS="pass";
    String[] Teams = { "ФК Сочи", "Легион Цезаря", "Братство Стали", "Подрывники", "Ван Мурлегемы",
            "НКР", "Муты", "Шаурма" };
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        PersonViewModel =
                ViewModelProviders.of(this).get(PersonViewModel.class);
        View root = inflater.inflate(R.layout.fragment_person
                , container, false);
        auth = FirebaseAuth.getInstance();
        //-------------------------------------->Сохранить настройки<------------------------------//
        final Button chngAcc = (Button) root.findViewById(R.id.chngAcc);
        final Button saveBtn = (Button) root.findViewById(R.id.saveChanges);
        final Button loadImg = (Button) root.findViewById(R.id.loadImg);
        // кнопка для alertdialog и повышения статуса
        final Button statusBut = (Button) root.findViewById(R.id.statusBut);
        final Button upStatusBut = (Button) root.findViewById(R.id.upStatusBut);
        final Button geoBut = (Button) root.findViewById(R.id.geoBut);
        //
        final EditText inputnick = root.findViewById(R.id.inputNick);
        chsImg = root.findViewById(R.id.chooseImage);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (saveBtn.getText().toString().equals("Сохранить")) {
                    loadImg.setVisibility(View.INVISIBLE);
                    inputnick.setEnabled(false);
                    saveBtn.setText("Редактировать");
                } else {
                    loadImg.setVisibility(View.VISIBLE);
                    inputnick.setEnabled(true);
                    saveBtn.setText("Сохранить");
                }
            }
        });
        chngAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.signOut();
                startActivity(new Intent(getActivity(), LogInActivity.class));
            }
        });

        loadImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, GALLERY_REQUEST);

            }
        });
        //
        statusBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder deleteDialog = new AlertDialog.Builder(getActivity());
                deleteDialog.setTitle("Вы действительно хотите удалить выбранные ингредиенты?");
                deleteDialog.setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
            }
        });
        upStatusBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), LogInActivity.class));
            }
        });
        geoBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), MapActivity.class));
            }
        });


        //------------------->Имя с базы<---------------------------------------//
        firebaseUser = auth.getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user=dataSnapshot.getValue(User.class);
                //Toast.makeText(getContext().getApplicationContext(), ""+user.getUsername(), Toast.LENGTH_SHORT).show();
                inputnick.setText(user.getUsername());
                if (user.getImageURL().equals("default")){
                    chsImg.setImageResource(R.drawable.vault);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        //---------------->Спиннер<-----------------------------------------//

        final Spinner spinner = root.findViewById(R.id.spinner);
        MyCustomAdapter adapter = new MyCustomAdapter(getContext(),
                R.layout.row, Teams);
        spinner.setAdapter(adapter);
        spinner.setSelection(1);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {}
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {}
        });

        return root;
    }
//-------------------------------->Spinner<------------------------------------//
public class MyCustomAdapter extends ArrayAdapter<String> {

    public MyCustomAdapter(Context context, int textViewResourceId,
                           String[] objects) {
        super(context, textViewResourceId, objects);
    }

    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {

        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        return getCustomView(position, convertView, parent);
    }

    public View getCustomView(int position, View convertView,
                              ViewGroup parent) {

        LayoutInflater inflater = getLayoutInflater();
        View row = inflater.inflate(R.layout.row, parent, false);
        TextView label = (TextView) row.findViewById(R.id.teams);
        label.setText(Teams[position]);

        ImageView icon = (ImageView) row.findViewById(R.id.icon);

        if (Teams[position] == "Подрывники") {
            icon.setImageResource(R.drawable.evil_karma);
        } else {
            //icon.setImageResource(R.drawable.vault);
        }
        return row;
    }
}

//--------------------------->Фото<----------------------------------//
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        Bitmap bitmap = null;
        switch(requestCode) {
            case GALLERY_REQUEST:
                Toast.makeText(getContext().getApplicationContext(), ""+resultCode, Toast.LENGTH_SHORT).show();
                if(resultCode == -1){
                    Uri selectedImage = imageReturnedIntent.getData();
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), selectedImage);
                        Toast.makeText(getContext().getApplicationContext(), "Я попытался присвоить битмапу", Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    chsImg.setImageBitmap(bitmap);
                    chsImg.setImageURI(selectedImage);
                }
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        PersonViewModel.setImage(chsImg.getDrawable());

    }
}