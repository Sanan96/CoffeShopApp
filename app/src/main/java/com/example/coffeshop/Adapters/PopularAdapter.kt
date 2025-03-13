package com.example.coffeshop.Adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.coffeshop.Activities.DetailActivity
import com.example.coffeshop.Domain.ItemsModel
import com.example.coffeshop.R
import com.example.coffeshop.databinding.ViewholderPopularBinding

class PopularAdapter(private val items:MutableList<ItemsModel>) :
    RecyclerView.Adapter<PopularAdapter.ViewHolder>() {

    class ViewHolder (view: View):RecyclerView.ViewHolder(view) {
    val binding = ViewholderPopularBinding.bind(view)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.viewholder_popular , parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.binding.titleText.text = items[position].title
        holder.binding.priceText.text = "$" + items[position].price.toString()

        Glide.with(holder.itemView.context)
            .load(items[position].picUrl[0])
            .into(holder.binding.pic)


        holder.itemView.setOnClickListener{
        val intent = Intent (holder.itemView.context, DetailActivity::class.java )
            intent.putExtra("object", items [position])
            holder.itemView.context.startActivity(intent)
        }
    }
}