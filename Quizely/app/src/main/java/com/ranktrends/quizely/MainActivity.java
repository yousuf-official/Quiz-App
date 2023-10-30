package com.ranktrends.quizely;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.ranktrends.quizely.databinding.ActivityMainBinding;
import com.ranktrends.quizely.model.Question;
import com.ranktrends.quizely.model.QuestionsList;
import com.ranktrends.quizely.viewmodel.QuizViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    QuizViewModel quizViewModel;
    List<Question> questionList;

    static int result = 0;
    static int totalQuestion = 0;
    int i = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

     binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        //Resetting the Score
        result =0;
        totalQuestion = 0;


        // Getting the response

       quizViewModel = new ViewModelProvider(this).get(QuizViewModel.class);

        //Displaying the first question

        quizViewModel.getQuestionsListLiveData().observe(this, new Observer<QuestionsList>() {
            @Override
            public void onChanged(QuestionsList questions) {
                Log.i("TAGY", "The first question: " +questions.get(0) );
                binding.txtQuestion.setText("Question 1: "+questions.get(0).getQuestion());
                binding.radio1.setText(questions.get(0).getOption1());
                binding.radio2.setText(questions.get(0).getOption2());
                binding.radio3.setText(questions.get(0).getOption3());
                binding.radio4.setText(questions.get(0).getOption4());
                questionList=questions;

            }
        });

        // Display the nest question


        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int selectedOption = binding.radioGroup.getCheckedRadioButtonId();

                if (selectedOption !=-1){
                    RadioButton radioButton = findViewById(selectedOption);



                    if(questionList.size()>i){

                        // Getting the number of question
                        totalQuestion = questionList.size();

                        // Check if the chosen option is correct

                        if (radioButton.getText().toString().equals(questionList.get(i-1).getCorrectOption())){
                            result++;
                            binding.txtResult.setText("Correct Answer: " +result);
                        }

                        // Displaying the next question

                        binding.txtQuestion.setText("Question " + (i+1) + " : "  + questionList.get(i).getQuestion());
                        binding.radio1.setText(questionList.get(i).getOption1());
                        binding.radio2.setText(questionList.get(i).getOption2());
                        binding.radio3.setText(questionList.get(i).getOption3());
                        binding.radio4.setText(questionList.get(i).getOption4());

                        //Checking if it is the last question
                        if (i == questionList.size()-1){
                            binding.btnNext.setText("Finish");

                        }
                        binding.radioGroup.clearCheck();
                        i++;



                    }else {
                        if (radioButton.getText().toString().equals(questionList.get(i-1).getCorrectOption())){
                            result++;
                            binding.txtResult.setText("Correct Answer: "+result);
                        }else {


                        }

                        // Redirect the user to the result activity

                        Intent intent = new Intent(MainActivity.this, ResultsActivity.class);
                        startActivity(intent);
                        finish();


                    }


                } else {
                    Toast.makeText(MainActivity.this, "You need to make selection", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
}