package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.telecom.Call;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class IncomeDetailsDbHelper extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "income1";
    public static final String COLUMN_NAME_SOURCE = "source";
    public static final String COLUMN_NAME_AMOUNT = "income";
    public static final String COLUMN_NAME_DATE = "date";
    public static final String ID = "id";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    ID + " INTEGER PRIMARY KEY," +
                    COLUMN_NAME_SOURCE + " TEXT," +
                    COLUMN_NAME_AMOUNT + " TEXT,"+
                    COLUMN_NAME_DATE + " TEXT )";
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION =109;
    public static final String DATABASE_NAME = "Money1.db";

    public IncomeDetailsDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }


    public void add(String source,String amount, String date){
        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();
        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_SOURCE, source);
        values.put(COLUMN_NAME_AMOUNT, amount);
        values.put(COLUMN_NAME_DATE, date);
        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(TABLE_NAME, null, values);
    }
    public ArrayList<IncomeDetails> get(){
        // Gets the data repository in get mode
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<IncomeDetails>  newArrayList= new ArrayList<>();
      Cursor cursor=  db.rawQuery("SELECT * FROM " +TABLE_NAME, null );
      while(cursor.moveToNext()){
        IncomeDetails details = new IncomeDetails();
          details.setIncomeDetailId(cursor.getInt(0));
          details.setSourceOfIncome(cursor.getString(1));
          details.setAmount(cursor.getString(2));
          details.setDateOfAddIncome(cursor.getString(3));
          newArrayList.add(details);

      }
      return   newArrayList;

 }
    public void update(String source,String amount, String date, String id) {

        // calling a method to get writable database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are passing all values
        // along with its key and value pair.
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_SOURCE, source);
        values.put(COLUMN_NAME_AMOUNT, amount);
        values.put(COLUMN_NAME_DATE, date);

        // on below line we are calling a update method to update our database and passing our values.
        // and we are comparing it with name of our course which is stored in original name variable.
        db.update(TABLE_NAME, values, "ID=" +id, null);

        db.close();
    }
public int getTotalNumberOfId () {
    SQLiteDatabase db = this.getWritableDatabase();
    Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    ArrayList<Integer> itemIds = new ArrayList<>();

    while (cursor.moveToNext()) {
        long itemId = cursor.getLong(
                cursor.getColumnIndexOrThrow(ID));
        itemIds.add((int) itemId);
    }
    System.out.println("kali##########"+itemIds.size());
    return itemIds.size();

}


    public void delete(String id) {
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are calling a method to delete our
        // course and we are comparing it with our course name.
        db.delete(TABLE_NAME, "ID=" +id, null);
        db.close();

    }
}