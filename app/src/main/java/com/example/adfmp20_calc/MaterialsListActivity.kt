package com.example.adfmp20_calc

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

class MaterialsListActivity : AppCompatActivity() {
    class Material(val name: String, val price: Int, val square: Int) {

    }

    class Item(val name: String, val price: Int, val square: Int, val amount: Int)

    val materialList = arrayListOf<Material>(Material("TEST MATERIAL", 12, 10))

    fun searhForMaterial(square: Int): Item {
        val selectedMaterial: Material
        var totalSquare: Int = 0
        var amount: Int = 0

        var item: Item


        for (material in materialList){
            if (material.square <=  square ){
                selectedMaterial = material
                while (totalSquare < square){
                    amount++
                    totalSquare += material.square
                }
                item = Item(selectedMaterial.name, selectedMaterial.price, selectedMaterial.square, amount)
                return item
            }

        }
       return Item("",0,0,0)
    }

    var selectedItems: ArrayList<Item> = arrayListOf<Item>()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.materials_list_activity)



        val goBackButton = findViewById<TextView>(R.id.goBackButton)
        goBackButton.setOnClickListener {
            finish()
        }



        val attributes  = intent.extras
        val type: String? = attributes?.getString("type")
        val total : Double? = attributes?.getDouble("total")
        val p1: Int? = attributes?.getInt("p1")
        val p2: Int? = attributes?.getInt("p2")



        if(total!=null) {
            val material = searhForMaterial(total.toInt())
            if (material.amount != 0){
                selectedItems.add(material)
                val adapter = ProjectAdapter(this, selectedItems, total.toInt())
                val listView = findViewById<ListView>(R.id.listView)
                listView.adapter = adapter

                val totalPrice = findViewById<TextView>(R.id.totalPrice)
                var price:Int = 0
                for (item in selectedItems)
                    price += item.amount*item.price
                totalPrice.text = "$price₽"
            }

        }




        val goBack = findViewById<TextView>(R.id.selectAnotherRemont)
        val homeIntent = Intent(this, MainActivity::class.java)
        goBack.setOnClickListener {
            finish()
            startActivity(homeIntent)
        }



    }

    private  class ProjectAdapter(context: Context, selectedItems: ArrayList<Item>, total :Int) : BaseAdapter() {

        private val mContext : Context = context
        private val items :ArrayList<Item> = selectedItems
        private val total = total

        override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {
           val layoutInflater = LayoutInflater.from(mContext)
            val card = layoutInflater.inflate(R.layout.material_card, viewGroup, false)


            if (items.size!=0){
                val itemName = card.findViewById<TextView>(R.id.itemName)
                val itemPrice = card.findViewById<TextView>(R.id.itemPrice)
                val itemAmount= card.findViewById<TextView>(R.id.itemAmount)
                val itemSurplus = card.findViewById<TextView>(R.id.itemSurplus)

                itemName.text = items[position].name
                itemPrice.text = items[position].price.toString()
                itemAmount.text = items[position].amount.toString()
                itemSurplus.text = if( items[position].amount*items[position].square - total > 0)  (items[position].amount*items[position].square - total).toString() else "0"
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

           return items.size
        }

    }


}
