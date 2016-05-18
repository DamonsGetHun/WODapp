package com.example.sabod.testapp1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by sabod on 9/29/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Student.db";
    private static final String TABLE_NAME = "student_table";
    private static final String UID = "_id";
    private static final String Name = "NAME";
    private static final String Sername = "SURNAME";
    private static final String Marks = "MARKS";
    private static final Integer DATABASE_VERSION = 2;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + Name + " TEXT, " + Sername + " TEXT, " + Marks + " INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData (String name, String surname, String marks) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Name, name);
        contentValues.put(Sername, surname);
        contentValues.put(Marks, marks);
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.insert(TABLE_NAME,null,contentValues);

        if (result == -1)
            return false;
        else
            return true;

    }

    public String getData(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = {UID, Name, Sername, Marks};
        StringBuffer buffer = new StringBuffer();

        Cursor res = db.query(TABLE_NAME, columns, Name +" = '"+name+"'",null,null,null,null);

        if (res.getCount()==0) {
            // Show message
            //ShowMessage("Error","Nothing Found");
            return buffer.toString();
        }
        while (res.moveToNext()){
            int index0 = res.getColumnIndex(UID);
            int index1 = res.getColumnIndex(Name);
            int index2 = res.getColumnIndex(Sername);
            int index3 = res.getColumnIndex(Marks);
            buffer.append("ID: "+res.getString(index0)+"\n");
            buffer.append("Name: "+res.getString(index1)+"\n");
            buffer.append("Surname: "+res.getString(index2)+"\n");
            buffer.append("Marks: "+res.getString(index3)+"\n\n");

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

    public boolean updateData(String id,String name, String surname, String marks ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(UID, id);
        contentValues.put(Name, name);
        contentValues.put(Sername, surname);
        contentValues.put(Marks, marks);
        db.update(TABLE_NAME,contentValues, "id = ?", new String[] {id});
        return true;
    }

    public Integer deleteData(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,"Name = ?",new String[] {name});
    }
}
