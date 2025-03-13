package com.example.coffeshop.Activities

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coffeshop.Adapters.ItemsListCategoryAdapter
import com.example.coffeshop.R
import com.example.coffeshop.ViewModel.MainViewModel
import com.example.coffeshop.databinding.ActivityItemsListBinding
import java.util.ResourceBundle.getBundle

class ItemsListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityItemsListBinding
    private  var viewModel = MainViewModel ()
    private var id:String = ""
    private var title:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemsListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getBundle()
        initList()

    }

    private fun initList() {
        binding.apply {
            progressBar.visibility = View.GONE
            viewModel.loadItems(id).observe(this@ItemsListActivity, Observer {
        listView.layoutManager = LinearLayoutManager(this@ItemsListActivity,
            LinearLayoutManager.VERTICAL, false)
                listView.adapter = ItemsListCategoryAdapter(it)
                progressBar.visibility = View.GONE
            })
            backBtn.setOnClickListener { finish() }
        }
    }

    private fun getBundle() {
        id = intent.getStringExtra("id")!!
        title = intent.getStringExtra("title")!!

        binding.categoryText.text = title
    }
}