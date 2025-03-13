package com.example.coffeshop.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.coffeshop.Activities.DetailActivity
import com.example.coffeshop.Domain.ItemsModel
import com.example.coffeshop.databinding.ViewholderItemPickLeftBinding
import com.example.coffeshop.databinding.ViewholderItemPickRightBinding

class ItemsListCategoryAdapter (val items:MutableList<ItemsModel>):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        companion object {
            const val TYPE_ITEM1 = 0
            const val TYPE_ITEM2 = 1
        }

    lateinit var context:Context

    override fun getItemViewType(position: Int): Int {
        return if (position%2 == 0) TYPE_ITEM1 else TYPE_ITEM2
    }

    class ViewHolderItem1(val binding:ViewholderItemPickRightBinding):
        RecyclerView.ViewHolder (binding.root)

    class ViewHolderItem2(val binding:ViewholderItemPickLeftBinding):
        RecyclerView.ViewHolder (binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
    context = parent.context
        return when (viewType){
            TYPE_ITEM1 -> {
                val binding = ViewholderItemPickRightBinding.inflate(LayoutInflater.from(context),
                    parent, false)
                ViewHolderItem1(binding)
            }

            TYPE_ITEM2 -> {
                val binding = ViewholderItemPickLeftBinding.inflate(LayoutInflater.from(context),
                    parent, false)
                ViewHolderItem2(binding)
            }
            else -> throw IllegalArgumentException ("InvalidView type")
        }
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]

        fun bindCommonData (
            titleText:String,
            priceText:String,
            rating:Float,
            picUrl:String
        ) {
            when (holder){
                is ViewHolderItem1 -> {
                    holder.binding.titleText.text = titleText
                    holder.binding.priceText.text = priceText
                    holder.binding.ratingBar.rating = rating

                    Glide.with(context)
                        .load(picUrl)
                        .into(holder.binding.picMain)


                    holder.itemView.setOnClickListener {
                        val intent = Intent (context, DetailActivity :: class.java)
                        intent.putExtra("object", items[position])
                        context.startActivity(intent)
                    }
                }

                is ViewHolderItem2 -> {
                    holder.binding.titleText.text = titleText
                    holder.binding.priceText.text = priceText
                    holder.binding.ratingBar.rating = rating

                    Glide.with(context)
                        .load(picUrl)
                        .into(holder.binding.picMain)


                    holder.itemView.setOnClickListener {
                        val intent = Intent (context, DetailActivity :: class.java)
                        intent.putExtra("object", items[position])
                        context.startActivity(intent)
                    }
                }
            }
        }

        bindCommonData(
            item.title,
            "${item.price} USD",
            item.rating.toFloat(),
            item.picUrl[0]
        )
    }

}