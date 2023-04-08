package com.driuft.random_pets_starter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class PetAdapter (private val cardList: List<String>, private val nameList: List<String>, private val typeList: List<String>,
                  private val priceList: List<String>, private val priceList2: List<String>, private val idList: List<String>) : RecyclerView.Adapter<PetAdapter.ViewHolder>() {
//class PetAdapter (private val petList: List<String>) : RecyclerView.Adapter<PetAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cardImage: ImageView
        val nameText: TextView
        val typeText: TextView
        val priceText: TextView
        val priceText2: TextView

    init {
            // Find our RecyclerView item's ImageView for future use
            cardImage = view.findViewById(R.id.card_image)
            nameText = view.findViewById(R.id.name_Text)
            typeText = view.findViewById(R.id.type_Text)
            priceText = view.findViewById(R.id.price_Text)
            priceText2 = view.findViewById(R.id.price_Text2)

    }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.pet_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(cardList[position])
            .centerCrop()
            .into(holder.cardImage)

        // `holder` can used to reference any View within the RecyclerView item's layout file
        //holder.cardImage.setOnClickListener {
            //Toast.makeText(holder.itemView.context, "Card at position $position clicked", Toast.LENGTH_SHORT).show()
        //}

        holder.cardImage.setOnClickListener {
            Toast.makeText(holder.itemView.context, "Card ID:" + idList[position] , Toast.LENGTH_SHORT).show()
        }

        holder.nameText.text = nameList[position]
        holder.typeText.text = typeList[position]
        holder.priceText.text = priceList[position]
        holder.priceText2.text = priceList2[position]

    }

    override fun getItemCount() = cardList.size

}