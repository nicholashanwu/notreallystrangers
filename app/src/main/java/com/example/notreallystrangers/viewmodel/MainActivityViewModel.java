package com.example.notreallystrangers.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.notreallystrangers.model.Question;
import com.example.notreallystrangers.repository.QuestionRepo;

import java.util.List;

//extend AndroidViewModel instead if Application context is needed
public class MainActivityViewModel extends AndroidViewModel {

	QuestionRepo mRepo;

	public MainActivityViewModel(@NonNull Application application) {
		super(application);
		mRepo = new QuestionRepo();
	}

	public List<Question> getQuestions() {
		return mRepo.getQuestions();
	}
}
