package com.technoee.jmaghrebsched.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

public class DbHelper extends SQLiteOpenHelper {

	public static final String DB_NAME = "jmaghreb.db";
	public static final int DB_VERSION = 1;

	interface Tables {
		String SAVED_SESSIONS = "sessions";
	}

	interface SavedSessionsColumns {
		String START_TIME = "start_time";
		String END_TIME = "end_titme";
		String TITLE = "title";
		String SUB_TITLE = "sub_title";
		String SLOT_TYPE = "slot_type";
		String DAY = "day";
		String SESSION_ID = "session_id";
	}

	public DbHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

//		db.execSQL("CREATE TABLE " + Tables.SAVED_SESSIONS + " ("
//                + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
//				+ SavedSessionsColumns.START_TIME +" TEXT,"
//				+ SavedSessionsColumns.END_TIME +" TEXT,"
//				+ SavedSessionsColumns.TITLE +" TEXT,"
//				+ SavedSessionsColumns.SUB_TITLE +" TEXT,"
//				+ SavedSessionsColumns.SLOT_TYPE +" TEXT,"
//				+ SavedSessionsColumns.DAY +" INTEGER,"
//				+ SavedSessionsColumns.SESSION_ID +" TEXT,"
//				+ "UNIQUE (" + SavedSessionsColumns.SESSION_ID + ") ON CONFLICT REPLACE)");
		
		db.execSQL("CREATE TABLE " + Tables.SAVED_SESSIONS + " ("
                + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ SavedSessionsColumns.SESSION_ID +" TEXT,"
				+ "UNIQUE (" + SavedSessionsColumns.SESSION_ID + ") ON CONFLICT REPLACE)");
				
		Log.d("DbHelper", "onCreated DB");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
        db.execSQL("DROP TABLE IF EXISTS " + Tables.SAVED_SESSIONS);
		Log.d("DbHelper", "onUpdated");
		onCreate(db);

	}

}
