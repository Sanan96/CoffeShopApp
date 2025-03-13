package com.example.coffeshop.Adapters

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.coffeshop.Activities.ItemsListActivity
import com.example.coffeshop.Domain.CategoryModel
import com.example.coffeshop.R
import com.example.coffeshop.databinding.ViewholderCategoryBinding

class CategoryAdapter (val items:MutableList<CategoryModel>):RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    var selectedPosition = RecyclerView.NO_POSITION


    inner class ViewHolder(view: View) :RecyclerView.ViewHolder(view){

        val binding = ViewholderCategoryBinding.bind(view)

    }


    @SuppressLint("SuspiciousIndentation")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.viewholder_category, parent, false)

                return ViewHolder(view)
    }

    override fun getItemCount(): Int = items.size


    override fun onBindViewHolder(holder: ViewHolder, position:Int) {
        val item = items[position]
        holder.binding.titleCat.text = item.title

        holder.binding.root.setOnClickListener{
            val previousPosition = selectedPosition
            selectedPosition = position


            notifyItemChanged(previousPosition)
            notifyItemChanged(selectedPosition)

            Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(holder.itemView.context, ItemsListActivity::class.java).apply {
                putExtra("id", item.id.toString())
                putExtra("title", item.title)
            }
                ContextCompat.startActivity(holder.itemView.context, intent, null)
            }, 500)
        }

        val context = holder.itemView.context
        if (selectedPosition == position){
            holder.binding.titleCat.setBackgroundResource(R.drawable.dark_brown_bg)
            holder.binding.titleCat.setTextColor(ContextCompat.getColor(context ,R.color.white))
        } else {
            holder.binding.titleCat.setBackgroundResource(R.drawable.white_bg)
            holder.binding.titleCat.setTextColor(ContextCompat.getColor(context ,R.color.darkBrown))
        }
    }

}