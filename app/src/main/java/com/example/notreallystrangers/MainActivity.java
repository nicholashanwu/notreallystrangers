package com.example.notreallystrangers;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentResultListener;

import com.example.notreallystrangers.model.Question;
import com.example.notreallystrangers.repository.AppDatabase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import link.fls.swipestack.SwipeStack;

public class MainActivity extends AppCompatActivity {

	private AppDatabase db;

	private FloatingActionButton mButton;
	private SwipeStack swipeStack;

	private ArrayList<String> selectedDecks;

	private	List<Question> questionList = new ArrayList<>();
	private	List<Question> shuffledQuestionList = new ArrayList<>();

	private TextView exampleTextView;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		swipeStack = (SwipeStack) findViewById(R.id.bodyTextView);
		mButton = (FloatingActionButton) findViewById(R.id.button);
		exampleTextView = (TextView) findViewById(R.id.exampleTextView);

		db = AppDatabase.getInstance(this);

		swipeStack.setListener(new SwipeStack.SwipeStackListener() {			//view listener
			@Override
			public void onViewSwipedToLeft(int position) {											//position of view that was swiped away. position + 1 should be current view
				swipeStack.performHapticFeedback(HapticFeedbackConstants.CONFIRM);
			}

			@Override
			public void onViewSwipedToRight(int position) {
				swipeStack.performHapticFeedback(HapticFeedbackConstants.CONFIRM);
			}

			@Override
			public void onStackEmpty() {
				swipeStack.performHapticFeedback(HapticFeedbackConstants.CONFIRM);
			}
		});

		questionList = AppDatabase.getInstance(this).questionDao().getQuestionsOfType("base pack");



		Collections.shuffle(shuffledQuestionList);

		selectedDecks = new ArrayList<>();

		/*if(selectedDecks.isEmpty()) {
			selectedDecks.add("base pack");
		}

		reloadDecks(selectedDecks);*/



		/*
		Collections.reverse(questionList);
		Collections.reverse(shuffledQuestionList);
		*/

		exampleTextView.setText(String.valueOf(questionList.size()));



		mButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				OptionsBottomSheet bottomSheet = new OptionsBottomSheet();
				Bundle bundle = new Bundle();
				bundle.putStringArrayList("selectedDecks", selectedDecks);
				bottomSheet.setArguments(bundle);

				bottomSheet.show(getSupportFragmentManager(), "bottomSheet");

			}
		});

		if (savedInstanceState != null) {																			//restore state

			questionList = savedInstanceState.getParcelableArrayList("questionList");
			shuffledQuestionList = savedInstanceState.getParcelableArrayList("shuffledQuestionList");
			selectedDecks = savedInstanceState.getStringArrayList("selectedDecks");
		}

		getSupportFragmentManager().setFragmentResultListener("requestKey", this, new FragmentResultListener() {
			@Override
			public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle bundle) {
				// We use a String here, but any type that can be put in a Bundle is supported
				ArrayList<String> result = bundle.getStringArrayList("bundleKey");
				// Do something with the result

				Toast.makeText(getApplicationContext(), result.toString(), Toast.LENGTH_SHORT).show();
				reloadDecks(result);

			}
		});
	}

	//base
	//honest dating
	//inner circle
	//relationship
	//breakup
	//own it



	public void reloadDecks(ArrayList<String> selectedDecks) {
		List<Question> newQuestionList = new ArrayList<>();

		for(String deckString : selectedDecks) {
			if(!deckString.equals("shuffle")) {
				newQuestionList.addAll(db.questionDao().getQuestionsOfType(deckString));
			}
		}

		if(selectedDecks.contains("shuffle")) {
			Collections.shuffle(newQuestionList);
		} else {
			Collections.sort(newQuestionList);
		}

		questionList = newQuestionList;
		System.out.println(newQuestionList.get(0).getQuestionType());
		swipeStack.resetStack();
		swipeStack.setAdapter(new SwipeStackAdapter(newQuestionList, this));

	}

	@Override
	protected void onSaveInstanceState(@NonNull Bundle outState) {
		super.onSaveInstanceState(outState);

		outState.putParcelableArrayList("questionList", (ArrayList<? extends Parcelable>) questionList);
		outState.putParcelableArrayList("shuffledQuestionList", (ArrayList<? extends Parcelable>) shuffledQuestionList);
		outState.putStringArrayList("selectedDecks", selectedDecks);

	}




}