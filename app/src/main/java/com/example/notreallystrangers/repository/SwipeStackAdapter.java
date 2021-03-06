package com.example.notreallystrangers.repository;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.notreallystrangers.R;
import com.example.notreallystrangers.model.Question;

import java.util.List;

public class SwipeStackAdapter extends BaseAdapter {

	private List<Question> mData;
	private Context context;

	public SwipeStackAdapter(List<Question> data, Context context) {
		this.mData = data;
		this.context = context;
	}

	@Override
	public int getCount() {
		return mData.size();
	}

	@Override
	public Question getItem(int position) {
		return mData.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {


		if(mData.get(position).getIsWildCard().equals("true") || mData.get(position).getIsWildCard().equals("final")) {
			convertView = LayoutInflater.from(context).inflate(R.layout.wildcard, parent,false);
		} else {
			convertView = LayoutInflater.from(context).inflate(R.layout.card, parent,false);
		}

		TextView cardWildcardTextView = (TextView) convertView.findViewById(R.id.cardWildcardTextView);
		TextView cardLevelTextView = (TextView) convertView.findViewById(R.id.cardLevelTextView);
		TextView cardBodyTextView = (TextView) convertView.findViewById(R.id.cardBodyTextView);
		TextView cardQuestionNumberTextView = (TextView) convertView.findViewById(R.id.cardQuestionNumberTextView);

		cardLevelTextView.setText("LEVEL " + mData.get(position).getLevel());
		cardBodyTextView.setText(mData.get(position).getQuestionBody().toUpperCase());
		cardQuestionNumberTextView.setText(String.valueOf(mData.get(position).getQuestionId()));

		if(mData.get(position).getIsWildCard().equals("final")) {
			cardWildcardTextView.setText("FINAL CARD");
		}


		TextView cardExpansionTextView = (TextView) convertView.findViewById(R.id.cardExpansionTextView);

		if(!mData.get(position).getQuestionType().equals("base pack")) {
			cardExpansionTextView.setText(mData.get(position).getQuestionType().toUpperCase() + " EXPANSION PACK");
		} else {
			cardExpansionTextView.setVisibility(View.INVISIBLE);
		}



		return convertView;
	}
}