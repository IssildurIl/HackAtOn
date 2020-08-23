package com.example.hackaton;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hackaton.model.ExampleItem;


public class IntentActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent);
        Intent intent = getIntent();
        ExampleItem exampleItem = intent.getParcelableExtra("Example Item");
        int imageRes = exampleItem.getImageResource();
        String line1 = exampleItem.getText1();
        String line2 = exampleItem.getText2();
        Button gotochat=findViewById(R.id.gotoChat);
        ImageView imageView = findViewById(R.id.iconmain);
        imageView.setImageResource(imageRes);
        TextView textView1 = findViewById(R.id.titlemain);
        textView1.setText(line1);
        TextView textView2 = findViewById(R.id.bodymain);
        textView2.setText(line2);
        gotochat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IntentActivity.this,ChatActivity.class);
                startActivity(intent);
            }
        });
    }
}