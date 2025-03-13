@file:Suppress("DEPRECATION")

package com.example.coffeshop.Activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.coffeshop.Domain.ItemsModel
import com.example.coffeshop.R
import com.example.coffeshop.databinding.ActivityDetailBinding
import com.example.project1762.Helper.ManagmentCart

class DetailActivity : AppCompatActivity() {
    lateinit var binding:ActivityDetailBinding
    private lateinit var item:ItemsModel
    private lateinit var managmentCart: ManagmentCart
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managmentCart = ManagmentCart (this)

        bundle()
        initSizeList()
    }

    private fun initSizeList() {
        binding.apply {
            smallBtn.setOnClickListener {
                smallBtn.setBackgroundResource(R.drawable.stroke_brown_bg)
                mediumBtn.setBackgroundResource(0)
                largeBtn.setBackgroundResource(0)
            }

            mediumBtn.setOnClickListener {
                smallBtn.setBackgroundResource(0)
                mediumBtn.setBackgroundResource(R.drawable.stroke_brown_bg)
                largeBtn.setBackgroundResource(0)
            }

            largeBtn.setOnClickListener {
                smallBtn.setBackgroundResource(0)
                mediumBtn.setBackgroundResource(0)
                largeBtn.setBackgroundResource(R.drawable.stroke_brown_bg)
            }
        }
    }

    private fun bundle() {
        binding.apply {
          item= intent.getSerializableExtra("object") as ItemsModel

            Glide.with(this@DetailActivity)
                .load(item.picUrl[0])
                .into(binding.picMain)

            titleText.text = item.title
            descriptionText.text = item.description
            priceText.text = "$"+item.price
            ratingTxt.text = item.rating.toString()

            addToCartBtn.setOnClickListener {
                item.numberInCart = Integer.valueOf(
                    numberItemText.text.toString()
                )
                managmentCart.insertItems(item)
            }
            backBtn.setOnClickListener {
                finish()
            }
            plusCart.setOnClickListener {
                numberItemText.text = (item.numberInCart+1).toString()
                item.numberInCart++
            }
            minusBtn.setOnClickListener {
                if (item.numberInCart>0){
                    numberItemText.text = (item.numberInCart -1).toString()
                    item.numberInCart--
                }
            }
        }
    }
}