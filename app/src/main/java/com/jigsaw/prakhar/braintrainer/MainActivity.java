package com.jigsaw.prakhar.braintrainer;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startbutton;
    TextView timer;
    TextView points;
    TextView question;
    Button answer0;
    Button answer1;
    Button answer2;
    Button answer3;
    TextView result;
    Button playagain;
    RelativeLayout relative;

    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    int score = 0;
    int numberOfQuestions = 0;

    public void playAgain(View view) {

        score = 0;
        numberOfQuestions = 0;

        timer.setText("30s");
        points.setText("0/0");
        result.setText("");
        playagain.setVisibility(View.INVISIBLE);

        generateQuestion();

        new CountDownTimer(30100, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {

                timer.setText(String.valueOf(millisUntilFinished / 1000) + "s");

            }

            @Override
            public void onFinish() {

                playagain.setVisibility(View.VISIBLE);
                timer.setText("0s");
                result.setText("Your score: " + Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));

            }
        }.start();


    }

    public void generateQuestion() {

        Random rand = new Random();

        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        question.setText(Integer.toString(a) + " + " + Integer.toString(b));

        locationOfCorrectAnswer = rand.nextInt(4);

        answers.clear();

        int incorrectAnswer;

        for (int i=0; i<4; i++) {

            if (i == locationOfCorrectAnswer) {

                answers.add(a + b);

            } else {

                incorrectAnswer = rand.nextInt(41);

                while (incorrectAnswer == a + b) {

                    incorrectAnswer = rand.nextInt(41);

                }

                answers.add(incorrectAnswer);

            }

        }
        answer0.setText(Integer.toString(answers.get(0)));
        answer1.setText(Integer.toString(answers.get(1)));
        answer2.setText(Integer.toString(answers.get(2)));
        answer3.setText(Integer.toString(answers.get(3)));
    }

    public void chooseAnswer(View view) {

        if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))) {

            score++;
            result.setText("Correct!");

        } else {

            result.setText("Wrong!");

        }

        numberOfQuestions++;
        points.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));

        generateQuestion();


    }



    public void start(View view){
        startbutton.setVisibility(View.INVISIBLE);
        relative.setVisibility(RelativeLayout.VISIBLE);

        playAgain(findViewById(R.id.button6));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startbutton = (Button)findViewById(R.id.button);
        timer = (TextView)findViewById(R.id.textView);
        points =(TextView)findViewById(R.id.textView2);
        question = (TextView)findViewById(R.id.textView3);
        answer0 = (Button)findViewById(R.id.button2);
        answer1 = (Button)findViewById(R.id.button3);
        answer2 = (Button)findViewById(R.id.button4);
        answer3 = (Button)findViewById(R.id.button5);
        result = (TextView)findViewById(R.id.textView4);
        playagain = (Button)findViewById(R.id.button6);
        relative = (RelativeLayout)findViewById(R.id.relative);





    }
}
