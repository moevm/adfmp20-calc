package com.example.adfmp20_calc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class WallPapersScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.wallpapers_activity)


        val goBackButton = findViewById<TextView>(R.id.goBackButton)
        goBackButton.setOnClickListener {
            finish()
        }

        val wallpapersSettingsScreenIntent = Intent(this, WallpapersSettingsActivity::class.java)
        val openWallpapersSettings = findViewById<TextView>(R.id.wallsSquare)
        openWallpapersSettings.setOnClickListener {
            startActivity(wallpapersSettingsScreenIntent)
        }

        val acceptButton = findViewById<TextView>(R.id.accept)
        val materialsListIntent = Intent(this, MaterialsListActivity::class.java)
        acceptButton.setOnClickListener {
            startActivity(materialsListIntent)
        }
    }
}
