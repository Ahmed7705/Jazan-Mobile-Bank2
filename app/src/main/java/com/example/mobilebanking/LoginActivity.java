package com.example.mobilebanking;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextAccountNumber, editTextPassword;
    private MaterialButton btnLogin, btnRegister;
    private CheckBox rememberMeCheckBox;

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextAccountNumber = findViewById(R.id.accountNumberEditText2);
        editTextPassword = findViewById(R.id.passwordEditText);
        btnLogin = findViewById(R.id.loginButton);
        btnRegister = findViewById(R.id.registerButton);

        rememberMeCheckBox = findViewById(R.id.rememberMeCheckBox);

        databaseHelper = new DatabaseHelper(this);

        btnLogin.setOnClickListener(view -> {
            String accountNumber = editTextAccountNumber.getText().toString().trim();
            String password = editTextPassword.getText().toString().trim();

            if (TextUtils.isEmpty(accountNumber) || TextUtils.isEmpty(password)) {
                Toast.makeText(LoginActivity.this, "Please enter account number and password", Toast.LENGTH_SHORT).show();
            } else {
                boolean isValid = databaseHelper.checkUser(accountNumber, password);
                if (isValid) {
                    Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("ACCOUNT_NUMBER", accountNumber); // Pass the account number to MainActivity
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "Invalid account number or password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnRegister.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        databaseHelper.close();
    }
}
