package com.example.manasaa.remindertask;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sowjanya on 20-02-2017.
 */

public class MySQLiteHelper extends SQLiteOpenHelper {
    private static final String TAG="MySQLiteHelperclass";
    public static final String TABLE_NAME = "reminders";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TASKTITLE = "tasknamecolumn";
    public static final String COLUMN_TASKTIME = "tasktimecolumn";
    private static final String DATABASE_NAME = "reminders.db";
    private static final int DATABASE_VERSION = 1;
    private Context context;

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table " + TABLE_NAME +
            "( " + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_TASKTITLE  + " text not null,"
            + COLUMN_TASKTIME + " text not null);";

    //Constructor
    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }

    //Creating Table
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }
    //Upgrading Database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }
    //Adding/Inserting  Remainder
    void add(Reminder reminder){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(COLUMN_TASKTIME,reminder.getTimeTask());
        values.put(COLUMN_TASKTITLE,reminder.getNameTask());
        db.insert(TABLE_NAME,null,values);
        db.close();
    }
    // Getting All Remainders
    public List<Reminder> getAllReminders(){
        List<Reminder> remindersList= new ArrayList<Reminder>();
        //Select Query

        String selectQuery="SELECT * FROM "+TABLE_NAME;
        SQLiteDatabase db =this.getWritableDatabase();
        Cursor cursorReminder = db.rawQuery(selectQuery+" ORDER BY " + COLUMN_ID + " DESC",null);
        if(cursorReminder.moveToFirst()){
            do{
                Reminder reminderObj = new Reminder();
                reminderObj.setId(Integer.parseInt(cursorReminder.getString(0)));
                reminderObj.setNameTask(cursorReminder.getString(1));
                reminderObj.setTimeTask(cursorReminder.getString(2));
                remindersList.add(reminderObj);
            }while (cursorReminder.moveToNext());
        }
        return remindersList;
    }
    // Remainders Count
    public int getRemindersCount() {
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        //cursor.close();
        return cursor.getCount();
    }
    //delete a remider
    public void deleteReminder(int id){
        SQLiteDatabase db =this.getWritableDatabase();
        db.execSQL("delete from "+TABLE_NAME+" where _id='"+id+"'");
        //db.delete(TABLE_NAME,COLUMN_ID, new String[] { String.valueOf(id) });

    }
    //

    public Cursor getReminders(String id, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder sqliteQueryBuilder = new SQLiteQueryBuilder();
        sqliteQueryBuilder.setTables(TABLE_NAME);

        if(id != null) {
            sqliteQueryBuilder.appendWhere("_id" + " = " + id);
        }

        if(sortOrder == null || sortOrder == "") {
            sortOrder = COLUMN_ID;
        }
        Cursor cursor = sqliteQueryBuilder.query(getReadableDatabase(),
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder);
        return cursor;
    }




}
