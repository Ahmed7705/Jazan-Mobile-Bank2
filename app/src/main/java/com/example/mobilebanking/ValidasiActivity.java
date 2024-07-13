package com.example.mobilebanking;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ValidasiActivity extends AppCompatActivity {
    private TextView recipientAccount, recipientName, amount, description;
    private TextView bank, senderName, senderAccount, nominal, free, total;
    private Button confirmButton;
    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;
    private String senderAccountNumber;
    private float senderBalance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validasi);

        dbHelper = new DatabaseHelper(this);
        db = dbHelper.getReadableDatabase();

        recipientAccount = findViewById(R.id.recipient_account);
        recipientName = findViewById(R.id.recipient_name);
        amount = findViewById(R.id.amount);
        description = findViewById(R.id.description);
        bank = findViewById(R.id.bank);
        senderName = findViewById(R.id.sender_name);
        senderAccount = findViewById(R.id.sender_account);
        free = findViewById(R.id.free);
        total = findViewById(R.id.total);
        confirmButton = findViewById(R.id.confirm_button);

        Intent intent = getIntent();
        recipientAccount.setText(intent.getStringExtra("recipient_account"));
        recipientName.setText(intent.getStringExtra("recipient_name"));
        amount.setText(intent.getStringExtra("amount"));
        description.setText(intent.getStringExtra("description"));

        // Fetch sender details from the database
        fetchSenderDetails();
        free.setText("0.5");
        calculateTotal();

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveTransaction();
            }
        });
    }

    private void fetchSenderDetails() {
        String[] columns = {
                DatabaseHelper.COLUMN_USERNAME,
                DatabaseHelper.COLUMN_ACCOUNT_NUMBER,
                DatabaseHelper.COLUMN_BALANCE
        };

        Cursor cursor = db.query(
                DatabaseHelper.TABLE_USERS,
                columns,
                null,
                null,
                null,
                null,
                null
        );

        if (cursor != null && cursor.moveToFirst()) {
            String username = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_USERNAME));
            senderAccountNumber = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ACCOUNT_NUMBER));
            senderBalance = cursor.getFloat(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_BALANCE));

            senderName.setText(username);
            senderAccount.setText(senderAccountNumber);

            cursor.close();
        } else {
            senderName.setText("Unknown");
            senderAccount.setText("Unknown");
        }
    }
    private void calculateTotal() {
        float amountValue = Float.parseFloat(amount.getText().toString());
        float freeValue = Float.parseFloat(free.getText().toString());
        total.setText(String.valueOf(amountValue + freeValue));
    }
    private void saveTransaction() {
        float transactionAmount = Float.parseFloat(amount.getText().toString());

        if (senderBalance >= transactionAmount) {
            // Update sender's balance
            senderBalance -= transactionAmount;
            ContentValues values = new ContentValues();
            values.put(DatabaseHelper.COLUMN_BALANCE, senderBalance);

            int rowsAffected = db.update(DatabaseHelper.TABLE_USERS, values,
                    DatabaseHelper.COLUMN_ACCOUNT_NUMBER + " = ?", new String[]{senderAccountNumber});

            if (rowsAffected > 0) {
                // Save transaction details
                values = new ContentValues();
                values.put(DatabaseHelper.COLUMN_RECIPIENT_ACCOUNT, recipientAccount.getText().toString());
                values.put(DatabaseHelper.COLUMN_RECIPIENT_NAME, recipientName.getText().toString());
                values.put(DatabaseHelper.COLUMN_AMOUNT, amount.getText().toString());
                values.put(DatabaseHelper.COLUMN_DESCRIPTION, description.getText().toString());

                long newRowId = db.insert(DatabaseHelper.TABLE_HISTORY, null, values);

                if (newRowId != -1) {
                    Toast.makeText(this, "Conversion completed successfully!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ValidasiActivity.this, HistoryActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "Error saving transaction!", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Error updating balance!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Insufficient balance!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        db.close();
        super.onDestroy();
    }
}
