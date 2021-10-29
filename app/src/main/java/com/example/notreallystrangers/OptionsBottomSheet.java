package com.example.notreallystrangers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;

public class OptionsBottomSheet extends BottomSheetDialogFragment {

	private boolean shuffled = false;
	private Chip chipShuffle, chipBase, chipHonestDating, chipRelationship, chipInnerCircle, chipBreakup, chipOwnIt;
	private ChipGroup chipGroup;
	private ArrayList<String> selectedDecks;

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.bottom_sheet_layout, container, false);

		chipGroup = (ChipGroup) v.findViewById(R.id.chipGroup);

		chipShuffle = (Chip) v.findViewById(R.id.chipShuffle);
		chipBase = (Chip) v.findViewById(R.id.chipBase);
		chipHonestDating = (Chip) v.findViewById(R.id.chipHonestDating);
		chipRelationship = (Chip) v.findViewById(R.id.chipRelationship);
		chipInnerCircle = (Chip) v.findViewById(R.id.chipInnerCircle);
		chipBreakup = (Chip) v.findViewById(R.id.chipBreakup);
		chipOwnIt = (Chip) v.findViewById(R.id.chipOwnIt);

		shuffled = getArguments().getBoolean("shuffled");
		selectedDecks = getArguments().getStringArrayList("selectedDecks");

		chipGroup.setSelectionRequired(true);


		System.out.println(shuffled);

		for (int i = 0; i < chipGroup.getChildCount(); i++) {
			Chip chip = (Chip) chipGroup.getChildAt(i);
			if (selectedDecks.contains(chip.getText().toString().toLowerCase())) {
				chip.setChecked(true);
			}
		}


		/*
		ChipGroup.OnCheckedChangeListener checkedChangeListener = new ChipGroup.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(ChipGroup group, int checkedId) {
				String msg = "Chips checked are:";
				int chipsCount = chipGroup.getChildCount();
				if (chipsCount == 0) {
					msg += " None!!";
				} else {
					int i = 0;
					while (i < chipsCount) {
						Chip chip = (Chip) chipGroup.getChildAt(i);
						if (chip.isChecked() ) {
							msg += chip.getText().toString() + " " ;
						}
						i++;
					};
				}
				// show message
				Toast.makeText(getContext().getApplicationContext(),msg, Toast.LENGTH_LONG).show();
			}

		};

		chipGroup.setOnCheckedChangeListener(checkedChangeListener);
		*/

		CompoundButton.OnCheckedChangeListener checkedChangeListener1 = new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
				String msg = "Chips checked are:";
				int chipsCount = chipGroup.getChildCount();
				if (chipsCount == 0) {
					msg += " None!!";
				} else {
					int i = 0;
					while (i < chipsCount) {
						Chip chip = (Chip) chipGroup.getChildAt(i);
						if (chip.isChecked() && !selectedDecks.contains(chip.getText().toString().toLowerCase())) {
							selectedDecks.add(chip.getText().toString().toLowerCase());
							msg += chip.getText().toString() + " " ;
							Bundle bundle = new Bundle();

							bundle.putStringArrayList("bundleKey", selectedDecks);
							getParentFragmentManager().setFragmentResult("requestKey", bundle);
						} else if (!chip.isChecked() && selectedDecks.contains(chip.getText().toString().toLowerCase()) && selectedDecks.size() > 1) {
							selectedDecks.remove(chip.getText().toString().toLowerCase());
							Bundle bundle = new Bundle();

							bundle.putStringArrayList("bundleKey", selectedDecks);
							getParentFragmentManager().setFragmentResult("requestKey", bundle);
						}
						i++;
					};
				}
				// show message
//				Toast.makeText(getContext().getApplicationContext(),msg, Toast.LENGTH_LONG).show();
//				Toast.makeText(getContext().getApplicationContext(), selectedDecks.get(0) + " " + selectedDecks.get(1), Toast.LENGTH_LONG).show();




			}
		};

		chipShuffle.setOnCheckedChangeListener(checkedChangeListener1);
		chipBase.setOnCheckedChangeListener(checkedChangeListener1);
		chipHonestDating.setOnCheckedChangeListener(checkedChangeListener1);
		chipRelationship.setOnCheckedChangeListener(checkedChangeListener1);
		chipInnerCircle.setOnCheckedChangeListener(checkedChangeListener1);
		chipBreakup.setOnCheckedChangeListener(checkedChangeListener1);
		chipOwnIt.setOnCheckedChangeListener(checkedChangeListener1);




/*

		mSwitchShuffle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

				MainActivity mainActivity = (MainActivity) getActivity();

				if(mSwitchShuffle.isChecked()) {
					System.out.println("shuffling...");
					mainActivity.shuffleList();

				} else {
					System.out.println("sorting...");
					mainActivity.sortList();
				}


				shuffled = !shuffled;
				System.out.println(shuffled);

			}
		});*/


		return v;
	}


}
