package com.example.mathsquizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ChooseTopic extends AppCompatActivity {
    Button Zero,One,Two;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_topic);

        Zero = findViewById(R.id.zero);
        One = findViewById(R.id.one);
        Two = findViewById(R.id.two);

        Zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent on = new Intent(ChooseTopic.this,OrderNumActivity.class);
                startActivity(on);
            }
        });
        One.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  cn = new Intent(ChooseTopic.this,ComNumActivity.class);
                startActivity(cn);
            }
        });
        Two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cp = new Intent(ChooseTopic.this,ComposingNumActivity.class);
                startActivity(cp);
            }
        });
    }
}
