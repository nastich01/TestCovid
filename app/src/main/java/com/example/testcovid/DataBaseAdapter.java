package com.example.testcovid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;

public class DataBaseAdapter {

    private DataBaseHelper dbHelper;
    private SQLiteDatabase database;

    public DataBaseAdapter(Context context){
        dbHelper = new DataBaseHelper(context.getApplicationContext());
    }

    public DataBaseAdapter open(){
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        dbHelper.close();
    }

    private Cursor getAllEntries(){
        String[] columns = new String[] {DataBaseHelper.COLUMN_ID, DataBaseHelper.COLUMN_NAME, DataBaseHelper.COLUMN_ADDRESS, DataBaseHelper.COLUMN_PRICE, DataBaseHelper.COLUMN_PHONE, DataBaseHelper.COLUMN_LATITUDE, DataBaseHelper.COLUMN_LONGITUDE};
        return  database.query(DataBaseHelper.TABLE, columns, null, null, null, null, null);
    }

    public List<Hospital> getStores(){
        ArrayList<Hospital> hospitals = new ArrayList<>();
        Cursor cursor = getAllEntries();
        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseHelper.COLUMN_ID));
            String name = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseHelper.COLUMN_NAME));
            String address = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseHelper.COLUMN_ADDRESS));
            String price = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseHelper.COLUMN_PRICE));
            String phone = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseHelper.COLUMN_PHONE));
            double latitude = cursor.getDouble(cursor.getColumnIndexOrThrow(DataBaseHelper.COLUMN_LATITUDE));
            double longitude = cursor.getDouble(cursor.getColumnIndexOrThrow(DataBaseHelper.COLUMN_LONGITUDE));
            hospitals.add(new Hospital(id, name, address, price, phone, latitude, longitude));
        }
        cursor.close();
        return  hospitals;
    }

    public long getCount(){
        return DatabaseUtils.queryNumEntries(database, DataBaseHelper.TABLE);
    }

    public Hospital getStore(long id){
        Hospital hospital = null;
        String query = String.format("SELECT * FROM %s WHERE %s=?",DataBaseHelper.TABLE, DataBaseHelper.COLUMN_ID);
        Cursor cursor = database.rawQuery(query, new String[]{ String.valueOf(id)});
        if(cursor.moveToFirst()){
            String name = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseHelper.COLUMN_NAME));
            String address = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseHelper.COLUMN_ADDRESS));
            String price = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseHelper.COLUMN_PRICE));
            String phone = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseHelper.COLUMN_PHONE));
            double latitude = cursor.getDouble(cursor.getColumnIndexOrThrow(DataBaseHelper.COLUMN_LATITUDE));
            double longitude = cursor.getDouble(cursor.getColumnIndexOrThrow(DataBaseHelper.COLUMN_LONGITUDE));
            hospital = new Hospital(id, name, address, price, phone, latitude, longitude);
        }
        cursor.close();
        return  hospital;
    }

    public long insert(Hospital hospital){

        ContentValues cv = new ContentValues();
        cv.put(DataBaseHelper.COLUMN_NAME, hospital.getName());
        cv.put(DataBaseHelper.COLUMN_ADDRESS, hospital.getAddress());
        cv.put(DataBaseHelper.COLUMN_PRICE, hospital.getPrice());
        cv.put(DataBaseHelper.COLUMN_PHONE, hospital.getPhone());
        cv.put(DataBaseHelper.COLUMN_LATITUDE, hospital.getLatitude());
        cv.put(DataBaseHelper.COLUMN_LONGITUDE, hospital.getLongitude());

        return  database.insert(DataBaseHelper.TABLE, null, cv);
    }

    public long delete(long id){

        String whereClause = "_id = ?";
        String[] whereArgs = new String[]{String.valueOf(id)};
        return database.delete(DataBaseHelper.TABLE, whereClause, whereArgs);
    }

    public long update(Hospital hospital){

        String whereClause = DataBaseHelper.COLUMN_ID + "=" + hospital.getId();
        ContentValues cv = new ContentValues();
        cv.put(DataBaseHelper.COLUMN_NAME, hospital.getName());
        cv.put(DataBaseHelper.COLUMN_ADDRESS, hospital.getAddress());
        cv.put(DataBaseHelper.COLUMN_PRICE, hospital.getPrice());
        cv.put(DataBaseHelper.COLUMN_PHONE, hospital.getPhone());
        cv.put(DataBaseHelper.COLUMN_LATITUDE, hospital.getLatitude());
        cv.put(DataBaseHelper.COLUMN_LONGITUDE, hospital.getLongitude());
        return database.update(DataBaseHelper.TABLE, cv, whereClause, null);
    }
}

