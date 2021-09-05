package com.umesh.hobiapplication.ui.paggination.pagingadater

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.umesh.hobiapplication.R
import com.umesh.hobiapplication.databinding.ProductListItemBinding
import com.umesh.hobiapplication.localdb.modeldata.Product
import com.umesh.hobiapplication.utils.setStringForPrice

class ProductPagedAdapter(var onProductClick: OnProductItemClickPaged): PagingDataAdapter<Product, ProductPagedAdapter.ProductViewHolder>(
    PRODUCT_DIFFUTILS
) {

    private lateinit var binding: ProductListItemBinding
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {

        val product = getItem(position)
        if (product != null) {
            holder.bind(product)
        }


    }

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

    inner class ProductViewHolder(/*var binding: ProductListItemBinding*/) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) {
            binding.apply {
                tvProductName.text = product.productName
                  tvPrice.text = String.format(
                      binding.root.resources.getString(R.string.product_price),
                      product.productPrice.toString()
                  )
                tvPrice.text= setStringForPrice(binding.root.context,product.productPrice.toString())
                tvProductDescription.text = product.productType

            //    tvNumProduct.text = "0"product.count.toString()
            }
            binding.apply {
                ivAdd.setOnClickListener {
                    tvNumProduct.text = (tvNumProduct.text.toString().toInt() + 1).toString()
                    product.count = product.count?.plus(1)
                    onProductClick.onAddProductClickPaged(product,position)
                    onProductClick.showPayCard(true)
                }
                ivMinus.setOnClickListener {
                    if (tvNumProduct.text.toString().toInt() >= 1  ) {
                        tvNumProduct.text = (tvNumProduct.text.toString().toInt() - 1).toString()
                        product.count = product.count?.minus(1)
                        onProductClick.onMinusProductClickPaged(product,position)
                    }

                }
            }
        }

    }
    companion object {
        val PRODUCT_DIFFUTILS = object : DiffUtil.ItemCallback<Product>() {
            override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean =
                oldItem == newItem

            override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean =
                oldItem.id == newItem.id
        }
    }

    interface OnProductItemClickPaged {
        fun onProductClickDetailsPaged(product: Product)

        fun onAddProductClickPaged(product: Product,position: Int)

        fun onMinusProductClickPaged(product: Product,position: Int)

        fun showPayCard(onOff:Boolean)

    }


}

