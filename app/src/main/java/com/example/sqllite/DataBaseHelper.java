package com.example.sqllite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {
    public DataBaseHelper(Context context){
        //Наименование БД;  Version >= 1
        super(context, "Userdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Запрос на создание таблицы в БД
        sqLiteDatabase.execSQL("create Table UserInfo(name TEXT primary key, " + "phone TEXT, date_of_birth TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //Условие на удаление таблицы UserInfo
        sqLiteDatabase.execSQL("drop Table if exists UserInfo");
    }

    /**
     * Метод добавления данных пользователя в таблицу UserInfo
     * @param name - Имя
     * @param phone - Номер телефона
     * @param date_of_birth - Дата рождения
     * @return - Возвращаем true/false (зависит от результата)
     */

    public boolean insert(String name, String phone, String date_of_birth){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("phone", phone);
        contentValues.put("date_of_birth", date_of_birth);
        long result = DB.insert("UserInfo", null, contentValues);
        return result != -1;
    }

    public boolean Update(String name, String phone, String date_of_birth){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("phone", phone);
        contentValues.put("date_of_birth", date_of_birth);
        long result = DB.update("UserInfo", contentValues, "name=?", new String[] {name});
        return result != -1;
    }

    public boolean Delete(String name){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        long result = DB.delete("UserInfo", "name=?", new String[] {name});
        return result != -0;
    }

    /***/

    /**
     * Выполняем запрос Select для выборки всех данных из БД
     * @return
     */
    public Cursor getdata(){
        SQLiteDatabase DB = this.getWritableDatabase();
        return DB.rawQuery("Select * from UserInfo", null);
    }


}
