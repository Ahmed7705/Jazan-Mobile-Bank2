package com.example.mobilebanking;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper2 extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "mobile_banking.db";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_USERS = "users";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_ACCOUNT_NUMBER = "account_number";
    public static final String COLUMN_TRANSACTION_PASSWORD = "transaction_password";

    public static final String TABLE_HISTORY = "history";
    public static final String COLUMN_RECIPIENT_ACCOUNT = "recipient_account";
    public static final String COLUMN_RECIPIENT_NAME = "recipient_name";
    public static final String COLUMN_AMOUNT = "amount";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_SENDER_NAME = "sender_name";
    public static final String COLUMN_SENDER_ACCOUNT = "sender_account";
    public static final String COLUMN_DATE = "date";

    public DatabaseHelper2(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + " (" +
                COLUMN_USERNAME + " TEXT, " +
                COLUMN_ACCOUNT_NUMBER + " TEXT, " +
                COLUMN_TRANSACTION_PASSWORD + " TEXT)";
        db.execSQL(CREATE_USERS_TABLE);

        String CREATE_HISTORY_TABLE = "CREATE TABLE " + TABLE_HISTORY + " (" +
                COLUMN_RECIPIENT_ACCOUNT + " TEXT, " +
                COLUMN_RECIPIENT_NAME + " TEXT, " +
                COLUMN_AMOUNT + " TEXT, " +
                COLUMN_DESCRIPTION + " TEXT, " +
                COLUMN_SENDER_NAME + " TEXT, " +
                COLUMN_SENDER_ACCOUNT + " TEXT, " +
                COLUMN_DATE + " INTEGER)";
        db.execSQL(CREATE_HISTORY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HISTORY);
        onCreate(db);
    }
}
