package com.example.notreallystrangers.repository;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.notreallystrangers.model.Question;

import java.util.List;

@Dao
public interface QuestionDao {
	@Query("SELECT * FROM question")
	List<Question> getAllQuestions();

	@Query("SELECT * FROM question WHERE question_type = :type")
	List<Question> getQuestionsOfType(String type);

	@Insert
	public void insertQuestions(Question... questions);

	@Insert
	public void insertQuestion(Question question);
}
