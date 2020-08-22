package com.example.hackaton;


import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hackaton.ui.person.PersonFragment;


public class UpdateStatusActivity extends AppCompatActivity {


    String[] Teams = { "Brilliant", "Prumium", "Active", "Base"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.update_status_layout);

        final Spinner spinner = findViewById(R.id.spinner2);
        //PersonFragment.MyCustomAdapter adapter = new PersonFragment.MyCustomAdapter(getContext(), R.layout.row, Teams);
        //spinner.setAdapter(adapter);
        spinner.setSelection(1);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {}
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {}
        });
    }
}
