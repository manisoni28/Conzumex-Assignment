package com.mani.conzumex.assignment.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by deepmetha on 8/28/16.
 */
public class SqliteHelper extends SQLiteOpenHelper {
    // Constants
    public static final String DatabaseName = "ActivityConzumex.db";
    public static final String TableName = "ActivityTask";
    public static final String Col_1 = "ActivityID";
    public static final String Col_2 = "ActivityName";
    public static final String Col_3 = "ActivityIntensity";
    public static final String Col_4 = "ActivityStartTimeDate";
    public static final String Col_5 = "ActivityEndTimeDate";

    // Create DB to store To Do Tasks
    public SqliteHelper(Context context) {
        super(context, DatabaseName, null, 1);

    }

    // Create Table
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "create table if not exists " + TableName + " ( ActivityID INTEGER PRIMARY KEY AUTOINCREMENT, ActivityName TEXT, ActivityIntensity TEXT, ActivityStartTimeDate TEXT, ActivityEndTimeDate TEXT)";
        db.execSQL(createTableQuery);
    }

    // Drop Table for new table creation
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TableName);
        onCreate(db);
    }

    // Insert into Table
    public boolean insertInto(ContentValues cv) {
        SQLiteDatabase db = this.getWritableDatabase();
        long results = db.insert(TableName, null, cv);
        if (results == -1) {
            return false;
        } else {
            return true;
        }
    }

    // Select * from Table i.e get all data
    public Cursor selectAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "select * from " + TableName;
        Cursor result = db.rawQuery(query, null);
        return result;
    }

}
