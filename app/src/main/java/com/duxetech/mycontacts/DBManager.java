package com.duxetech.mycontacts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by Karthik Swamy on 12/11/18.
 */

class DBManager extends SQLiteOpenHelper {

    SQLiteDatabase db;
    Contacts contact;
    List<Contacts> contact_list;
    String table_name="contacts";
    String login = "login";
    public DBManager(Context context) {
        super(context, "contacts.db", null, '1');
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table if not exists "+table_name+"(first_name varchar, last_name varchar, phone int, mail varchar)");
        db.execSQL("create table if not exists "+login+"(name varchar, password varchar, mail varchar)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
     // db.execSQL("alter table contacts add column address varchar");


    }

    Cursor read(){

            db = getReadableDatabase();
            Cursor c = db.rawQuery("select * from login", null);
            return c;
    }

    boolean insert(String name, String pass, String mail){
        db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("password", pass);
        cv.put("mail",mail);
        return db.insert("login", null,cv)!=-1;

    }


    boolean checkUserRegistered(String name){

        Cursor c = read();
        while(c.moveToNext()){
            if(name.equals(c.getString(c.getColumnIndex("name")))){

            return true;
            }
        } return false;
    }
    boolean validateLogin(String name, String pass){
        Cursor c = read();
        while(c.moveToNext()){
            if(name.equals(c.getString(c.getColumnIndex("name")))
                    &&pass.equals(c.getString(c.getColumnIndex("password")))){
                return true;
            }
        }
        return false;


    }

    public List<Contacts> getAllContacts() {
        db = getReadableDatabase();
        Cursor c  = db.rawQuery("select * from "+table_name+"",null);
        contact_list = new ArrayList<>();
        while (c.moveToNext()){
            contact = new Contacts();
            contact.setFirst_name(c.getString(0));
            contact.setLast_name(c.getString(1));
            contact.setMobile(c.getString(2));
            contact.setMail(c.getString(3));
//            contact.setAddress(c.getString(4));
            contact_list.add(contact);
        }
        return contact_list;
    }

    boolean checkPhoneExists(String phone) {
        db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from "+table_name+"", null);
        while (c.moveToNext()) {
            if (phone.equals(c.getString(c.getColumnIndex("phone")))) {

                return true;
            }
        }
        return false;
    }

    public boolean insertContact(Contacts contact){
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("first_name",contact.getFirst_name());
        cv.put("last_name",contact.getLast_name());
        cv.put("phone",contact.getMobile());
        cv.put("mail",contact.getMail());
     //   cv.put("address",contact.getAddress());
        return db.insert(table_name,null,cv)!=1;

    }

    void deleteContacts(){
        db.execSQL("delete from "+table_name+"");
        db.close();
    }

    boolean insert(){
        db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("first_name","a");
        cv.put("last_name","b");
        cv.put("phone","c");
        cv.put("mail","d");
      //  cv.put("address","e");
        return db.insert("abc",null,cv)!=1;


    }

}
