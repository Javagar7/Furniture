package com.example.adl;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.ComponentActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class DiningRoom extends ComponentActivity {
    ArrayList<ImageView> imageViews;
    StorageReference stref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dining_room);
        getWindow().setStatusBarColor(getColor(R.color.brown));
        enableDummyElements();
        StrictMode.ThreadPolicy threadPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(threadPolicy);

        imageViews = new ArrayList<ImageView>();
        for(int i = 1;i <= 6;i++) {
            int imgId = getResources().getIdentifier("diningRoomImage" + i, "id", getPackageName());
            imageViews.add(findViewById(imgId));
        }

        setImageContent();
    }

    public void setImageContent() {
        int j = 1;
        for(ImageView i:imageViews) {
            stref = FirebaseStorage.getInstance().getReference("diningroom" + j + ".jpg");
            stref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    Glide.with(DiningRoom.this).load(uri.toString()).into(i);
                }
            });
            j++;
        }
    }
    private void enableDummyElements() {
        ImageView home = findViewById(R.id.imgv1);
        ImageView account = findViewById(R.id.imgv2);
        ImageView cart = findViewById(R.id.imgv3);
        ImageView menu = findViewById(R.id.imgv4);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(DiningRoom.this, MainActivity.class);
                startActivity(intent);
            }
        });

        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Log In", Toast.LENGTH_SHORT).show();
            }
        });

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Cart Empty", Toast.LENGTH_SHORT).show();
            }
        });

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Menu Clicked", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
