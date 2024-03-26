package com.mvvmdemo.view

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.mvvmdemo.R
import com.mvvmdemo.adapter.ProductAdapter
import com.mvvmdemo.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var context: Context
    private lateinit var mainActivityViewModel: MainActivityViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        context = this@MainActivity

        mainActivityViewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        adapter = ProductAdapter(context)
        recyclerView.adapter = adapter

        mainActivityViewModel.getProducts().observe(this) { products ->
            products?.let {
                adapter.setProducts(it)
            }
        }
    }
}