package com.example.adfmp20_calc


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler



class LoadingActivity : AppCompatActivity() {

   private fun nextPage(){
       val attributes = intent.extras
        val materialsListScreenIntent = Intent(this, MaterialsListActivity::class.java)

            finish()
       if (attributes!=null){
           materialsListScreenIntent.putExtra("type",intent.extras?.getString("type") )
           materialsListScreenIntent.putExtra("total", intent.extras?.getDouble("total"))
           materialsListScreenIntent.putExtra("p1", intent.extras?.getInt("p1"))
           materialsListScreenIntent.putExtra("p2", intent.extras?.getInt("p2"))

           startActivity(materialsListScreenIntent)
       }


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.loading_screen)


        Handler().postDelayed({
           nextPage()
        }, 2000)
    }
}
