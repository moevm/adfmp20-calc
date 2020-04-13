package com.example.adfmp20_calc

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar



class WallsScreenActivity : AppCompatActivity() {

    private fun checkPrice(v: View, p1: Int, p2:Int, total:Double){
        if (p1>p2){
            val snack: Snackbar = Snackbar.make(v, "Проверьте правильность ввода цены", Snackbar.LENGTH_LONG)
            val view = snack.view
            val params =
                view.layoutParams as FrameLayout.LayoutParams
            params.gravity = Gravity.TOP
            view.layoutParams = params
            snack.show()
        } else {

            val materialsListIntent = Intent(this, LoadingActivity::class.java)
            materialsListIntent.putExtra("type", "Перегородки")
            materialsListIntent.putExtra("total", total)
            materialsListIntent.putExtra("p1", p1)
            materialsListIntent.putExtra("p2", p2)
            startActivity(materialsListIntent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.walls_activity)

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

        val wallsSettingsScreenIntent = Intent(this, WallsSettingsActivity::class.java)
        val openWallsSettings = findViewById<TextView>(R.id.wallsSquare)
        openWallsSettings.setOnClickListener {
            finish()
            startActivity(wallsSettingsScreenIntent)
        }

        val acceptButton = findViewById<TextView>(R.id.accept)

        acceptButton.setOnClickListener {
            val p1 = findViewById<EditText>(R.id.priceFrom).text.toString()
            val p2 = findViewById<EditText>(R.id.priceTo).text.toString()
            if(p1 == "" || p2 == "")
                checkPrice(it, 20, 10, total as Double)
            else
                checkPrice(it, p1.toInt(), p2.toInt(), total as Double)
        }
    }
}
