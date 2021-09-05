package com.umesh.hobiapplication.ui.dashboard.fragment

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.umesh.hobiapplication.R
import com.umesh.hobiapplication.databinding.FragmentDealsBinding
import com.umesh.hobiapplication.localdb.modeldata.Product
import com.umesh.hobiapplication.resource.Status
import com.umesh.hobiapplication.ui.adapter.OnProductItemClick
import com.umesh.hobiapplication.ui.adapter.ProductAdapter
import com.umesh.hobiapplication.utils.setStringForPrice
import com.umesh.hobiapplication.viewmodel.ProductViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DealsFragment : Fragment(),OnProductItemClick  {

    private lateinit var binding: FragmentDealsBinding
    private var list: List<Product> = listOf()
    private lateinit var adapter: ProductAdapter
    private val viewModel:ProductViewModel by viewModels()

    private var totalPrice=0.0
    private  var productListAddedByUser:ArrayList<Product> = arrayListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        pagedAdapter=ProductPagedAdapter(this@DealsFragment)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_deals, container, false)

        binding.tvTotalPrice.text= setStringForPrice(requireContext(),(totalPrice).toString())
        setUpObserverViewModel()
        setUpRecyclerView()
        clickForPayNow()

        return binding.root
    }


    private fun clickForPayNow() {
        binding.tvPayNow.setOnClickListener {
            if(totalPrice!=0.0) {
                val builder: AlertDialog.Builder = requireActivity().let {
                   AlertDialog.Builder(it)
                }
                builder.apply {
                    setPositiveButton("Ok",
                        DialogInterface.OnClickListener { dialog, id ->

                            viewModel.insertProductsDataList(productListAddedByUser)
//                            requireActivity().finish()


                        })
                    setNegativeButton("Cancel",
                        DialogInterface.OnClickListener { dialog, id ->
                            // User cancelled the dialog
                        })
                    setTitle("Pay Out for products.")
                    setMessage("You want to pay for this \u20B9 $totalPrice")
                }
                builder.show()
            }
            else{
                Snackbar.make(binding.root,"Please Select one item.",Snackbar.LENGTH_LONG).show()
            }

        }
    }

    private fun setUpRecyclerView() {
        binding.apply{
            rvProduct.layoutManager= LinearLayoutManager(requireContext(),RecyclerView.VERTICAL,false)
            adapter =ProductAdapter(list,this@DealsFragment)

        }
    }

    private fun setUpObserverViewModel() {
        viewModel.getProductDataFormRoomDB()
        viewModel.getProductsDataStatusList.observe(requireActivity(), Observer {
            when(it.status){
                Status.LOADING->{

                }
                Status.SUCCESS->{
                    list= it.data!!
                    adapter.list= list
                    adapter.notifyDataSetChanged()
                }
                else->{

                }
            }
        })
    }


    override fun onProductClickDetails(product: Product) {
    //open details pages
           }

    override fun onAddProductClick(product: Product,position: Int) {
        totalPrice+= product.productPrice!!
        binding.tvTotalPrice.text= setStringForPrice(requireContext(),(totalPrice).toString())
        product.addBuyUser="yes"
        productListAddedByUser.add(product)
    }

    override fun onMinusProductClick(product: Product,position: Int) {
        totalPrice-= product.productPrice!!
        binding.tvTotalPrice.text= setStringForPrice(requireContext(),(totalPrice).toString())
        if(product.count==0){
            product.addBuyUser="no"
        }
        productListAddedByUser.remove(product)
    }


}