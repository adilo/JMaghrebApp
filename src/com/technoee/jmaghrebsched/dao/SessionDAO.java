package com.technoee.jmaghrebsched.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class SessionDAO {

	public static void add(String slotId, SQLiteDatabase db) {
		ContentValues values = new ContentValues();
		values.put(DbHelper.SavedSessionsColumns.SESSION_ID, slotId);
		db.insertOrThrow(DbHelper.Tables.SAVED_SESSIONS, null, values);
	}

	public static void remove(String slotId, SQLiteDatabase db) {
		ContentValues values = new ContentValues();
		values.put(DbHelper.SavedSessionsColumns.SESSION_ID, slotId);
		db.delete(DbHelper.Tables.SAVED_SESSIONS, DbHelper.SavedSessionsColumns.SESSION_ID + "="
				+ slotId + "", null);
	}

	public static List<String> getSaveSlots(SQLiteDatabase db) {
		List<String> slotsIds = new ArrayList<String>();
		Cursor cursor = db
				.query(DbHelper.Tables.SAVED_SESSIONS, null, null, null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {

			int id = cursor.getColumnIndex(DbHelper.SavedSessionsColumns.SESSION_ID);
			String slotId = cursor.getString(1);
			slotsIds.add(slotId);

			cursor.moveToNext();
		}
		return slotsIds;
	}
}
