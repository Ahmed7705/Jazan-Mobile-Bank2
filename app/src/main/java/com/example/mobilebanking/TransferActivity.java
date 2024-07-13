package com.example.mobilebanking;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TransferActivity extends AppCompatActivity {


    public Button BNI, antarbank, kembali;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);
        BNI =(Button)findViewById(R.id.BNI);
        BNI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TBNI();
                startActivity(new Intent(TransferActivity.this, MTransferActivity.class));
            }
        });

        antarbank =(Button)findViewById(R.id.antarbank);
        antarbank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TransferActivity.this, AntarbankActivity.class));
            }
        });

        kembali =(Button)findViewById(R.id.kembali);
        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TransferActivity.this, MainActivity.class));
            }
        });
    }
    public void TBNI(){
        Toast.makeText(this,"Transfer BNI", Toast.LENGTH_SHORT).show();
    }
}