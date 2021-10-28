package com.example.notreallystrangers.repository;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.example.notreallystrangers.model.Question;

@Database(entities = {Question.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

	public abstract QuestionDao questionDao();

	private static volatile AppDatabase INSTANCE;

	public static AppDatabase getInstance(Context context) {
		if (INSTANCE == null) {
			synchronized (AppDatabase.class) {
				if (INSTANCE == null) {
					INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
							AppDatabase.class,
							"database_name").
							createFromAsset("database_name").
							allowMainThreadQueries().
							build();
				}
			}
		}
		return INSTANCE;
	}

	@NonNull
	@Override
	protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
		return null;
	}

	@NonNull
	@Override
	protected InvalidationTracker createInvalidationTracker() {
		return null;
	}

	@Override
	public void clearAllTables() {

	}
}
