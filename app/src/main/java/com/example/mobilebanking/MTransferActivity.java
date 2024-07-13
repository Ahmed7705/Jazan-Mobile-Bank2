package com.example.mobilebanking;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MTransferActivity extends AppCompatActivity {
    private Button continueButton;
    private EditText debitAccount, recipientAccount, recipientEmail, amount, description;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mtransfer);

        continueButton = findViewById(R.id.continue_button);
        debitAccount = findViewById(R.id.debit_account);
        recipientAccount = findViewById(R.id.recipient_account);
        recipientEmail = findViewById(R.id.recipient_email);
        amount = findViewById(R.id.amount);
        description = findViewById(R.id.description);

        databaseHelper = new DatabaseHelper(this);

        // Automatically fetch debit account from the database
        fetchDebitAccount();

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MTransferActivity.this, ValidasiActivity.class);
                intent.putExtra("debit_account", debitAccount.getText().toString());
                intent.putExtra("recipient_account", recipientAccount.getText().toString());
                intent.putExtra("recipient_name", recipientEmail.getText().toString());
                intent.putExtra("recipient_email", recipientEmail.getText().toString());
                intent.putExtra("amount", amount.getText().toString());
                intent.putExtra("description", description.getText().toString());
                startActivity(intent);
            }
        });
    }

    private void fetchDebitAccount() {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.query(DatabaseHelper.TABLE_USERS,
                new String[]{DatabaseHelper.COLUMN_ACCOUNT_NUMBER},
                null, null, null, null, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                String accountNumber = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_ACCOUNT_NUMBER));
                debitAccount.setText(accountNumber);
            }
            cursor.close();
        }
        db.close();
    }
}
