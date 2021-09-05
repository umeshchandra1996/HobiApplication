package com.umesh.hobiapplication.ui.dashboard.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer


import androidx.lifecycle.asLiveData
import com.umesh.hobiapplication.databinding.FragmentHomeBinding
import com.umesh.hobiapplication.utils.ammountStringWithDollar
import com.umesh.hobiapplication.utils.debugPrintln
import com.umesh.hobiapplication.utils.PrefUtil
import com.umesh.hobiapplication.R
import com.umesh.hobiapplication.localdb.modeldata.Users
import com.umesh.hobiapplication.resource.Status
import com.umesh.hobiapplication.ui.dashboard.TopupActivity
import com.umesh.hobiapplication.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var binding: FragmentHomeBinding? = null
    private val viewModel: HomeViewModel by viewModels()
    var id: Long = -1L
    var c_ammount = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        PrefUtil(requireContext()).getLastLoginId.asLiveData().observe(viewLifecycleOwner) {
            id = it
            debugPrintln("id__${it}")
            setupVieModel()
        }


        binding!!.imTopup.setOnClickListener {
            startActivity(
                Intent(requireContext(), TopupActivity::class.java).putExtra(
                    "c_ammount",
                    c_ammount
                )
            )
        }
        return binding!!.root
    }

    private fun setupVieModel() {
        debugPrintln("id_v_${id}")
        viewModel.GetUserLoginDataStatus(id)
        viewModel.getUserDataStatus.observe(requireActivity(), Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    debugPrintln("it.data__${it.data }")
                    if(it.data!=null) {
                       // c_ammount = it.data.accountBalance!!
                        setDataWithView(it.data)
                    }
                }
                Status.ERROR -> {
                    debugPrintln("it.message__error${it.message}")
                }
                Status.LOADING -> {

                }
            }
        })
    }

    @SuppressLint("SetTextI18n")
    private fun setDataWithView(data: Users?) {
       // binding!!.rewardPoint.text = data!!.rewardPoint.toString()+"pt"
        //ammountStringWithDollar(binding!!.tvDwAmmount,"S$ "+data.accountBalance.toString()+".00")
//        binding!!.tvDwAmmount.text = data.accountBalance.toString()
        //binding!!.level.text = data.tier

    }


}