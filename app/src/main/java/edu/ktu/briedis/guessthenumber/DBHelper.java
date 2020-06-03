package edu.ktu.briedis.guessthenumber;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;


public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyDBName.db";
    public static final String CONTACTS_TABLE_NAME = "results";
    public static final String CONTACTS_COLUMN_ID = "id";
    public static final String CONTACTS_COLUMN_TEXT1 = "playerName";
    public static final String CONTACTS_COLUMN_TEXT2 = "playerAge";
    public static final String CONTACTS_COLUMN_TEXT3 = "difficulty";

    private HashMap hp;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table results" +
                        "(id INTEGER PRIMARY KEY AUTOINCREMENT, field1 text, field2 int, field3 int)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS results");
        onCreate(db);
    }

    public boolean insertData(String text1, int text2, int text3, int text4) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("field1", text1);
        contentValues.put("field2", text2);
        contentValues.put("field3", text3);
        db.insert("results", null, contentValues);
        return true;
    }

    public List<Results> allResults() {
        List<Results> results = new ArrayList<>();
        String selectQuery = "SELECT  * FROM results";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Results result = new Results();
                result.setId(cursor.getInt(0));
                result.setName(cursor.getString(1));
                result.setAge(cursor.getInt(2));
                result.setDifficulty(cursor.getInt(3));
                results.add(result);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return results;
    }
}