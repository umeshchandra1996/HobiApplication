package com.umesh.hobiapplication.ui.dashboard.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.umesh.hobiapplication.R
import com.umesh.hobiapplication.databinding.FragmentHistoryBinding
import com.umesh.hobiapplication.localdb.modeldata.Product
import com.umesh.hobiapplication.resource.Status
import com.umesh.hobiapplication.utils.setStringForPrice
import com.umesh.hobiapplication.ui.adapter.ProductAdapterHistory
import com.umesh.hobiapplication.viewmodel.ProductViewModel
import dagger.hilt.android.AndroidEntryPoint

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HistoryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class HistoryFragment : Fragment() {

    private lateinit var binding: FragmentHistoryBinding
    private var list: List<Product> = listOf()
    private lateinit var adapter: ProductAdapterHistory
    private val viewModel: ProductViewModel by viewModels()
    private var totalPrice = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_history, container, false)
        binding.tvTotalPrice.text = setStringForPrice(requireContext(), (totalPrice).toString())
        setUpObserverViewModel()
        setUpRecyclerView()
        return binding.root
    }


    private fun setUpRecyclerView() {
        binding.apply {
            rvProduct.layoutManager = LinearLayoutManager(
                requireContext(),
                RecyclerView.VERTICAL, false
            )
            adapter = ProductAdapterHistory(list)
            rvProduct.adapter = adapter

        }
    }

    private fun setUpObserverViewModel() {
        viewModel.getCartProductDataFormRoomDB()
        viewModel.getProductsDataStatusList.observe(requireActivity(), Observer {
            when (it.status) {
                Status.LOADING -> {
                }
                Status.SUCCESS -> {
                    list = it.data!!
                    list.forEach{ product ->
                        totalPrice+=(product.productPrice!! * product.count!!).toDouble()
                        }
                    binding.tvTotalPrice.text= setStringForPrice(requireContext(),totalPrice.toString())

                    adapter.list = list
                    adapter.notifyDataSetChanged()
                }
                else -> {

                }
            }
        })
    }

}