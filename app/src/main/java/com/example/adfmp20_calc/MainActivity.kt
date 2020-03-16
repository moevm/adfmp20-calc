package com.example.adfmp20_calc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val x = findViewById<ImageView>(R.id.tilesArrow)


        x.setOnClickListener {
            Log.d("TILE", "Open tiles")
        }
    }
}
