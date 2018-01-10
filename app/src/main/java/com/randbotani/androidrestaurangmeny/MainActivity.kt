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

class MainActivity : AppCompatActivity() { // För att kunnna visa detaljer på en annan Activity

    var adapter: FoodAdapter? = null
    var listOfFoods = ArrayList<Food>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listOfFoods.add(Food("title_app", "Burgers", R.drawable.doherty))
        listOfFoods.add(Food("DOHERTY", "Cheddar / Bacon / Smokey mayo / BBQ-sauce / Tomato / Ruccola", R.drawable.doherty))
        listOfFoods.add(Food("VEGGIE", "Fried halloumi / Cream cheese / Chipotle mayo / Pickled jalapeño", R.drawable.veggie))

        listOfFoods.add(Food("title_app", "Hot drinks", R.drawable.tea))
        listOfFoods.add(Food("TEA", "Te är en växt och en dryck framställd från denna växt. Drycken te dricks oftast varm. Te framställs av unga bladskott från växten Camellia sinensis och har använts av människor i åtminstone 1700 år. Den globala produktionen uppskattades av FN:s livsmedels- och jordbruksorganisation FAO till 3,44 miljoner ton för år 2005 vilket är en rekordhög notering. De fyra största producentländerna 2005 var Kina (940 000 ton), Indien (830 000 ton), Sri Lanka (308 000 ton) och Kenya (295 000 ton). Andra länder med en årsproduktion som överstiger 100 000 ton är Turkiet, Indonesien, Vietnam och Japan.", R.drawable.tea))
        listOfFoods.add(Food("COFFEE", "Kaffe är rostade bönor (egentligen bär) från kaffebusken och så kallas även den dryck som tillverkas av dessa bönor. Kaffebusken kommer ursprungligen från subtropiska Afrika och har därifrån exporterats till andra delar av världen." +
                "Kaffedrickandet har sitt ursprung i Etiopien för två tusen år sedan och fick sedan en spridning via Arabiska halvön till Turkiet och nådde Sverige under slutet av 1600-talet.", R.drawable.coffe))

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

 // kategorisera listan
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

//Visa detaljer genom att klicka på bilden
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
