package com.ranktrends.quizely.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.ranktrends.quizely.model.QuestionsList;
import com.ranktrends.quizely.repository.QuizRepository;

public class QuizViewModel extends ViewModel {
    QuizRepository repository = new QuizRepository();
    LiveData<QuestionsList> questionsListLiveData;

    public QuizViewModel() {
        questionsListLiveData = repository.getQuestionsFromAPI();

    }

    public  LiveData<QuestionsList> getQuestionsListLiveData(){
        return questionsListLiveData;
    }

}
