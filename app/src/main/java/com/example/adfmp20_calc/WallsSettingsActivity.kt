package com.example.adfmp20_calc

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

class WallsSettingsActivity : AppCompatActivity() {
    private fun calculate(walls: ArrayList<Wall>): Double {
        var total = 0.0
        for (wall in walls)
            total+=wall.height*wall.width*wall.amount
        return total
    }
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

        val acceptButton = findViewById<TextView>(R.id.accept)
        val screenIntent = Intent(this, WallsScreenActivity::class.java)
        acceptButton.setOnClickListener {
            val total = calculate(adapter.walls)
            screenIntent.putExtra("total", total)
            finish()
            startActivity(screenIntent)
        }
    }

    private  class ProjectAdapter(context: Context) : BaseAdapter() {

        private val mContext : Context = context


        var walls = arrayListOf<Wall>(
            Wall(0.0, 0.0)
        )




         fun createFloor(){
            walls.add( Wall(0.0, 0.0))
        }

        private fun removeFloor(index:Int){
            walls.removeAt(index)
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

            amountTextView.text = walls[position].amount.toString()

            increaseButton.setOnClickListener {
                walls[position].amount++
                amountTextView.text = walls[position].amount.toString()

            }

            decreaseButton.setOnClickListener {
                walls[position].amount--
                amountTextView.text = walls[position].amount.toString()
            }

            removeItem.setOnClickListener{

                removeFloor(position)
                notifyDataSetChanged()
            }

            widthTextEdit.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(p0: Editable?) {
                    walls[position].width = p0.toString().toDouble()

                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }
            })

            heightTextEdit.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(p0: Editable?) {
                    walls[position].height = p0.toString().toDouble()

                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }
            })

            if(walls[position].width != 0.0){
                widthTextEdit.setText(walls[position].width.toString())
            }
            if(walls[position].height != 0.0){
                heightTextEdit.setText(walls[position].height.toString())
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
           return walls.size
        }

    }

     class Wall(width:Double, height: Double) {
        var width = 0.0
        var height = 0.0
         var amount = 1

         init {
             this.width = width
             this.height = height
         }
    }
}
