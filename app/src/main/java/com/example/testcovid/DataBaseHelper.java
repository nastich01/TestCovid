package com.example.testcovid;

import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;

public class DataBaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "hospitals.db"; // название бд
    private static final int SCHEMA = 1; // версия базы данных
    static final String TABLE = "hospitals"; // название таблицы в бд
    // названия столбцов
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_ADDRESS = "address";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_LATITUDE = "latitude";
    public static final String COLUMN_LONGITUDE = "longitude";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + TABLE + " (" + COLUMN_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_NAME
                + " TEXT, " + COLUMN_ADDRESS + " TEXT, " + COLUMN_PRICE + " TEXT, " + COLUMN_PHONE + " TEXT, " + COLUMN_LATITUDE + " TEXT, "+ COLUMN_LONGITUDE + " TEXT);");
        // добавление начальных данных
        db.execSQL("INSERT INTO "+ TABLE +" (" + COLUMN_NAME
                + ", " + COLUMN_ADDRESS + ", " + COLUMN_PRICE +", "+COLUMN_PHONE+", " + COLUMN_LATITUDE +", "+COLUMN_LONGITUDE+ ") VALUES ('Инвитро','ул. Татарстан, 16','1700', '8 (800) 200-36-30','55.782430', '49.114894');");
        db.execSQL("INSERT INTO "+ TABLE +" (" + COLUMN_NAME
                + ", " + COLUMN_ADDRESS + ", " + COLUMN_PRICE +", "+COLUMN_PHONE+", " + COLUMN_LATITUDE +", "+COLUMN_LONGITUDE+ ") VALUES ('Ситилаб','ул. Карла Маркса, 53Б','1600', '8 (800) 100-36-30','55.795575', '49.133902');");
        db.execSQL("INSERT INTO "+ TABLE +" (" + COLUMN_NAME
                + ", " + COLUMN_ADDRESS + ", " + COLUMN_PRICE +", "+COLUMN_PHONE+", " + COLUMN_LATITUDE +", "+COLUMN_LONGITUDE+ ") VALUES ('Гемотест','ул. Парижской Коммуны, 4','1500', '8 (800) 550-13-13','55.784860', '49.115990');");
        db.execSQL("INSERT INTO "+ TABLE +" (" + COLUMN_NAME
                + ", " + COLUMN_ADDRESS + ", " + COLUMN_PRICE +", "+COLUMN_PHONE+", " + COLUMN_LATITUDE +", "+COLUMN_LONGITUDE+ ") VALUES ('Медэксперт','ул. Муштари, 12А','1900', '+7 (843) 590-33-33','55.790140', '49.136633');");

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,  int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE);
        onCreate(db);
    }
}

