package com.example.notreallystrangers.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Question implements Parcelable, Comparable<Question> {
	@PrimaryKey(autoGenerate = true)
	private int questionId;

	@ColumnInfo(name = "question_type")
	private String questionType;

	@ColumnInfo(name = "level")
	private int level;

	@ColumnInfo(name = "is_wildcard")
	private String isWildCard; //"true" or "false" or "final" or "reminder"

	@ColumnInfo(name = "question_body")
	private String questionBody;

	public Question() {

	}

	public Question(String questionType, int level, String isWildCard, String questionBody) {
		this.level = level;
		this.questionType = questionType;
		this.isWildCard = isWildCard;
		this.questionBody = questionBody;
	}

	protected Question(Parcel in) {
		questionId = in.readInt();
		questionType = in.readString();
		isWildCard = in.readString();
		questionBody = in.readString();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(questionId);
		dest.writeString(questionType);
		dest.writeString(isWildCard);
		dest.writeString(questionBody);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public static final Creator<Question> CREATOR = new Creator<Question>() {
		@Override
		public Question createFromParcel(Parcel in) {
			return new Question(in);
		}

		@Override
		public Question[] newArray(int size) {
			return new Question[size];
		}
	};

	@Override
	public String toString() {
		return "Question{" +
				"questionId=" + questionId +
				", questionType='" + questionType + '\'' +
				", isWildCard='" + isWildCard + '\'' +
				", questionBody='" + questionBody + '\'' +
				'}';
	}

	public String getIsWildCard() {
		return isWildCard;
	}

	public void setIsWildCard(String isWildCard) {
		this.isWildCard = isWildCard;
	}

	public String getQuestionType() {
		return questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public String getQuestionBody() {
		return questionBody;
	}

	public void setQuestionBody(String questionBody) {
		this.questionBody = questionBody;
	}


	@Override
	public int compareTo(Question question) {
		return this.getQuestionId() - question.getQuestionId();
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
}
