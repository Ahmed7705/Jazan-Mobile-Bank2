package com.example.mobilebanking;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ProfileActivity extends AppCompatActivity {
    private TextView backbtn;
    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;
    String accountNumber2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile); // This should be called before findViewById
        Toolbar toolbar = findViewById(R.id.toolbar);
        dbHelper = new DatabaseHelper(this);
        db = dbHelper.getReadableDatabase();
        setSupportActionBar(toolbar);

        backbtn = findViewById(R.id.backbtn); // Move this after setContentView

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchDebitAccount();

                Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
                intent.putExtra("ACCOUNT_NUMBER", accountNumber2); // Pass the account number to MainActivity

                startActivity(intent);

            }
        });
    }
    private void fetchDebitAccount() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(DatabaseHelper.TABLE_USERS,
                new String[]{DatabaseHelper.COLUMN_ACCOUNT_NUMBER},
                null, null, null, null, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                String accountNumber = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_ACCOUNT_NUMBER));
                accountNumber2=accountNumber;
            }
            cursor.close();
        }
        db.close();
    }

}
