package com.example.mathsquizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class Level1 extends AppCompatActivity implements View.OnClickListener {

    TextView level,questionNumber, timer, question;
    Button opt1, opt2, opt3, opt4, submit;

    int questionIndex = 0;
    private int score = 0;
    int totalQuestion = questions.questions.length;

    String selectedAns = "";

    MediaPlayer successSound, failSound;
    CountDownTimer countDownTimer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level1);

        successSound = MediaPlayer.create(this, R.raw.pass);
        failSound = MediaPlayer.create(this, R.raw.fail);
        successSound.setLooping(false);
        successSound.setLooping(false);


        //Connect using an XML tag that has an ID attribute.
        level = findViewById(R.id.Two);
        questionNumber = findViewById(R.id.ttl_questions);
        timer = findViewById(R.id.timelimit);
        question = findViewById(R.id.question1);

        opt1 = findViewById(R.id.option11);
        opt2 = findViewById(R.id.option21);
        opt3 = findViewById(R.id.option31);
        opt4 = findViewById(R.id.option41);
        submit = findViewById(R.id.submit1);

        opt1.setOnClickListener(this);
        opt2.setOnClickListener(this);
        opt3.setOnClickListener(this);
        opt4.setOnClickListener(this);
        submit.setOnClickListener(this);
        questionNumber.setText("Total questions: " + totalQuestion);
        submit.setEnabled(false);//Initially, the submission feature should be disabled.


        //set level text
        level.setText("Level 1");

        //load question
        loadNewQuestion();
    }

    //getRandomNonRepeatingIntegers function
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

    //on click function
    @Override
    public void onClick(View v) {


        Button clickButton = (Button) v;

        if (clickButton.equals(submit)){
            if (selectedAns.equals(questions.answer[(int) List.get(questionIndex)])){
                score++;
            }

            questionIndex++;
            loadNewQuestion();
        }
        else {
            countDownTimer.cancel();
            selectedAns = clickButton.getText().toString();
            if (selectedAns == null) {
                submit.setEnabled(false); //If the selectedAnswer is null, then the submit button should be disabled
            } else {
                submit.setEnabled(true); //If the the selectedAnswer is not null, then the submit button be enable
            }
            showAns();
            //check answer
            if (selectedAns.equals(questions.answer[(int) List.get(questionIndex)])){

                if (successSound == null){
                    successSound = MediaPlayer.create(this, R.raw.pass);
                }
                successSound.start();
            }
            else {
                if (failSound == null){
                    failSound = MediaPlayer.create(this, R.raw.fail);
                }
                failSound.start();
            }

        }
    }

    ArrayList List = getRandomNonRepeatingIntegers(questions.questions.length, 0, (questions.questions.length-1));
    void loadNewQuestion(){
        timer();
        if (questionIndex == totalQuestion) {
            finishQuiz();
            return;
        }
        submit.setEnabled(false);
        opt1.setEnabled(true);
        opt2.setEnabled(true);
        opt3.setEnabled(true);
        opt4.setEnabled(true);


        opt1.setBackgroundColor(Color.parseColor("#d3a04f"));
        opt2.setBackgroundColor(Color.parseColor("#d3a04f"));
        opt3.setBackgroundColor(Color.parseColor("#d3a04f"));
        opt4.setBackgroundColor(Color.parseColor("#d3a04f"));


        questionNumber.setText("Question : "+(questionIndex+1)+" / "+totalQuestion );
        question.setText(questions.questions[(int) List.get(questionIndex)]);
        opt1.setText(questions.choices[(int) List.get(questionIndex)][0]);
        opt2.setText(questions.choices[(int) List.get(questionIndex)][1]);
        opt3.setText(questions.choices[(int) List.get(questionIndex)][2]);
        opt4.setText(questions.choices[(int) List.get(questionIndex)][3]);
    }

    //finish quiz function
    void finishQuiz(){
        countDownTimer.cancel();
        Intent intent = new Intent(Level1.this,ResultScore.class);
        intent.putExtra("score",score);
        startActivity(intent);
        finish();
    }

    void showAns(){
        String b1=opt1.getText().toString();
        String b2=opt2.getText().toString();
        String b3=opt3.getText().toString();
        String b4=opt4.getText().toString();

        opt1.setEnabled(false);
        opt2.setEnabled(false);
        opt3.setEnabled(false);
        opt4.setEnabled(false);


        //opt1
        if (b1.equals(questions.answer[(int) List.get(questionIndex)])){
            opt1.setBackgroundColor(Color.parseColor("#66ff33"));
        }
        else {opt1.setBackgroundColor(Color.parseColor("#d80303"));}

        //opt2
        if (b2.equals(questions.answer[(int) List.get(questionIndex)])){
            opt2.setBackgroundColor(Color.parseColor("#66ff33"));
        }
        else {opt2.setBackgroundColor(Color.parseColor("#d80303"));}

        //opt3
        if (b3.equals(questions.answer[(int) List.get(questionIndex)])){
            opt3.setBackgroundColor(Color.parseColor("#66ff33"));
        }
        else {opt3.setBackgroundColor(Color.parseColor("#d80303"));}

        //opt4
        if (b4.equals(questions.answer[(int) List.get(questionIndex)])){
            opt4.setBackgroundColor(Color.parseColor("#66ff33"));
        }
        else {opt4.setBackgroundColor(Color.parseColor("#d80303"));}

    }
    private void timer() {
        countDownTimer = new CountDownTimer(20000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                // calculate seconds remaining and format as a string
                String seconds = String.format("%02d", millisUntilFinished / 1000);
                timer.setText("00:" + seconds);
            }

            @Override
            public void onFinish() {
                questionIndex++;
                loadNewQuestion();
            }
        }.start();
    }
}