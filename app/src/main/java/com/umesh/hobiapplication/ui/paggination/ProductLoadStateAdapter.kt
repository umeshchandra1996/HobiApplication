package com.umesh.hobiapplication.ui.paggination

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.umesh.hobiapplication.ui.paggination.pagingadater.ProductPagedAdapter

class ProductLoadStateAdapter (private val adapter:ProductPagedAdapter):LoadStateAdapter<NetworkStateItemViewHolder>(){
    override fun onBindViewHolder(holder: NetworkStateItemViewHolder, loadState: LoadState) {
        holder.bindTo(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): NetworkStateItemViewHolder {
        return NetworkStateItemViewHolder(parent) { adapter.retry() }
    }

}


