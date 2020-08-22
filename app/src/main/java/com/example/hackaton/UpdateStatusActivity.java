package com.example.hackaton;


import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hackaton.ui.person.PersonFragment;


public class UpdateStatusActivity extends AppCompatActivity {


    String[] Teams = { "Brilliant", "Prumium", "Active", "Base"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.update_status_layout);
        Spinner spinner = findViewById(R.id.spinner2);
        spinner.setSelection(3);

    }
}
