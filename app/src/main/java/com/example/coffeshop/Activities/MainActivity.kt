package com.example.coffeshop.Activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.coffeshop.Adapters.CategoryAdapter
import com.example.coffeshop.Adapters.PopularAdapter
import com.example.coffeshop.ViewModel.MainViewModel
import com.example.coffeshop.databinding.ActivityMainBinding

class MainActivity:AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val viewModel = MainViewModel ()
    override fun onCreate(savedInstanceState: Bundle?) {
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBanner ()
        initCategory()
        initPopular()
        initBottomMenu()

    }

    private fun initBottomMenu() {
        binding.cartBtn.setOnClickListener {
            startActivity(Intent(this, CartActivity::class.java))
        }
    }

    private fun initBanner() {
        binding.progressBarBanner.visibility = View.VISIBLE

        viewModel.loadBanner().observe(this) {
            Glide.with(this@MainActivity)
                .load(it[0].url)
                .into(binding.banner)

            binding.progressBarBanner.visibility = View.GONE
        }
    }



    private fun initCategory() {
        binding.progressBarCategory.visibility = View.VISIBLE

        binding.recyclerViewCat.layoutManager = LinearLayoutManager(
            this@MainActivity, LinearLayoutManager.HORIZONTAL, false
        )

        viewModel.loadCategory().observe(this) { categories ->
            binding.recyclerViewCat.adapter = CategoryAdapter(categories)
            binding.progressBarCategory.visibility = View.GONE
        }
    }

    private fun initPopular (){
        binding.progressBarPopular.visibility = View.VISIBLE
        viewModel.loadPopular().observe(this){
            binding.recyclerViewPopular.layoutManager = GridLayoutManager(this, 2)
            binding.recyclerViewPopular.adapter = PopularAdapter(it)
            binding.progressBarPopular.visibility = View.GONE
        }
        viewModel.loadPopular()
    }
}