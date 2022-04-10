package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "Login.db";

    public DBHelper(Context context) {
        super(context,  "Login.db",  null,  1);
    }




    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table users (contact_id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "\tfirst_name TEXT NOT NULL,\n" +
                "\tlast_name TEXT NOT NULL,\n" +
                "\tpassword TEXT NOT NULL,\n" +
                "\tphone TEXT NOT NULL UNIQUE)");

        MyDB.execSQL("create Table Friends (friend_id Integer PRIMARY KEY AUTOINCREMENT,first_name text NOT NULL,last_name text NOT NULL,mobile_no text NOT NULL UNIQUE,email text NOT NULL)");

    }



    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("Drop Table if exists users");
    }
    public boolean insertData(String fisrtName,String lastName,String mobileno, String password){
      SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("first_name", fisrtName);
        contentValues.put("last_name", lastName);
        contentValues.put("password", password);
        contentValues.put("phone", mobileno);
        long result = MyDB.insert( "users", null, contentValues);
        if (result==-1)
        {
            return false;
        }
        else{
          return true;
        }
    }

    public boolean insertDataFriends(String firstName,String lastName,String mobileno, String email){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("first_name", firstName);
        contentValues.put("last_name", lastName);
        contentValues.put("mobile_no", mobileno);
        contentValues.put("email", email);
        long result = MyDB.insert( "Friends", null, contentValues);
        if (result==-1)
        {
            return false;

        }
        else{
            return true;
        }

    }
    public Boolean checkfriend (String phone){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from Friends where mobile_no = ?", new String[]{phone});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Cursor selectAllFriend(){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from Friends",null);
        if (cursor.getCount() > 0)
            return cursor;
      else
          return cursor;
    }

    public boolean deleteFriend(String contact_id){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        int id=Integer.parseInt(contact_id);
        long result=MyDB.delete("Friends", "friend_id=?", new String[]{contact_id});
        if (result==-1)
        {
            return false;

        }
        else{
            return true;
        }
    }

    public Boolean checkusername(String phone) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where phone = ?", new String[]{phone});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkusernamepassword(String mobileno, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where phone = ? and password = ?", new String[] {mobileno,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }


}
