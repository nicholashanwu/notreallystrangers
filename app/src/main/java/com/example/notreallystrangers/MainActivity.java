package com.example.notreallystrangers;

import android.content.res.Configuration;
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

	private SwipeStack swipeStack;
	private TextView exampleTextView;
	private FloatingActionButton mButton;

	private ArrayList<String> selectedDecks;

	private	List<Question> questionList = new ArrayList<>();

	private boolean shuffled;

	private boolean darkMode;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		swipeStack = (SwipeStack) findViewById(R.id.bodyTextView);
		exampleTextView = (TextView) findViewById(R.id.exampleTextView);
		mButton = (FloatingActionButton) findViewById(R.id.button);

		db = AppDatabase.getInstance(this);

		// perform vibrations on cards swiped
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

		selectedDecks = new ArrayList<>();

		getNightMode();

		exampleTextView.setText(String.valueOf(questionList.size()));

		mButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				OptionsBottomSheet bottomSheet = new OptionsBottomSheet();

				Bundle bundle = new Bundle();
				bundle.putStringArrayList("selectedDecks", selectedDecks);
				bundle.putBoolean("shuffled", shuffled);
				bundle.putBoolean("darkMode", darkMode);

				bottomSheet.setArguments(bundle);
				bottomSheet.show(getSupportFragmentManager(), "bottomSheet");
			}
		});

		if (savedInstanceState != null) {																			//restore state

			selectedDecks = savedInstanceState.getStringArrayList("selectedDecks");
			questionList = savedInstanceState.getParcelableArrayList("questionList");
			shuffled = savedInstanceState.getBoolean("shuffled");
			darkMode = savedInstanceState.getBoolean("darkMode");
			swipeStack.setAdapter(new SwipeStackAdapter(questionList, this));
		}

		getSupportFragmentManager().setFragmentResultListener("requestKey", this, new FragmentResultListener() {
			@Override
			public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle bundle) {
				// We use a String here, but any type that can be put in a Bundle is supported
				ArrayList<String> result = bundle.getStringArrayList("bundleKey");
				shuffled = bundle.getBoolean("shuffled");
				// Do something with the result

				Toast.makeText(getApplicationContext(), result.toString(), Toast.LENGTH_SHORT).show();
				reloadDecks(result, shuffled);
			}
		});
	}

	//base
	//honest dating
	//inner circle
	//relationship
	//breakup
	//own it



	public void getNightMode() {
		int currentNightMode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
		switch (currentNightMode) {
			case Configuration.UI_MODE_NIGHT_NO:
				darkMode = false;
				break;
			case Configuration.UI_MODE_NIGHT_YES:
				darkMode = true;
				break;
		}
	}

	public void reloadDecks(ArrayList<String> selectedDecks, boolean shuffled) {
		List<Question> newQuestionList = new ArrayList<>();

		for(String deckString : selectedDecks) {
			newQuestionList.addAll(db.questionDao().getQuestionsOfType(deckString));
		}

		if(shuffled) {
			Collections.shuffle(newQuestionList);
		} else {
			Collections.sort(newQuestionList);
		}

		questionList = newQuestionList;
		swipeStack.resetStack();
		swipeStack.setAdapter(new SwipeStackAdapter(newQuestionList, this));

		this.selectedDecks = selectedDecks;

	}

	@Override
	protected void onSaveInstanceState(@NonNull Bundle outState) {
		super.onSaveInstanceState(outState);

		outState.putParcelableArrayList("questionList", (ArrayList<? extends Parcelable>) questionList);
		outState.putStringArrayList("selectedDecks", selectedDecks);
		outState.putBoolean("darkMode", darkMode);
		outState.putBoolean("shuffled", shuffled);

	}






}