package com.example.adfmp20_calc

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity



class FloorScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val arguments = intent.extras

        super.onCreate(savedInstanceState)
        setContentView(R.layout.floor_activity)

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
        val floorSettingsScreenIntent = Intent(this, FloorSettingsActivity::class.java)
        val openFloorSettings = findViewById<TextView>(R.id.floorSquare)
        openFloorSettings.setOnClickListener {
            finish()
            startActivity(floorSettingsScreenIntent)
        }

        val acceptButton = findViewById<TextView>(R.id.accept)
        val materialsListIntent = Intent(this, LoadingActivity::class.java)
        acceptButton.setOnClickListener {
            materialsListIntent.putExtra("total", total as Double)
            startActivity(materialsListIntent)
        }


    }
}
