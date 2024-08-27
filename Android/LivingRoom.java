package com.example.adl;

import android.animation.LayoutTransition;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.ComponentActivity;
import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class LivingRoom extends ComponentActivity {

    ArrayList<TextView> textViews;
    ArrayList<LinearLayout> linearLayouts;
    ArrayList<CardView> cardViews;
    ArrayList<Button> buttons;
    ArrayList<ImageView> imageViews;
    StorageReference stref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.living_room);

        getWindow().setStatusBarColor(getResources().getColor(R.color.brown));

        enableDummyElements();

        textViews = new ArrayList<TextView>();
        for(int i = 1;i <= 7;i++) {
            int txtId = getResources().getIdentifier("invis" + i, "id", getPackageName());
            textViews.add(findViewById(txtId));
        }
        linearLayouts = new ArrayList<LinearLayout>();
        for(int i = 1;i <= 7;i++) {
            int layoutId = getResources().getIdentifier("invislayout" + i, "id", getPackageName());
            linearLayouts.add(findViewById(layoutId));
        }
        cardViews = new ArrayList<CardView>();
        for(int i = 1;i <= 7;i++) {
            int cardId = getResources().getIdentifier("livingRoomCard" + i, "id", getPackageName());
            cardViews.add(findViewById(cardId));
        }
        buttons = new ArrayList<Button>();
        for(int i = 1;i <= 7;i++) {
            int btnId = getResources().getIdentifier("viewButton" + i, "id", getPackageName());
            Button btn = findViewById(btnId);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent =  new Intent(LivingRoom.this, Description.class);
                    startActivity(intent);
                }
            });
            buttons.add(btn);
        }
        imageViews = new ArrayList<ImageView>();
        for(int i = 1;i <= 7;i++) {
            int imgId = getResources().getIdentifier("livingRoomImage" + i, "id", getPackageName());
            imageViews.add(findViewById(imgId));
        }


        for (LinearLayout layout : linearLayouts) {
            layout.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
        }

        for (int index = 0; index < cardViews.size(); index++) {
            final int finalIndex = index;
            cardViews.get(index).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int visibility = textViews.get(finalIndex).getVisibility() == View.GONE ? View.VISIBLE : View.GONE;
                    textViews.get(finalIndex).setVisibility(visibility);
                    buttons.get(finalIndex).setVisibility(visibility);
                }
            });
        }
        setImageContent();
    }
    private void setImageContent() {
        int j = 1;
        for(ImageView i:imageViews) {
            stref = FirebaseStorage.getInstance().getReference("livingroom" + j + ".jpg");
            stref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    Glide.with(LivingRoom.this).load(uri.toString()).into(i);
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
                Intent intent =  new Intent(LivingRoom.this, MainActivity.class);
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
