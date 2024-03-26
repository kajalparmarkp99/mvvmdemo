package com.mvvmdemo.view

import ProductResponseItem
import android.os.Bundle
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.mvvmdemo.R

class ProductDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)

        val product = intent.getParcelableExtra<ProductResponseItem>("product")

        if (product != null) {
            bindData(product)
        }
    }

    private fun bindData(product: ProductResponseItem) {
        // Find views
        val imageView: ImageView = findViewById(R.id.imageView)
        val titleTextView: TextView = findViewById(R.id.titleTextView)
        val descriptionTextView: TextView = findViewById(R.id.descriptionTextView)
        val priceTextView: TextView = findViewById(R.id.priceTextView)
        val categoryTextView: TextView = findViewById(R.id.categoryTextView)
        val ratingBar: RatingBar = findViewById(R.id.ratingBar)
        val btnBack: ImageView = findViewById(R.id.btnBack)

        // Set data
        Glide.with(this)
            .load(product.image)
            .into(imageView)
        titleTextView.text = product.title
        descriptionTextView.text = product.description
        priceTextView.text = "$ ${product.price}"
        categoryTextView.text = product.category
        ratingBar.rating = product.rating.rate.toFloat()

        btnBack.setOnClickListener{
            finish()
        }
    }
}
