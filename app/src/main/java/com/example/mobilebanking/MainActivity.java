package com.example.mobilebanking;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private TextView balanceTextView;
    private TextView accountNumberTextView;
    private TextView nameTextView;
    private TextView validThruTextView;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DatabaseHelper(this);

        balanceTextView = findViewById(R.id.balanceTextView);
        accountNumberTextView = findViewById(R.id.accountNumberTextView);
        nameTextView = findViewById(R.id.nameTextView);
        validThruTextView = findViewById(R.id.validThruTextView);

        // Get account number from intent
        String accountNumber = getIntent().getStringExtra("ACCOUNT_NUMBER");

        // Check if account number is provided
        if (accountNumber != null) {
            // Fetch data from the database
            fetchDataFromDatabase(accountNumber);
        } else {
            Toast.makeText(this, "Account number is missing", Toast.LENGTH_SHORT).show();
            finish(); // Close the activity if no account number is provided
        }

        // Set up button click listeners
        findViewById(R.id.copyButton).setOnClickListener(v -> copyToClipboard(accountNumberTextView.getText().toString()));

        findViewById(R.id.mTransferButton).setOnClickListener(v -> navigateToPage(TransferActivity.class));
        findViewById(R.id.mPaymentButton).setOnClickListener(v -> navigateToPage(MPaymentActivity.class));
        findViewById(R.id.mCommerceButton).setOnClickListener(v -> navigateToPage(MCommerceActivity.class));
        findViewById(R.id.flazzButton).setOnClickListener(v -> navigateToPage(HistoryActivity.class));

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menuHome:
                        navigateToPage(MainActivity.class);
                        return true;
                    case R.id.menuHistory:
                        navigateToPage(HistoryActivity.class);
                        return true;
                    case R.id.menuNotification:
                        navigateToPage(NotificationActivity.class);
                        return true;
                    case R.id.menuProfile:
                        navigateToPage(ProfileActivity.class);
                        return true;
                }
                return false;
            }
        });
    }

    private void fetchDataFromDatabase(String accountNumber) {
        SQLiteDatabase db = null;
        Cursor cursor = null;

        try {
            db = dbHelper.getReadableDatabase();
            String[] columns = {
                    DatabaseHelper.COLUMN_ACCOUNT_NUMBER,
                    DatabaseHelper.COLUMN_USERNAME,
                    DatabaseHelper.COLUMN_BALANCE,
                    DatabaseHelper.COLUMN_VALID_THRU // Assuming you add this column to your database
            };
            String selection = DatabaseHelper.COLUMN_ACCOUNT_NUMBER + " = ?";
            String[] selectionArgs = {accountNumber};

            cursor = db.query(DatabaseHelper.TABLE_USERS, columns, selection, selectionArgs, null, null, null);

            if (cursor != null && cursor.moveToFirst()) {
                String balance = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_BALANCE));
                String username = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_USERNAME));
                String validThru = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_VALID_THRU));

                balanceTextView.setText(balance);
                accountNumberTextView.setText(accountNumber);
                nameTextView.setText(username);
                validThruTextView.setText(validThru);
            } else {
                Toast.makeText(this, "User data not found", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Error fetching data: " + e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
                db.close();
            }
        }
    }

    private void copyToClipboard(String text) {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("Account Number", text);
        clipboard.setPrimaryClip(clip);
        Toast.makeText(this, "Account number copied to clipboard", Toast.LENGTH_SHORT).show();
    }

    private void navigateToPage(Class<?> activityClass) {
        Intent intent = new Intent(this, activityClass);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbHelper.close();
    }
}
