package com.example.adfmp20_calc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class WallsScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.walls_activity)


        val goBackButton = findViewById<TextView>(R.id.goBackButton)
        goBackButton.setOnClickListener {
            finish()

        }

        val wallsSettingsScreenIntent = Intent(this, WallsSettingsActivity::class.java)
        val openWallsSettings = findViewById<TextView>(R.id.wallsSquare)
        openWallsSettings.setOnClickListener {
            startActivity(wallsSettingsScreenIntent)
        }

        val acceptButton = findViewById<TextView>(R.id.accept)
        val materialsListIntent = Intent(this, MaterialsListActivity::class.java)
        acceptButton.setOnClickListener {
            startActivity(materialsListIntent)
        }
    }
}
