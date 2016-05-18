package com.example.sabod.testapp1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by sabod on 10/26/2015.
 */
public class DatabaseHelperLift extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "LiftHistory.db";
    private static final String TABLE_NAME = "liftHistory_table";
    private static final String UID = "_id";
    private static final String LiftName = "LIFTNAME";
    private static final String LiftDate = "NULL";
    private static final String Sets = "0";
    private static final String Reps = "0";
    private static final String Weight = "0";
    private static final String AMRAP = "YES";
    private static final String Comment = "";
    private static final Integer DATABASE_VERSION = 1;

    public DatabaseHelperLift(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + LiftName + " TEXT, " + LiftDate + " TEXT,"
                 + Sets + " INTEGER," + Reps + " INTEGER," + Weight + " INTEGER, " + AMRAP + " TEXT," + Comment +" TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData (String lift_name, String lift_date, Integer reps, Integer weight, String amrap, String comment) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(LiftName, lift_name);
        contentValues.put(LiftDate, lift_date);
        contentValues.put(Sets, reps);
        contentValues.put(Reps, reps);
        contentValues.put(Weight, weight);
        contentValues.put(AMRAP, amrap);
        contentValues.put(Comment, comment);
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.insert(TABLE_NAME,null,contentValues);

        if (result == -1)
            return false;
        else
            return true;

    }

    public String getData(String lift_name){
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = {UID, LiftName, LiftDate, Sets, Reps, Weight, AMRAP, Comment};
        StringBuffer buffer = new StringBuffer();

        Cursor res = db.query(TABLE_NAME, columns, LiftName +" = '"+lift_name+"'",null,null,null,null);

        if (res.getCount()==0) {
            // Show message
            //ShowMessage("Error","Nothing Found");
            return buffer.toString();
        }
        while (res.moveToNext()){
            int index0 = res.getColumnIndex(UID);
            int index1 = res.getColumnIndex(LiftName);
            int index2 = res.getColumnIndex(LiftDate);
            int index3 = res.getColumnIndex(Sets);
            int index4 = res.getColumnIndex(Reps);
            int index5 = res.getColumnIndex(Weight);
            int index6 = res.getColumnIndex(AMRAP);
            int index7 = res.getColumnIndex(Comment);
            buffer.append("ID: "+res.getString(index0)+"\n");
            buffer.append("Lift Name: "+res.getString(index1)+"\n");
            buffer.append("Lift Date: "+res.getString(index2)+"\n");
            buffer.append("Sets: "+res.getString(index3)+"\n");
            buffer.append("Reps: "+res.getString(index4)+"\n");
            buffer.append("Weight: "+res.getString(index5)+"\n");
            buffer.append("AMRAP: "+res.getString(index6)+"\n\n");
            buffer.append("Comment: "+res.getString(index7)+"\n\n");

        }
        return buffer.toString();
        //Show all data
        //ShowMessage("Data",buffer.toString());


    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }

    public boolean updateData(String id, String lift_name, String lift_date, String reps, String weight, String amrap){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(UID, id);
        contentValues.put(LiftName, lift_name);
        contentValues.put(LiftDate, lift_date);
        contentValues.put(Sets, reps);
        contentValues.put(Reps, reps);
        contentValues.put(Weight, weight);
        contentValues.put(AMRAP, amrap);
        contentValues.put(Comment, amrap);
        db.update(TABLE_NAME, contentValues, "id = ?", new String[]{id});
        return true;
    }

    public Integer deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,"UID = ?",new String[] {id});
    }

}
