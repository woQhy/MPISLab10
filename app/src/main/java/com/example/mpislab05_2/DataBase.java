package com.example.mpislab05_2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "lab_5_db.db";
    private static final int DATABASE_VERSION = 1;

    public DataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE notes (_id INTEGER PRIMARY KEY AUTOINCREMENT, description TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS notes");
        onCreate(db);
    }

    public Cursor getAllNotes() {
        return getReadableDatabase().rawQuery("SELECT * FROM notes", null);
    }

    public void addNote(String description) {
        ContentValues values = new ContentValues();
        values.put("description", description);
        getWritableDatabase().insert("notes", null, values);
    }

    public void deleteNoteById(int id) {
        getWritableDatabase().delete("notes", "_id=?", new String[]{String.valueOf(id)});
    }

    public void updateNoteById(int id, String description) {
        ContentValues values = new ContentValues();
        values.put("description", description);
        getWritableDatabase().update("notes", values, "_id=?", new String[]{String.valueOf(id)});
    }

    public int getNotesCount() {
        String countQuery = "SELECT COUNT(*) FROM notes";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = 0;

        if (cursor.moveToFirst()) {
            count = cursor.getInt(0);
        }

        cursor.close();
        db.close();
        return count;
    }
}
