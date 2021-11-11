package com.example.notreallystrangers.sheet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.CheckBox;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.notreallystrangers.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;

public class InfoBottomSheet extends BottomSheetDialogFragment {

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
				FrameLayout bottomSheet = (FrameLayout) dialog.findViewById(com.google.android.material.R.id.design_bottom_sheet);
				bottomSheet.setBackgroundColor(android.R.color.transparent);
				BottomSheetBehavior behavior = BottomSheetBehavior.from(bottomSheet);
				behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
				behavior.setPeekHeight(0);
			}
		});
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.bottom_sheet_info_layout, container, false);

		return v;
	}




}
