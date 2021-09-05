package com.umesh.hobiapplication.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.umesh.hobiapplication.R
import com.umesh.hobiapplication.databinding.ProductListItemBinding
import com.umesh.hobiapplication.localdb.modeldata.Product
import com.umesh.hobiapplication.utils.setStringForPrice


class ProductAdapter(var list: List<Product>, var onProductClick: OnProductItemClick) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    private lateinit var binding: ProductListItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.product_list_item,
            parent,
            false
        )
//        println("_________"+"view")
        return ProductViewHolder(/*binding*/)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
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

    inner class ProductViewHolder(/*var binding: ProductListItemBinding*/) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) {

            binding.apply {
                tvProductName.text = product.productName
              /*  tvPrice.text = String.format(
                    binding.root.resources.getString(R.string.product_price),
                    product.productPrice.toString()
                )*/
                tvPrice.text= setStringForPrice(binding.root.context,product.productPrice.toString())
                tvProductDescription.text = product.productType
                tvNumProduct.text = "0"/*product.count.toString()*/
            }
            binding.apply {
                ivAdd.setOnClickListener {
                    tvNumProduct.text = (tvNumProduct.text.toString().toInt() + 1).toString()
                    product.count = product.count?.plus(1)
                    onProductClick.onAddProductClick(product,position)
                }
                ivMinus.setOnClickListener {
                    if (tvNumProduct.text.toString().toInt() >= 1  ) {
                        tvNumProduct.text = (tvNumProduct.text.toString().toInt() - 1).toString()
                        product.count = product.count?.minus(1)
                         onProductClick.onMinusProductClick(product,position)
                    }

                }
            }
        }
    }
}

interface OnProductItemClick {
    fun onProductClickDetails(product: Product)

    fun onAddProductClick(product: Product,position: Int)

    fun onMinusProductClick(product: Product,position: Int)


}
