package com.example.hackaton;


import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hackaton.ui.person.PersonFragment;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


public class UpdateStatusActivity extends AppCompatActivity {
    private SharedPreferences mSettings;
    public static final String APP_PREFERENCES_STATUS = "status";
    public static final String APP_PREFERENCES_TYPE_DOC="typedoc";
    public static final String APP_PREFERENCES_DOC="doc";
    String selected=""; //, type_doc="", doc="";
    String[] Teams = { "Brilliant", "Premium", "Active", "Base"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.update_status_layout);
        Spinner spinner = findViewById(R.id.spinner2);
        spinner.setSelection(3);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {

                String[] choose = getResources().getStringArray(R.array.catNames);
                selected = choose[selectedItemPosition];

            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        Button send = (Button) findViewById(R.id.send);
        TextInputLayout text1 = findViewById(R.id.textInputLayout);
        final TextInputEditText type_doc_v = new TextInputEditText(text1.getContext());
        TextInputLayout text2 = findViewById(R.id.textInputLayout2);
        final TextInputEditText doc_v = new TextInputEditText(text2.getContext());
        send.setOnClickListener(new View.OnClickListener() {
            final String type_doc = type_doc_v.getText().toString();
            final String doc = doc_v.getText().toString();
                                    @Override
                                    public void onClick(View v) {
                                        //final String email = inputEmail.getText().toString();

                                        Toast.makeText(UpdateStatusActivity.this,""+type_doc, Toast.LENGTH_LONG).show();
                                        Toast.makeText(UpdateStatusActivity.this,""+doc, Toast.LENGTH_LONG).show();
                                        if (type_doc.equals("")) {
                                            Toast.makeText(getApplicationContext(), "Введите тип документа!", Toast.LENGTH_SHORT).show();
                                            return;
                                        }
                                        if (doc.equals("")) {
                                            Toast.makeText(getApplicationContext(), "Введите номер документа!", Toast.LENGTH_SHORT).show();
                                            return;
                                        }
                                        /*SharedPreferences.Editor ed = mSettings.edit();
                                        ed.putString(APP_PREFERENCES_STATUS, selected);
                                        ed.putString(APP_PREFERENCES_TYPE_DOC, type_doc);
                                        ed.putString(APP_PREFERENCES_DOC, doc);
                                        ed.commit();

                                         */

                                        //startActivity(new Intent(UpdateStatusActivity.this, MainActivity.class));
                                    }
                                });


    }
}
