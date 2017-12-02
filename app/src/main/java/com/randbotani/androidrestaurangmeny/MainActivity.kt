package com.randbotani.androidrestaurangmeny

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.food_ticket.view.*
import kotlinx.android.synthetic.main.title_ticket.view.*

class MainActivity : AppCompatActivity() {

    var adapter: FoodAdapter? = null
    var listOfFoods = ArrayList<Food>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listOfFoods.add(Food("title_app", "Burgers", R.drawable.doherty))
        listOfFoods.add(Food("DOHERTY", "Cheddar / Bacon / Smokey mayo / BBQ-sauce / Tomato / Ruccola", R.drawable.doherty))
        listOfFoods.add(Food("VEGGIE", "Fried halloumi / Cream cheese / Chipotle mayo / Pickled jalapeño", R.drawable.veggie))

        listOfFoods.add(Food("title_app", "Hot drinks", R.drawable.tea))
        listOfFoods.add(Food("TEA", "Ceylon tea", R.drawable.tea))
        listOfFoods.add(Food("COFFEE", "Brazilian coffee", R.drawable.coffe))

        listOfFoods.add(Food("title_app", "Dessert", R.drawable.macadamia))
        listOfFoods.add(Food("MACADAMIA", "Salted caramel milkshake", R.drawable.macadamia))
        listOfFoods.add(Food("CHOCOLATE TRUFFLE", "Passionfruit / Coconut", R.drawable.choc_truffle_mousse))

        adapter = FoodAdapter(listOfFoods,applicationContext)
        lvFood.adapter = adapter

    }

    inner class FoodAdapter : BaseAdapter {
        var context: Context? = null
        var listOfFoodLocal = ArrayList<Food>()

        constructor(listOfFood: ArrayList<Food>, context: Context) {
            this.listOfFoodLocal = listOfFood
            this.context = context
        }

        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            val food = listOfFoodLocal[p0]

            if (food.name == "title_app") {
                var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                val foodView = inflator.inflate(R.layout.title_ticket, null)
                foodView.tvTitle.text = food.des!!
                return foodView
            } else {
                var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                val foodView = inflator.inflate(R.layout.food_ticket, null)
                foodView.tvName.text = food.name!!
                foodView.tvDes.text = food.des!!
                foodView.ivFoodImage.setImageResource(food.image!!)

                foodView.ivFoodImage.setOnClickListener {
                    val intent = Intent(context, FoodDetails::class.java)
                    intent.putExtra("name", food.name!!)
                    intent.putExtra("des", food.des!!)
                    intent.putExtra("image", food.image!!)
                    context!!.startActivity(intent)
                }
                return foodView
            }

        }

        override fun getItem(p0: Int): Any {
            return listOfFoodLocal[p0]
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getCount(): Int {
            return listOfFoodLocal.size
        }

    }
}
