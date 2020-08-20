package com.example.hackaton.ui.person;

import android.app.Person;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.hackaton.R;
import com.example.hackaton.ui.person.PersonViewModel;

import java.io.IOException;


public class PersonFragment extends Fragment {
    private PersonViewModel PersonViewModel;
    static final int GALLERY_REQUEST = 1;
    private int RESULT_OK=1;
    ImageView chsImg;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        PersonViewModel =
                ViewModelProviders.of(this).get(PersonViewModel.class);
        View root = inflater.inflate(R.layout.fragment_person
                , container, false);
        //-----------------------------------------------------------------

        final Button saveBtn = (Button) root.findViewById(R.id.saveChanges);
        final Button loadImg = (Button) root.findViewById(R.id.loadImg);
        final EditText inputnick = root.findViewById(R.id.inputNick);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (saveBtn.getText().toString().equals("Сохранить")) {
                    inputnick.setEnabled(false);
                    saveBtn.setText("Редактировать");
                } else {
                    inputnick.setEnabled(true);
                    saveBtn.setText("Сохранить");
                }
            }
        });

        chsImg = (ImageView) root.findViewById(R.id.chooseImage);
        loadImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, GALLERY_REQUEST);
            }
        });


        return root;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        Bitmap bitmap = null;

        switch(requestCode) {
            case GALLERY_REQUEST:
                if(resultCode == RESULT_OK){
                    Uri selectedImage = imageReturnedIntent.getData();
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectedImage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    chsImg.setImageBitmap(bitmap);
                }
        }
    }

}