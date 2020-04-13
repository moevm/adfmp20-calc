package com.example.adfmp20_calc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class TilesScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val arguments = intent.extras

        val totalText =  findViewById<TextView>(R.id.total)
        var total  = arguments?.get("total")
        if(total!=null) totalText.text = total.toString() else{
            total = 0.0
            totalText.text = total.toString()
        }

        val goBackButton = findViewById<TextView>(R.id.goBackButton)
        goBackButton.setOnClickListener {
            finish()
        }

        val tilesSettingsScreenIntent = Intent(this, TilesSettingsActivity::class.java)
        val openTileSettings = findViewById<TextView>(R.id.floorSquare)
        openTileSettings.setOnClickListener {
            finish()
            startActivity(tilesSettingsScreenIntent)
        }

        val acceptButton = findViewById<TextView>(R.id.accept)
        val materialsListIntent = Intent(this, MaterialsListActivity::class.java)
        acceptButton.setOnClickListener {
            startActivity(materialsListIntent)
        }


    }
}
