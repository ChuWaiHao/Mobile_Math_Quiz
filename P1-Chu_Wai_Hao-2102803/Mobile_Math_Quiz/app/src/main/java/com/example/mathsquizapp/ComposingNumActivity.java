package com.example.mathsquizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class ComposingNumActivity extends AppCompatActivity implements View.OnClickListener{
    TextView level,questionNumber, timer, question;
    Button opt1, opt2, opt3, submit;

    int questionIndex = 0;
    private int score = 0;
    int totalQuestion = ComposingNumQues.question3.length;

    String selectedAns = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_composing_number);

        //Connect using an XML tag that has an ID attribute.
        level = findViewById(R.id.level2);
        questionNumber = findViewById(R.id.questionindex2);
        timer = findViewById(R.id.timer2);


        question = findViewById(R.id.question2);


        question = findViewById(R.id.question2);
        opt1 = findViewById(R.id.Option1);
        opt2 = findViewById(R.id.Option2);
        opt3 = findViewById(R.id.Option3);
        submit = findViewById(R.id.submit2);

        opt1.setOnClickListener(this);
        opt2.setOnClickListener(this);
        opt3.setOnClickListener(this);
        submit.setOnClickListener(this);
        questionNumber.setText("Total questions: " + totalQuestion);
        submit.setEnabled(false);//Initially, the submission feature should be disabled.


        //set level text
        level.setText("Composing Number");

        //load question
        loadNewQuestion();
    }
    public static ArrayList getRandomNonRepeatingIntegers(int size, int min, int max) {
        ArrayList numbers = new ArrayList();
        Random random = new Random();
        while (numbers.size() < size) {
            int randomNumber = random.nextInt((max - min) + 1) + min;
            if (!numbers.contains(randomNumber)) {
                numbers.add(randomNumber);
            }
        }
        return numbers;
    }
    @Override
    public void onClick(View v) {
        Button clickButton = (Button) v;

        if (clickButton.equals(submit)){
            if (selectedAns.equals(ComposingNumQues.answer3[(int) List.get(questionIndex)])){
                score++;
            }

            questionIndex++;
            loadNewQuestion();
        }
        else {

            selectedAns = clickButton.getText().toString();
            if (selectedAns == null) {
                submit.setEnabled(false); //If the selectedAnswer is null, then the submit button should be disabled
            } else {
                submit.setEnabled(true); //If the the selectedAnswer is not null, then the submit button be enable
            }
            showAns();

        }
    }

    ArrayList List = getRandomNonRepeatingIntegers(ComposingNumQues.question3.length, 0, (ComposingNumQues.question3.length-1));
    void loadNewQuestion(){

        if (questionIndex == totalQuestion) {
            finishQuiz();
            return;
        }
        submit.setEnabled(false);
        opt1.setEnabled(true);
        opt2.setEnabled(true);
        opt3.setEnabled(true);

        opt1.setBackgroundColor(Color.parseColor("#d3a04f"));
        opt2.setBackgroundColor(Color.parseColor("#d3a04f"));
        opt3.setBackgroundColor(Color.parseColor("#d3a04f"));


        questionNumber.setText("Question : "+(questionIndex+1)+" / "+totalQuestion );
        question.setText(ComposingNumQues.question3[(int) List.get(questionIndex)]);
        opt1.setText(ComposingNumQues.choice3[(int) List.get(questionIndex)][0]);
        opt2.setText(ComposingNumQues.choice3[(int) List.get(questionIndex)][1]);
        opt3.setText(ComposingNumQues.choice3[(int) List.get(questionIndex)][2]);
    }

    //finish quiz function
    void finishQuiz(){

        Intent intent = new Intent(ComposingNumActivity.this,ResultScore.class);
        intent.putExtra("score",score);
        startActivity(intent);
        finish();
    }

    void showAns(){
        String b1=opt1.getText().toString();
        String b2=opt2.getText().toString();
        String b3=opt3.getText().toString();

        opt1.setEnabled(false);
        opt2.setEnabled(false);
        opt3.setEnabled(false);


        //opt1
        if (b1.equals(ComposingNumQues.answer3[(int) List.get(questionIndex)])){
            opt1.setBackgroundColor(Color.parseColor("#66ff33"));
        }
        else {opt1.setBackgroundColor(Color.parseColor("#d80303"));}

        //opt2
        if (b2.equals(ComposingNumQues.answer3[(int) List.get(questionIndex)])){
            opt2.setBackgroundColor(Color.parseColor("#66ff33"));
        }
        else {opt2.setBackgroundColor(Color.parseColor("#d80303"));}

        //opt3
        if (b3.equals(ComposingNumQues.answer3[(int) List.get(questionIndex)])){
            opt3.setBackgroundColor(Color.parseColor("#66ff33"));
        }
        else {opt3.setBackgroundColor(Color.parseColor("#d80303"));}

    }
}
