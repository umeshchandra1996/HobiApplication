package com.umesh.hobiapplication.ui.paggination

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.umesh.hobiapplication.R
import com.umesh.hobiapplication.databinding.FragmentProductPagginationBinding
import com.umesh.hobiapplication.localdb.modeldata.Product
import com.umesh.hobiapplication.ui.paggination.pagingadater.ProductPagedAdapter
import com.umesh.hobiapplication.utils.setStringForPrice
import com.umesh.hobiapplication.viewmodel.ProductViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter

@AndroidEntryPoint
class ProductPagginationFragment : Fragment(), ProductPagedAdapter.OnProductItemClickPaged {

    private lateinit var binding : FragmentProductPagginationBinding
    private val viewModel: ProductViewModel by viewModels()
    private lateinit var pagedAdapter: ProductPagedAdapter
    private var totalPrice=0.0
    private  var productListAddedByUser:MutableList<Product> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pagedAdapter = ProductPagedAdapter(this)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=DataBindingUtil.inflate(inflater,
            R.layout.fragment_product_paggination,container,false)
        setUpRecyclerView()
        initPagedDataOfProduct()
        clickForPayNow()
        return binding.root
    }


    private fun initPagedDataOfProduct() {
        lifecycleScope.launchWhenStarted {
            viewModel.getPagedProductListData.collectLatest {
                pagedAdapter.submitData(it)
            }
        }

        lifecycleScope.launchWhenStarted {
            pagedAdapter.loadStateFlow
                // Use a state-machine to track LoadStates such that we only transition to
                // NotLoading from a RemoteMediator load if it was also presented to UI.
                // Only emit when REFRESH changes, as we only want to react on loads replacing the list.
                .distinctUntilChangedBy { it.refresh }
                // Only react to cases where REFRESH completes i.e., NotLoading.
                .filter {
//                    delay(2000)
                    it.refresh is LoadState.NotLoading
                }
                .collectLatest {
                    // Scroll to top is synchronous with UI updates, even if remote load was triggered.
                   // viewModel.getProductCount()
                    binding.rvProduct.scrollToPosition(0)

                    // Manage the "no record" notice view
                    if (pagedAdapter.itemCount > 0) {
                        binding.txtNoProduct.visibility = View.GONE
                      //  binding.swipeRefreshAssessment.visibility = View.VISIBLE
                    } else {
                        binding.txtNoProduct.visibility = View.VISIBLE
                      //  binding.swipeRefreshAssessment.visibility = View.GONE
                    }
                }
        }

    }

    private fun setUpRecyclerView() {
        binding.rvProduct.apply{
            layoutManager= LinearLayoutManager(requireContext(),RecyclerView.VERTICAL,false)
            adapter =pagedAdapter.withLoadStateHeaderAndFooter(
                header = ProductLoadStateAdapter(pagedAdapter),
                footer = ProductLoadStateAdapter(pagedAdapter)
            )
        }
    }

    override fun onProductClickDetailsPaged(product: Product) {
        //TODO("Not yet implemented")
    }

    override fun onAddProductClickPaged(product: Product, position: Int) {
        totalPrice+= product.productPrice!!
        binding.tvTotalPrice.text= setStringForPrice(requireContext(),(totalPrice).toString())
        product.addBuyUser="yes"
        productListAddedByUser.add(product)
    }

    override fun onMinusProductClickPaged(product: Product, position: Int) {
        totalPrice-= product.productPrice!!

        if(totalPrice==0.0){
            binding.cvPayout.visibility=View.GONE
        }

        binding.tvTotalPrice.text= setStringForPrice(requireContext(),(totalPrice).toString())
        if(product.count==0){
            product.addBuyUser="no"
        }
        productListAddedByUser.remove(product)
    }

    override fun showPayCard(onOff: Boolean) {
        if(onOff){
            binding.cvPayout.visibility=View.VISIBLE
        }
        else{
            binding.cvPayout.visibility=View.GONE
        }
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
                Snackbar.make(binding.root,"Please Select one item.", Snackbar.LENGTH_LONG).show()
            }

        }
    }

}