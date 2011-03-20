package com.superdeveloperz.supergame;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLiteHelper extends SQLiteOpenHelper {

	public SQLiteHelper(Context context) {
		super(context, "xxx.db", null, 1);

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE TEXTS (id INTEGER PRIMARY KEY, name VARCHAR, text TEXT)");
		ContentValues contentValues = createText(1, "xxx",
				"Как я выебал вашу маму");
		long insert = db.insert("TEXTS", null, contentValues);
		
		Log.e("SQLite.onCreate", "insert = " + insert);

	}

	private ContentValues createText(Integer id, String name, String text) {
		ContentValues contentValues = new ContentValues();
		contentValues.put("id", id);
		contentValues.put("name", name);
		contentValues.put("text", text);
		return contentValues;
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w("Example",
				"Upgrading database, this will drop tables and recreate.");
		db.execSQL("DROP TABLE IF EXISTS TEXTS");
		onCreate(db);
	}

}
