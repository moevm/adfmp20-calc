package com.example.adfmp20_calc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tilesScreenIntent = Intent(this, TilesScreenActivity::class.java)
        val openTilesScreenButton = findViewById<ImageView>(R.id.openTilesScreenButton)
        openTilesScreenButton.setOnClickListener {
          startActivity(tilesScreenIntent)
        }

        val wallpapersScreenIntent = Intent(this, WallPapersScreenActivity::class.java)
        val openWallpapersScreenButton = findViewById<ImageView>(R.id.openWallpapersScreenButton)
        openWallpapersScreenButton.setOnClickListener {
            startActivity(wallpapersScreenIntent)
        }

        val floorScreenIntent = Intent(this, FloorScreenActivity::class.java)
        val openFloorScreenButton = findViewById<ImageView>(R.id.openFloorScreenButton)
        openFloorScreenButton.setOnClickListener {
            startActivity(floorScreenIntent)
        }

        val wallsScreenIntent = Intent(this, WallsScreenActivity::class.java)
        val openWallsScreenButton = findViewById<ImageView>(R.id.openWallsScreenButton)
        openWallsScreenButton.setOnClickListener {
            startActivity(wallsScreenIntent)
        }
    }
}
