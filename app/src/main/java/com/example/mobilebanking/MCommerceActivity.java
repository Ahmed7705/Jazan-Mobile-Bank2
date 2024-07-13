package com.example.mobilebanking;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MCommerceActivity extends AppCompatActivity {

    private ImageView productImage1, productImage2, productImage3, productImage4;
    private TextView productTitle1, productTitle2, productTitle3, productTitle4;
    private TextView productDescription1, productDescription2, productDescription3, productDescription4;
    private TextView productPrice1, productPrice2, productPrice3, productPrice4;
    private Button addToCartButton1, addToCartButton2, addToCartButton3, addToCartButton4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mcommerce);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        productImage1 = findViewById(R.id.product_image1);
        productTitle1 = findViewById(R.id.product_title1);
        productDescription1 = findViewById(R.id.product_description1);
        productPrice1 = findViewById(R.id.product_price1);
        addToCartButton1 = findViewById(R.id.add_to_cart_button1);

        productImage2 = findViewById(R.id.product_image2);
        productTitle2 = findViewById(R.id.product_title2);
        productDescription2 = findViewById(R.id.product_description2);
        productPrice2 = findViewById(R.id.product_price2);
        addToCartButton2 = findViewById(R.id.add_to_cart_button2);

        productImage3 = findViewById(R.id.product_image3);
        productTitle3 = findViewById(R.id.product_title3);
        productDescription3 = findViewById(R.id.product_description3);
        productPrice3 = findViewById(R.id.product_price3);
        addToCartButton3 = findViewById(R.id.add_to_cart_button3);

        productImage4 = findViewById(R.id.product_image4);
        productTitle4 = findViewById(R.id.product_title4);
        productDescription4 = findViewById(R.id.product_description4);
        productPrice4 = findViewById(R.id.product_price4);
        addToCartButton4 = findViewById(R.id.add_to_cart_button4);

        // Product 1 details
        productTitle1.setText("iPhone 15");
        productDescription1.setText("This is the latest iPhone with advanced features.");
        productPrice1.setText("4,599.00 SAR");

        // Product 2 details
        productTitle2.setText("Samsung Galaxy S21");
        productDescription2.setText("Experience the next level of smartphone technology with Samsung.");
        productPrice2.setText("3,999.00 SAR");

        // Product 3 details
        productTitle3.setText("Apple Watch Series 5 - 40mm");
        productDescription3.setText("Built-in GPS, GLONASS, Galileo, and QZSS, S5 with 64-bit dual-core processor, W3 Apple wireless chip, Barometric altimeter, Capacity 32GB, Optical heart ...");
        productPrice3.setText("1,099.00 SAR");

        // Product 4 details
        productTitle4.setText("Apple - AirPods 3rd");
        productDescription4.setText("Sound that supports spatial audio with dynamic head tracking, sound surrounds you from every direction and immerses you in a 3D listening experience...");
        productPrice4.setText("13.00 SAR");

        addToCartButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MCommerceActivity.this, "iPhone 15 added to cart", Toast.LENGTH_SHORT).show();
            }
        });

        addToCartButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MCommerceActivity.this, "Samsung Galaxy S21 added to cart", Toast.LENGTH_SHORT).show();
            }
        });

        addToCartButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MCommerceActivity.this, "Google Pixel 6 added to cart", Toast.LENGTH_SHORT).show();
            }
        });

        addToCartButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MCommerceActivity.this, "OnePlus 9 Pro added to cart", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
