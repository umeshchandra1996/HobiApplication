package com.umesh.hobiapplication.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.umesh.hobiapplication.R
import com.umesh.hobiapplication.databinding.ProductListItemBinding
import com.umesh.hobiapplication.localdb.modeldata.Product
import com.umesh.hobiapplication.utils.setStringForPrice


class ProductAdapterHistory(var list: List<Product>) :
    RecyclerView.Adapter<ProductAdapterHistory.ProductViewHolderHistory>() {
    private lateinit var binding: ProductListItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolderHistory {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.product_list_item,
            parent,
            false
        )
//        println("_________"+"view")
        return ProductViewHolderHistory(/*binding*/)
    }

    override fun onBindViewHolder(holder: ProductViewHolderHistory, position: Int) {
        val product = list[position]
        holder.bind(product)
    }

    override fun getItemCount(): Int {
        return if (list.isNullOrEmpty()) {
            0
        } else {
            list.size
        }
    }

    inner class ProductViewHolderHistory(/*var binding: ProductListItemBinding*/) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) {

            binding.apply {
                tvProductName.text = product.productName
                tvPrice.text= setStringForPrice(binding.root.context,product.productPrice.toString())
                tvProductDescription.text = product.productType
                tvNumProduct.text = product.count.toString()
            }
            binding.apply {
                ivAdd.visibility=View.GONE
                ivMinus.visibility = View.GONE
            }
        }
    }
}
