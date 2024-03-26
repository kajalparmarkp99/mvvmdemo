
package com.mvvmdemo.adapter

import ProductResponseItem
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mvvmdemo.R
import com.mvvmdemo.view.ProductDetailsActivity

class ProductAdapter(private val context: Context) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    private var products: List<ProductResponseItem> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]
        holder.bind(product)
        holder.itemView.setOnClickListener {
            val intent = Intent(context, ProductDetailsActivity::class.java).apply {
                putExtra("product", product) // Pass the selected product to DetailActivity
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return products.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setProducts(products: List<ProductResponseItem>) {
        this.products = products
        notifyDataSetChanged()
    }

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        private val descriptionTextView: TextView = itemView.findViewById(R.id.descriptionTextView)
        private val priceTextView: TextView = itemView.findViewById(R.id.priceTextView)
        private val image: ImageView = itemView.findViewById(R.id.imageView)


        fun bind(product: ProductResponseItem) {
            titleTextView.text = product.title
            descriptionTextView.text = product.description
            priceTextView.text = "$ ${product.price}"
            Glide.with(itemView.context)
                .load(product.image)
                .apply(RequestOptions().placeholder(R.drawable.ic_launcher_background)) // Placeholder image while loading
                .into(image)
        }
    }
}
