package com.example.notreallystrangers;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentResultListener;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.notreallystrangers.model.Question;
import com.example.notreallystrangers.repository.AppDatabase;
import com.example.notreallystrangers.repository.SwipeStackAdapter;
import com.example.notreallystrangers.sheet.InfoBottomSheet;
import com.example.notreallystrangers.sheet.OptionsBottomSheet;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.akexorcist.roundcornerprogressbar.common.AnimatedRoundCornerProgressBar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import link.fls.swipestack.SwipeStack;

public class MainActivity extends AppCompatActivity {

	private AppDatabase db;

	private SwipeStack swipeStack;
	private TextView messageTextView;
	private TextView progressTextView;
	private FloatingActionButton optionButton;
	private FloatingActionButton infoButton;
	private AnimatedRoundCornerProgressBar progressBar;
	private int progress = 0;
	private int totalProgress;

	private List<String> selectedDecks;

	private	List<Question> questionList;

	private boolean shuffled;
	private boolean darkMode;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		swipeStack = (SwipeStack) findViewById(R.id.bodyTextView);
		progressTextView = (TextView) findViewById(R.id.progressTextView);
		optionButton = (FloatingActionButton) findViewById(R.id.button);
		infoButton = (FloatingActionButton) findViewById(R.id.infoButton);
		progressBar = (AnimatedRoundCornerProgressBar) findViewById(R.id.progressBar);

		db = AppDatabase.getInstance(this);

		// perform vibrations on cards swiped
		swipeStack.setListener(new SwipeStack.SwipeStackListener() {			//view listener
			@Override
			public void onViewSwipedToLeft(int position) {											//position of view that was swiped away. position + 1 should be current view
				swipeStack.performHapticFeedback(HapticFeedbackConstants.CONFIRM);
				incrementProgress();
			}

			@Override
			public void onViewSwipedToRight(int position) {
				swipeStack.performHapticFeedback(HapticFeedbackConstants.CONFIRM);
				incrementProgress();
			}

			@Override
			public void onStackEmpty() {
				swipeStack.performHapticFeedback(HapticFeedbackConstants.CONFIRM);
				//show animation
				//show final question
				finishGame();
			}
		});

		questionList = new ArrayList<>();

		selectedDecks = new ArrayList<>();

		darkMode = getNightMode();

		optionButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				OptionsBottomSheet optionsBottomSheet = new OptionsBottomSheet();

				Bundle bundle = new Bundle();
				bundle.putStringArrayList("selectedDecks", (ArrayList<String>) selectedDecks);
				bundle.putBoolean("shuffled", shuffled);
				bundle.putBoolean("darkMode", getNightMode());

				optionsBottomSheet.setArguments(bundle);
				optionsBottomSheet.show(getSupportFragmentManager(), "bottomSheet");
			}
		});

		infoButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				InfoBottomSheet infoBottomSheet = new InfoBottomSheet();

				Bundle bundle = new Bundle();
				bundle.putStringArrayList("selectedDecks", (ArrayList<String>) selectedDecks);
				bundle.putBoolean("shuffled", shuffled);
				bundle.putBoolean("darkMode", getNightMode());

				infoBottomSheet.setArguments(bundle);
				infoBottomSheet.show(getSupportFragmentManager(), "bottomSheet");
			}
		});

		if (savedInstanceState != null) {																			//restore state

			selectedDecks = savedInstanceState.getStringArrayList("selectedDecks");
			questionList = savedInstanceState.getParcelableArrayList("questionList");
			shuffled = savedInstanceState.getBoolean("shuffled");
			darkMode = savedInstanceState.getBoolean("darkMode");
			progress = savedInstanceState.getInt("progress");
			totalProgress = savedInstanceState.getInt("totalProgress");

			swipeStack.setAdapter(new SwipeStackAdapter(questionList, this));

			progressBar.setProgress(progress);
			progressBar.setMax(totalProgress);

			if(!questionList.isEmpty()) {
				progressTextView.setText(totalProgress - progress + " CARDS LEFT");
			}

		}

		getSupportFragmentManager().setFragmentResultListener("requestKey", this, new FragmentResultListener() {
			@Override
			public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle bundle) {
				ArrayList<String> result = bundle.getStringArrayList("bundleKey");
				shuffled = bundle.getBoolean("shuffled");

				reloadDecks(result, shuffled);
			}
		});
	}

	public void incrementProgress () {
		progress++;
		progressBar.setProgress(progress);
		progressTextView.setText(totalProgress - progress + " CARDS LEFT");

	}

	public boolean getNightMode() {
		int currentNightMode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
		switch (currentNightMode) {
			case Configuration.UI_MODE_NIGHT_NO:
				return false;
			case Configuration.UI_MODE_NIGHT_YES:
				return true;
		}
		return false;
	}

	public void reloadDecks(ArrayList<String> selectedDecks, boolean shuffled) {

		List<Question> newQuestionList1 = new ArrayList<>();
		List<Question> newQuestionList2 = new ArrayList<>();
		List<Question> newQuestionList3 = new ArrayList<>();
		List<Question> fullQuestionList = new ArrayList<>();

		for(String deckString : selectedDecks) {
			newQuestionList1.addAll(db.questionDao().getLevel1QuestionsOfType(deckString));
			newQuestionList2.addAll(db.questionDao().getLevel2QuestionsOfType(deckString));
			newQuestionList3.addAll(db.questionDao().getLevel3QuestionsOfType(deckString));
		}

		if(shuffled) {
			Collections.shuffle(newQuestionList1);
			Collections.shuffle(newQuestionList2);
			Collections.shuffle(newQuestionList3);
		} else {
			Collections.sort(newQuestionList1);
			Collections.sort(newQuestionList2);
			Collections.sort(newQuestionList3);
		}

		fullQuestionList.addAll(newQuestionList1);
		fullQuestionList.addAll(newQuestionList2);
		fullQuestionList.addAll(newQuestionList3);

		for(String deckString : selectedDecks) {		// final questions
			fullQuestionList.addAll(db.questionDao().getFinalQuestionsOfType(deckString));
		}

		questionList = fullQuestionList;

		swipeStack.resetStack();
		swipeStack.setAdapter(new SwipeStackAdapter(fullQuestionList, this));

		totalProgress = fullQuestionList.size();
		progress = 0;

		progressBar.setMax(totalProgress);
		progressBar.setProgress(progress);

		progressTextView.setText(totalProgress + " CARDS LEFT");

		YoYo.with(Techniques.SlideInUp).duration(500).playOn(progressTextView);
		YoYo.with(Techniques.SlideInUp).duration(500).playOn(progressBar);
	}

	public void finishGame() {
		shuffled = false;
		progress = 0;
		progressBar.setProgress(0);
		questionList = new ArrayList<>();
		selectedDecks = new ArrayList<>();
		YoYo.with(Techniques.SlideOutDown).duration(1000).playOn(progressBar);
		YoYo.with(Techniques.SlideOutDown).duration(1000).playOn(progressTextView);

	}

	@Override
	protected void onSaveInstanceState(@NonNull Bundle outState) {
		super.onSaveInstanceState(outState);

		outState.putParcelableArrayList("questionList", (ArrayList<? extends Parcelable>) questionList);
		outState.putStringArrayList("selectedDecks", (ArrayList<String>) selectedDecks);
		outState.putBoolean("darkMode", darkMode);
		outState.putBoolean("shuffled", shuffled);
		outState.putInt("progress", progress);
		outState.putInt("totalProgress", totalProgress);

	}


}