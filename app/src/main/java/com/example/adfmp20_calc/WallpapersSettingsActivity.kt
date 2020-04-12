package com.example.adfmp20_calc

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import org.w3c.dom.Text

class WallpapersSettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)


        val goBackButton = findViewById<TextView>(R.id.goBackButton)
        goBackButton.setOnClickListener {
            finish()
        }
        val adapter = ProjectAdapter(this)



        val listView = findViewById<ListView>(R.id.listView)
        listView.adapter = adapter
        val addNewItemButton = findViewById<Button>(R.id.addNewItem)
        addNewItemButton.setOnClickListener {
            adapter.createFloor()
            adapter.notifyDataSetChanged()
        }
    }

    private  class ProjectAdapter(context: Context) : BaseAdapter() {

        private val mContext : Context = context

        var floors = arrayListOf<Floor>(
            Floor(0.0, 0.0)
        )

         var width = 0
         var height = 0


         fun createFloor(){
            floors.add( Floor(0.0, 0.0))
        }

        private fun removeFloor(index:Int){
            floors.removeAt(index)
        }

        override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {
           val layoutInflater = LayoutInflater.from(mContext)
            val card = layoutInflater.inflate(R.layout.wall_setting_card, viewGroup, false)
            val settingCardId = card.findViewById<TextView>(R.id.itemId)
            settingCardId.text = (position + 1).toString()
            val increaseButton = card.findViewById<Button>(R.id.increase)
            val decreaseButton = card.findViewById<Button>(R.id.decrease)
            val amountTextView = card.findViewById<TextView>(R.id.amountTextView)
            val removeItem = card.findViewById<TextView>(R.id.removeItem)

            val widthTextEdit = card.findViewById<EditText>(R.id.widthTextEdit)
            val heightTextEdit = card.findViewById<EditText>(R.id.heightTextEdit)

            amountTextView.text = floors[position].amount.toString()

            increaseButton.setOnClickListener {
                floors[position].amount++
                amountTextView.text = floors[position].amount.toString()

            }

            decreaseButton.setOnClickListener {
                floors[position].amount--
                amountTextView.text = floors[position].amount.toString()
            }

            removeItem.setOnClickListener{

                removeFloor(position)
                notifyDataSetChanged()
            }

            widthTextEdit.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(p0: Editable?) {
                    floors[position].width = p0.toString().toDouble()

                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }
            })

            heightTextEdit.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(p0: Editable?) {
                    floors[position].height = p0.toString().toDouble()

                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }
            })

            if(floors[position].width != 0.0){
                widthTextEdit.setText(floors[position].width.toString())
            }
            if(floors[position].height != 0.0){
                heightTextEdit.setText(floors[position].height.toString())
            }


            return card
        }

        override fun getItem(position: Int): Any {
           return "Test string"
        }

        override fun getItemId(position: Int): Long {
           return  position.toLong()
        }

        override fun getCount(): Int {
           return floors.size
        }

    }

     class Floor(width:Double, height: Double) {
        var width = 0.0
        var height = 0.0
         var amount = 1

         init {
             this.width = width
             this.height = height
         }
    }
}
