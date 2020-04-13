package com.example.adfmp20_calc


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import java.util.*


class LoadingActivity : AppCompatActivity() {

   private fun nextPage(){
        val materialsListScreenIntent = Intent(this, MaterialsListActivity::class.java)
            finish()
            startActivity(materialsListScreenIntent)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.loading_screen)


        Handler().postDelayed({
           nextPage()
        }, 2000)
    }
}
