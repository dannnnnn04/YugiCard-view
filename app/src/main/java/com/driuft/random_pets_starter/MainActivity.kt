package com.driuft.random_pets_starter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers

class MainActivity : AppCompatActivity() {

    //private lateinit var petList: MutableList<String>
    //private lateinit var rvPets: RecyclerView
    //private lateinit var petList2: MutableList<String>

    private lateinit var rvCards: RecyclerView
    private lateinit var cardList: MutableList<String>
    private lateinit var nameList: MutableList<String>
    private lateinit var typeList: MutableList<String>
    private lateinit var priceList: MutableList<String>
    private lateinit var priceList2: MutableList<String>

    private lateinit var idList: MutableList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    rvCards = findViewById(R.id.card_list)
    cardList = mutableListOf()
    nameList = mutableListOf()
    typeList = mutableListOf()
        priceList = mutableListOf()
        priceList2 = mutableListOf()
        idList = mutableListOf()

        getCardImageURL()
}


    private fun getCardImageURL() {
        val client = AsyncHttpClient()

        client["https://db.ygoprodeck.com/api/v7/cardinfo.php?staple=yes", object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JsonHttpResponseHandler.JSON) {
                Log.d("Dog Success", "$json")

                for (i in 0 until 64) {
                    var resultsJSON = json.jsonObject.getJSONArray("data").getJSONObject(i)
                    cardList.add(resultsJSON.getJSONArray("card_images").getJSONObject(0).getString("image_url"))
                    nameList.add(resultsJSON.getString("name"))
                    typeList.add(resultsJSON.getString("type"))
                    priceList.add("ebay_price: $" + resultsJSON.getJSONArray("card_prices").getJSONObject(0).getString("ebay_price"))
                    priceList2.add("amazon_price: $" + resultsJSON.getJSONArray("card_prices").getJSONObject(0).getString("amazon_price"))
                    idList.add(resultsJSON.getString("id"))

                }

                val adapter = PetAdapter(cardList, nameList, typeList, priceList, priceList2, idList)
                rvCards.adapter = adapter
                rvCards.layoutManager = LinearLayoutManager(this@MainActivity)

                rvCards.addItemDecoration(DividerItemDecoration(this@MainActivity, LinearLayoutManager.VERTICAL))

            }

            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                errorResponse: String,
                throwable: Throwable?
            ) {
                Log.d("Dog Error", errorResponse)
            }
        }]
    }

    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvPets = findViewById(R.id.pet_list)
        petList = mutableListOf()
        petList2 = mutableListOf()

        getCatImageURL()

    }*/

    /*private fun getCatImageURL() {
        val client = AsyncHttpClient()

        client["https://api.thecatapi.com/v1/images/search?limit=10&breed_ids=beng", object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JsonHttpResponseHandler.JSON) {
                Log.d("Dog Success", "$json")

                for (i in 0 until 10) {

                    petList.add(json.jsonArray.getJSONObject(i).getString("url"))
                    petList2.add(json.jsonArray.getJSONObject(i).getString("id"))
                }

                val adapter = PetAdapter(petList, petList2)
                //val adapter = PetAdapter(petList)
                rvPets.adapter = adapter
                rvPets.layoutManager = LinearLayoutManager(this@MainActivity)

                rvPets.addItemDecoration(DividerItemDecoration(this@MainActivity, LinearLayoutManager.VERTICAL))

            }

            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                errorResponse: String,
                throwable: Throwable?
            ) {
                Log.d("Dog Error", errorResponse)
            }
        }]
    }*/

}