package com.example.notreallystrangers;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.notreallystrangers.model.Question;
import com.example.notreallystrangers.repository.AppDatabase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import link.fls.swipestack.SwipeStack;

public class MainActivity extends AppCompatActivity {

	private FloatingActionButton mButton;
	private SwipeStack swipeStack;

	private	List<Question> questionList = new ArrayList<>();
	private	List<Question> shuffledQuestionList = new ArrayList<>();

	private List<Question> usedQuestionList = new ArrayList<>();
	private List<Question> usedShuffledQuestionList = new ArrayList<>();

	private int cardPosition = 0;
	private int shuffledCardPosition = 0;

	private TextView exampleTextView;

	private boolean shuffled = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		swipeStack = (SwipeStack) findViewById(R.id.bodyTextView);
		mButton = (FloatingActionButton) findViewById(R.id.button);

		AppDatabase.getInstance(this);

		swipeStack.setListener(new SwipeStack.SwipeStackListener() {			//view listener
			@Override
			public void onViewSwipedToLeft(int position) {											//position of view that was swiped away. position + 1 should be current view
				swipeStack.performHapticFeedback(HapticFeedbackConstants.CONFIRM);
				cardPosition = position;

			}

			@Override
			public void onViewSwipedToRight(int position) {
				swipeStack.performHapticFeedback(HapticFeedbackConstants.CONFIRM);
				cardPosition = position;

			}

			@Override
			public void onStackEmpty() {
				swipeStack.performHapticFeedback(HapticFeedbackConstants.CONFIRM);
			}
		});


		shuffledQuestionList = AppDatabase.getInstance(this).questionDao().getAllQuestions();

		questionList = AppDatabase.getInstance(this).questionDao().getAllQuestions();
		questionList = AppDatabase.getInstance(this).questionDao().getWildcards();

		Collections.shuffle(shuffledQuestionList);



		/*
		Collections.reverse(questionList);
		Collections.reverse(shuffledQuestionList);
		*/

//		exampleTextView.setText(questionList.get(0).getQuestionBody());



		mButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				OptionsBottomSheet bottomSheet = new OptionsBottomSheet();
				Bundle bundle = new Bundle();
				bundle.putBoolean("shuffled", shuffled);
				bottomSheet.setArguments(bundle);

				bottomSheet.show(getSupportFragmentManager(), "bottomSheet");

			}
		});



		if (savedInstanceState != null) {
			shuffled = savedInstanceState.getBoolean("shuffled");
			questionList = savedInstanceState.getParcelableArrayList("questionList");
			shuffledQuestionList = savedInstanceState.getParcelableArrayList("shuffledQuestionList");
			shuffledCardPosition = savedInstanceState.getInt("shuffledCardPosition");
			cardPosition = savedInstanceState.getInt("cardPosition");


		}

		if(shuffled) {
			swipeStack.setAdapter(new SwipeStackAdapter(shuffledQuestionList, this));
		} else {
			swipeStack.setAdapter(new SwipeStackAdapter(questionList, this));
		}

		System.out.println("shuffled is " + shuffled);
	}

	//base
	//honest dating
	//inner circle
	//relationship
	//breakup
	//own it

	public void sortList() {
		swipeStack.resetStack();
		swipeStack.swipeTopViewToLeft();

		swipeStack.setAdapter(new SwipeStackAdapter(questionList, this));
		shuffled = false;
	}

	public void shuffleList() {
		swipeStack.resetStack();
		swipeStack.setAdapter(new SwipeStackAdapter(shuffledQuestionList, this));
		shuffled = true;
	}

	@Override
	protected void onSaveInstanceState(@NonNull Bundle outState) {
		super.onSaveInstanceState(outState);

		questionList.removeAll(usedQuestionList);
		shuffledQuestionList.removeAll(usedShuffledQuestionList);

		outState.putBoolean("shuffled", shuffled);

		outState.putParcelableArrayList("questionList", (ArrayList<? extends Parcelable>) questionList);
		outState.putParcelableArrayList("shuffledQuestionList", (ArrayList<? extends Parcelable>) shuffledQuestionList);

	}




}