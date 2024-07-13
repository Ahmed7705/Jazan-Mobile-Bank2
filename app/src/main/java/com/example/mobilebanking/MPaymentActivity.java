package com.example.mobilebanking;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MPaymentActivity extends AppCompatActivity {

    private EditText etCardNumber, etCardHolderName, etExpiryDate, etCVC, etBillingAddress, etEmail;
    private CheckBox cbSaveDetails;
    private Button btnPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mpayment);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        etCardNumber = findViewById(R.id.etCardNumber);
        etCardHolderName = findViewById(R.id.etCardHolderName);
        etExpiryDate = findViewById(R.id.etExpiryDate);
        etCVC = findViewById(R.id.etCVC);
        etBillingAddress = findViewById(R.id.etBillingAddress);
        etEmail = findViewById(R.id.etEmail);
        cbSaveDetails = findViewById(R.id.cbSaveDetails);
        btnPay = findViewById(R.id.btnPay);

        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cardNumber = etCardNumber.getText().toString().trim();
                String cardHolderName = etCardHolderName.getText().toString().trim();
                String expiryDate = etExpiryDate.getText().toString().trim();
                String cvc = etCVC.getText().toString().trim();
                String billingAddress = etBillingAddress.getText().toString().trim();
                String email = etEmail.getText().toString().trim();

                if (cardNumber.isEmpty() || cardHolderName.isEmpty() || expiryDate.isEmpty() || cvc.isEmpty() || billingAddress.isEmpty() || email.isEmpty()) {
                    Toast.makeText(MPaymentActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else {
                    // Handle payment logic here
                    Toast.makeText(MPaymentActivity.this, "Payment Successful", Toast.LENGTH_SHORT).show();

                    if (cbSaveDetails.isChecked()) {
                        // Save payment details logic here
                        Toast.makeText(MPaymentActivity.this, "Payment details saved", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
