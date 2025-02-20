package com.example.mathsquizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultScore extends AppCompatActivity implements View.OnClickListener {
    TextView result, scoreGot;
    Button home, exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_score);


        result = findViewById(R.id.Result);
        scoreGot = findViewById(R.id.scoreGot);
        home = findViewById(R.id.homebtn);
        exit = findViewById(R.id.exit);

        home.setOnClickListener(this);//What the fuck are you talking about Bro!????????
        exit.setOnClickListener(this);

        Intent intent = getIntent();
        int score = intent.getIntExtra("score", 0);

        //Comparing Number result

        scoreGot.setText(String.format("Your Score is " + score + " out of " + (ComNumQues.question1.length)));
        scoreGot.setText(String.format("Your Score is " + score + " out of " + (OrderNumQues.question2.length)));
        scoreGot.setText(String.format("Your Score is " + score + " out of " + (ComposingNumQues.question3.length)));
    }

    @Override
    public void onClick(View v) {

        Button clickButton = (Button) v;

        if (clickButton.equals(home)) {
            Intent intent = new Intent(ResultScore.this, ChooseTopic.class);
            startActivity(intent);
        } else {
            finishAffinity();
        }
    }

}
