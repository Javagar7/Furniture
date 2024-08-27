package com.example.adl;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.ComponentActivity;

public class Description extends ComponentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.description);

        getWindow().setStatusBarColor(getResources().getColor(R.color.brown));

        Button addToCart = findViewById(R.id.addToCart);
        Button buyNow = findViewById(R.id.buyNow);
        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Added To Cart", Toast.LENGTH_SHORT).show();
            }
        });
        buyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Under Development", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
