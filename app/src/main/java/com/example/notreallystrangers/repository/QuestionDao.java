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

	@Query("SELECT * FROM question WHERE question_type = :type AND level = 1 AND is_wildcard NOT IN ('final')")
	List<Question> getLevel1QuestionsOfType(String type);

	@Query("SELECT * FROM question WHERE question_type = :type AND level = 2 AND is_wildcard NOT IN ('final')")
	List<Question> getLevel2QuestionsOfType(String type);

	@Query("SELECT * FROM question WHERE question_type = :type AND level = 3 AND is_wildcard NOT IN ('final')")
	List<Question> getLevel3QuestionsOfType(String type);

	@Query("SELECT * FROM question WHERE question_type = :type AND is_wildcard = 'final'")
	List<Question> getFinalQuestionsOfType(String type);


	@Insert
	public void insertQuestions(Question... questions);

	@Insert
	public void insertQuestion(Question question);

	@Query("SELECT * FROM question WHERE is_wildcard = 'true'")
	List<Question> getWildcards();

	// when someone clicks on base deck and honest dating

	// create 3 separate decks
	// need to get level 1 from both decks, then shuffle them
	// need to get level 2 from both decks, then shuffle them
	// need to get level 3 from both decks, then shuffle them
	// merge them together
}
