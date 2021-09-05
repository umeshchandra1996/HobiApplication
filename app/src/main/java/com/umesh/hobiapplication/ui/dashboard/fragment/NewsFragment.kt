package com.umesh.hobiapplication.ui.dashboard.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.load.engine.Resource
import com.umesh.hobiapplication.R
import com.umesh.hobiapplication.databinding.FragmentAccountBinding
import com.umesh.hobiapplication.databinding.FragmentChatBinding
import com.umesh.hobiapplication.resource.Status
import com.umesh.hobiapplication.ui.adapter.NewsAdapter
import com.umesh.hobiapplication.utils.hideKeyboard
import com.umesh.hobiapplication.viewmodel.auth.LoginViewModel
import com.umesh.hobiapplication.viewmodel.news.EverythingViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class NewsFragment : Fragment(),NewsAdapter.OnClickPost {
    private lateinit var binding: FragmentChatBinding
    private val viewModel: EverythingViewModel by viewModels()
    private lateinit var adapterNews:NewsAdapter

    var searckKey="India"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_chat, container, false)
        adapterNews=NewsAdapter(emptyList(),this)

        binding.rvNewsPost.apply {
            adapter=adapterNews
        }
        viewModelObserver()
        binding.etSearch.setOnEditorActionListener { textView, actionId, keyEvent ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                searckKey = binding.etSearch.text.toString().trim()
                hideKeyboard()
                viewModel.getEveryThingNews(searckKey)
                searckKey =""
                binding.etSearch.setText("")
                true
            }else {
                false
            }

        }



        return binding.root

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun viewModelObserver() {
        viewModel.getEveryThingNews(searckKey)
        lifecycleScope.launchWhenStarted {
            viewModel.getEveryThingNewsDataStatus.collectLatest {
                when(it.status){
                    Status.LOADING->{
                        showProgressBar()
                    }
                    Status.SUCCESS->{
//                            println(it.data.toString()+ "_____")
                        adapterNews.list=it.data!!.articles
                        adapterNews.notifyDataSetChanged()

                        hideProgressBar()
                    }
                    Status.ERROR->{
                        hideProgressBar()
                    }
                }
            }
        }
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    override fun openNewsPost() {

    }
}