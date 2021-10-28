package com.example.notreallystrangers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.switchmaterial.SwitchMaterial;

public class OptionsBottomSheet extends BottomSheetDialogFragment {


	private SwitchMaterial mSwitchShuffle;
	private boolean shuffled = false;

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.bottom_sheet_layout, container, false);

		mSwitchShuffle = (SwitchMaterial) v.findViewById(R.id.switchShuffle);

		shuffled = getArguments().getBoolean("shuffled");

		mSwitchShuffle.setChecked(shuffled);

		System.out.println(shuffled);

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
		});


		return v;
	}
}
