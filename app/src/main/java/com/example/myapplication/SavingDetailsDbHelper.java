package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class SavingDetailsDbHelper extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "saving";
    public static final String COLUMN_NAME_DATE = "date";
    public static final String COLUMN_NAME_INCOME = "income";
    public static final String COLUMN_NAME_EXPENSE = "expense";
    public static final String COLUMN_NAME_SAVING= "saving";
    public static final String ID = "id";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    ID + " INTEGER PRIMARY KEY," +
                    COLUMN_NAME_DATE + " TEXT," +
                    COLUMN_NAME_INCOME + " TEXT," +
                    COLUMN_NAME_EXPENSE + " TEXT,"+
                    COLUMN_NAME_SAVING + " TEXT )";
    private static final String SQL_CREATE_ENTRIES2_totalIncome =
            "CREATE TABLE " + "totalIncome" + " (" +
                    ID + " INTEGER PRIMARY KEY," +
                    COLUMN_NAME_DATE + " TEXT," +
                    COLUMN_NAME_INCOME + " TEXT)" ;

    private static final String SQL_CREATE_ENTRIES2_totalExpense =
            "CREATE TABLE " + "totalExpense" + " (" +
                    ID + " INTEGER PRIMARY KEY," +
                    COLUMN_NAME_DATE + " TEXT," +
                    COLUMN_NAME_EXPENSE + " TEXT)" ;
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    private static final String SQL_DELETE_ENTRIES_totalIncome =
            "DROP TABLE IF EXISTS " + "totalIncome";


    private static final String SQL_DELETE_ENTRIES_totalExpense =
            "DROP TABLE IF EXISTS " + "totalExpense";
    private static final String SQL_DELETE_ENTRIES22 =
            "DELETE FROM " + TABLE_NAME;

    private static final String SQL_DELETE_ENTRIES_totalIncome33 =
            "DELETE FROM " + "totalIncome";


    private static final String SQL_DELETE_ENTRIES_totalExpense44 =
            "DELETE FROM " + "totalExpense";

    private static final String SQL_CREATE_ENTRIES1= "INSERT INTO totalIncome (date, income) SELECT date, sum(income) as expense FROM income1 GROUP BY date";
    private static final String SQL_CREATE_ENTRIES2= "INSERT INTO totalExpense (date, expense) SELECT date, sum(expense) as espese FROM expense GROUP BY date";
    private static final String SQL_CREATE_ENTRIES3= "INSERT INTO saving(date, income, expense) SELECT totalIncome.date, totalIncome.income, totalExpense.expense FROM  totalIncome LEFT JOIN totalExpense ON totalIncome.date = totalExpense.date";




    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION =109;
    public static final String DATABASE_NAME = "Money1.db";

    public SavingDetailsDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
        db.execSQL(SQL_CREATE_ENTRIES2_totalIncome);
        db.execSQL(SQL_CREATE_ENTRIES2_totalExpense);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        db.execSQL(SQL_DELETE_ENTRIES_totalIncome);
        db.execSQL(SQL_DELETE_ENTRIES_totalExpense);

        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }


    public void add(){

        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(SQL_DELETE_ENTRIES22);
        db.execSQL(SQL_DELETE_ENTRIES_totalIncome33);
        db.execSQL(SQL_DELETE_ENTRIES_totalExpense44);

        db.execSQL(SQL_CREATE_ENTRIES1);
        db.execSQL(SQL_CREATE_ENTRIES2);
        db.execSQL(SQL_CREATE_ENTRIES3);


    }
    public ArrayList<SavingDetails> get(){
        // Gets the data repository in get mode
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<SavingDetails>  newArrayList= new ArrayList<>();
      Cursor cursor=  db.rawQuery("SELECT * FROM " +TABLE_NAME, null );
      while(cursor.moveToNext()){
        SavingDetails details = new SavingDetails();
          details.setDate(cursor.getString(1));
          details.setIncome(cursor.getInt(2));
          details.setExpense(cursor.getInt(3));
          details.setSaving(cursor.getInt(2)-cursor.getInt(3));
          newArrayList.add(details);


      }
      return   newArrayList;

 }

}