package com.example.notreallystrangers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;

public class OptionsBottomSheet extends BottomSheetDialogFragment {

	private Chip chipBase, chipHonestDating, chipRelationship, chipInnerCircle, chipBreakup, chipOwnIt, chipQuarantine;
	private ChipGroup chipGroup;
	private CheckBox checkBoxShuffle;
	private CheckBox checkBoxDarkMode;

	private ArrayList<String> selectedDecks;

	private boolean shuffled = false;

	private boolean darkMode;


	// ensure OptionsBottomSheet is fully expanded in horizontal orientation
	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
			@Override
			public void onGlobalLayout() {
				BottomSheetDialog dialog = (BottomSheetDialog) getDialog();
				FrameLayout bottomSheet = (FrameLayout)
				dialog.findViewById(com.google.android.material.R.id.design_bottom_sheet);
				BottomSheetBehavior behavior = BottomSheetBehavior.from(bottomSheet);
				behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
				behavior.setPeekHeight(0);
			}
		});
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.bottom_sheet_layout, container, false);

		checkBoxShuffle = (CheckBox) v.findViewById(R.id.checkBoxShuffle);
		checkBoxDarkMode = (CheckBox) v.findViewById(R.id.checkBoxDarkMode);

		chipGroup = (ChipGroup) v.findViewById(R.id.chipGroup);

		chipBase = (Chip) v.findViewById(R.id.chipBase);
		chipHonestDating = (Chip) v.findViewById(R.id.chipHonestDating);
		chipRelationship = (Chip) v.findViewById(R.id.chipRelationship);
		chipInnerCircle = (Chip) v.findViewById(R.id.chipInnerCircle);
		chipBreakup = (Chip) v.findViewById(R.id.chipBreakup);
		chipOwnIt = (Chip) v.findViewById(R.id.chipOwnIt);
		chipQuarantine = (Chip) v.findViewById(R.id.chipQuarantine);

		shuffled = getArguments().getBoolean("shuffled");
		selectedDecks = getArguments().getStringArrayList("selectedDecks");
		darkMode = getArguments().getBoolean("darkMode");

		updateUI();



		checkBoxShuffle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
				shuffled = !shuffled;
//				Toast.makeText(getContext().getApplicationContext(), "Shuffled = " + shuffled, Toast.LENGTH_SHORT).show();
				updateUI();
				reloadDecks();
			}
		});


		checkBoxDarkMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

				if(darkMode) {
					AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
				} else {
					AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
				}
				darkMode = !darkMode;

				updateUI();
//				reloadDecks();

			}
		});





		CompoundButton.OnCheckedChangeListener checkedChangeListener1 = new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

				chipGroup.getChildAt(0); //shuffle
				int chipsCount = chipGroup.getChildCount();

				for (int i = 0; i < chipsCount; i++) {

					Chip chip = (Chip) chipGroup.getChildAt(i);

					if (chip.isChecked() && !selectedDecks.contains(chip.getText().toString().toLowerCase())) {                // if the chip is checked and the selected decks does not already have that deck, add it and allow the shuffle button to be checked.

						selectedDecks.add(chip.getText().toString().toLowerCase());
						updateUI();
						reloadDecks();

					} else if (!chip.isChecked() && selectedDecks.contains(chip.getText().toString().toLowerCase()) && selectedDecks.size() > 1) {            // if the chip is not checked and the selected decks contains that chip and there is at least one other selected deck, remove that deck
						selectedDecks.remove(chip.getText().toString().toLowerCase());
						updateUI();
						reloadDecks();
					}
				}

			}
		};

		chipBase.setOnCheckedChangeListener(checkedChangeListener1);
		chipHonestDating.setOnCheckedChangeListener(checkedChangeListener1);
		chipRelationship.setOnCheckedChangeListener(checkedChangeListener1);
		chipInnerCircle.setOnCheckedChangeListener(checkedChangeListener1);
		chipBreakup.setOnCheckedChangeListener(checkedChangeListener1);
		chipOwnIt.setOnCheckedChangeListener(checkedChangeListener1);
		chipQuarantine.setOnCheckedChangeListener(checkedChangeListener1);

		return v;
	}


	public void updateUI () {

		chipGroup.setSelectionRequired(true);

		checkBoxShuffle.setEnabled(!selectedDecks.isEmpty());

		checkBoxShuffle.setChecked(shuffled);

		checkBoxDarkMode.setChecked(darkMode);

		// check the appropriate chips
		for (int i = 0; i < chipGroup.getChildCount(); i++) {
			Chip chip = (Chip) chipGroup.getChildAt(i);
			if (selectedDecks.contains(chip.getText().toString().toLowerCase())) {
				chip.setChecked(true);
			}
		}
	}


	public void reloadDecks(){

		Bundle bundle = new Bundle();
		bundle.putStringArrayList("bundleKey", selectedDecks);
		bundle.putBoolean("shuffled", shuffled);
		getParentFragmentManager().setFragmentResult("requestKey", bundle);
	}



}
