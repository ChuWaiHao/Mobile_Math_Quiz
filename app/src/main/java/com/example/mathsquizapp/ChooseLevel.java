package com.example.mathsquizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ChooseLevel extends AppCompatActivity {
Button Zero,One,Two;
TextView select;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_level);

        Zero = findViewById(R.id.zero);
        One = findViewById(R.id.one);
        Two = findViewById(R.id.two);

        Zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lv0 = new Intent(ChooseLevel.this,Level_0.class);
                startActivity(lv0);
            }
        });
        One.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lv1 = new Intent(ChooseLevel.this,Level1.class);
                startActivity(lv1);
            }
        });
        Two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lv2 = new Intent(ChooseLevel.this,Level2.class);
                startActivity(lv2);
            }
        });
    }
}