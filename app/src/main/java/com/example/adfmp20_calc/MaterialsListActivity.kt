package com.example.adfmp20_calc

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

class MaterialsListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.materials_list_activity)


        val goBackButton = findViewById<TextView>(R.id.goBackButton)
        goBackButton.setOnClickListener {
            finish()
        }
        val adapter = ProjectAdapter(this)

        val goBack = findViewById<TextView>(R.id.selectAnotherRemont)
        val homeIntent = Intent(this, MainActivity::class.java)
        goBack.setOnClickListener {
            finish()
            startActivity(homeIntent)
        }

        val listView = findViewById<ListView>(R.id.listView)
        listView.adapter = adapter

    }

    private  class ProjectAdapter(context: Context) : BaseAdapter() {

        private val mContext : Context = context



        override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {
           val layoutInflater = LayoutInflater.from(mContext)
            val card = layoutInflater.inflate(R.layout.material_card, viewGroup, false)

            return card
        }

        override fun getItem(position: Int): Any {
           return "Test string"
        }

        override fun getItemId(position: Int): Long {
           return  position.toLong()
        }

        override fun getCount(): Int {
           return 10
        }

    }


}
