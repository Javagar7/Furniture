package com.example.adl

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_page)

        window.statusBarColor = getColor(R.color.dark_yellow)

        val bRoomButton: Button = findViewById(R.id.bedRoomButton)
        bRoomButton.setOnClickListener {
            val intent = Intent(this, BedRoom::class.java)
            startActivity(intent)
        }
        val lRoomButton: Button = findViewById(R.id.livingRoomButton)
        lRoomButton.setOnClickListener {
            val intent = Intent(this, LivingRoom::class.java)
            startActivity(intent)
        }
        val dRoomButton: Button = findViewById(R.id.diningRoomButton)
        dRoomButton.setOnClickListener {
            val intent = Intent(this, DiningRoom::class.java)
            startActivity(intent)
        }
        val hButton: Button = findViewById(R.id.helpButton)
        hButton.setOnClickListener {
            Toast.makeText(applicationContext, "All help lines busy", Toast.LENGTH_SHORT).show()
        }
    }
}