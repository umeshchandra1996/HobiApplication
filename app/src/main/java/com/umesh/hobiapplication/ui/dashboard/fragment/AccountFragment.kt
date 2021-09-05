package com.umesh.hobiapplication.ui.dashboard.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.umesh.hobiapplication.R
import com.umesh.hobiapplication.databinding.FragmentAccountBinding
import com.umesh.hobiapplication.resource.Status
import com.umesh.hobiapplication.ui.auth.SignupActivity
import com.umesh.hobiapplication.utils.PrefUtil
import com.umesh.hobiapplication.utils.debugPrintln
import com.umesh.hobiapplication.viewmodel.auth.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking


@AndroidEntryPoint
class AccountFragment : Fragment() {
    private lateinit var binding: FragmentAccountBinding
    private val viewModel: LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_account, container, false)
        binding.headerLayout.header.text = "Personal Data"
        logout()
        setupProfileData()
        return binding.root
    }

    private fun setupProfileData() {
        runBlocking {
            viewModel.getUserProfileData(PrefUtil(requireContext()).getLastLoginId.first())
        }
        viewModel.getUserProfileDataStatus.observe(requireActivity(), Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    debugPrintln("it.data__${it.data!!.email}")
                    binding.tvEmailSet.text = it.data!!.email
                    binding.tvMobSet.text = it.data.mobNum
                    binding.tvNameSet.text = it.data.name
                }
                Status.ERROR -> {
                    Log.d("Error", it.message!!)
                }
                else -> {

                }
            }
        })


    }

    private fun logout() {
        binding.btnLogout.setOnClickListener {
            runBlocking {
                PrefUtil(requireContext()).saveLastLogin("No")
                startActivity(Intent(requireContext(), SignupActivity::class.java))
                requireActivity().finish()
            }
        }
    }

}