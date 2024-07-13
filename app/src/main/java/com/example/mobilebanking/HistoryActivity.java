package com.example.mobilebanking;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {
    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;
    private RecyclerView recyclerView;
    private HistoryAdapter historyAdapter;
    private Button homeButton;
    String accountNumber2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_history);

        dbHelper = new DatabaseHelper(this);
        db = dbHelper.getReadableDatabase();

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        homeButton = findViewById(R.id.home_button);

            Button reloadButton = findViewById(R.id.home_button);
            reloadButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fetchDebitAccount();
                    Intent intent = new Intent(HistoryActivity.this, MainActivity.class);
                    intent.putExtra("ACCOUNT_NUMBER", accountNumber2); // Pass the account number to MainActivity

                    startActivity(intent);
                }
            });



        loadHistory();
    }

    private void loadHistory() {
        List<HistoryItem> historyItems = new ArrayList<>();

        String[] columns = {
                DatabaseHelper.COLUMN_RECIPIENT_ACCOUNT,
                DatabaseHelper.COLUMN_RECIPIENT_NAME,
                DatabaseHelper.COLUMN_AMOUNT,
                DatabaseHelper.COLUMN_DESCRIPTION
        };

        Cursor cursor = db.query(
                DatabaseHelper.TABLE_HISTORY,
                columns,
                null,
                null,
                null,
                null,
                null
        );

        if (cursor != null && cursor.moveToFirst()) {
            do {
                String recipientAccount = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_RECIPIENT_ACCOUNT));
                String recipientName = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_RECIPIENT_NAME));
                String amount = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_AMOUNT));
                String description = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_DESCRIPTION));

                historyItems.add(new HistoryItem(recipientAccount, recipientName, amount, description));
            } while (cursor.moveToNext());

            cursor.close();
        }

        historyAdapter = new HistoryAdapter(historyItems);
        recyclerView.setAdapter(historyAdapter);
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

    @Override
    protected void onDestroy() {
        db.close();
        super.onDestroy();
    }
}
