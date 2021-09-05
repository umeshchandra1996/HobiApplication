package com.umesh.hobiapplication.ui.dashboard

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.umesh.hobiapplication.R
import com.umesh.hobiapplication.databinding.ActivityDashboardBinding
import com.umesh.hobiapplication.ui.dashboard.fragment.AccountFragment
import com.umesh.hobiapplication.ui.dashboard.fragment.NewsFragment
import com.umesh.hobiapplication.ui.dashboard.fragment.HistoryFragment
import com.umesh.hobiapplication.ui.dashboard.fragment.HomeFragment
import com.umesh.hobiapplication.ui.paggination.ProductPagginationFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DashboardActivity : AppCompatActivity() {
    var binding:ActivityDashboardBinding?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        binding=DataBindingUtil.setContentView(this, R.layout.activity_dashboard)
        binding!!.bottomNavigationView.background=null
      //  binding!!.bottomNavigationView.menu.getItem(2).isEnabled = false
        openFragment(HomeFragment())
        bottomNavClickOnItem()

    }

    @SuppressLint("ResourceAsColor")
    private fun bottomNavClickOnItem() {

        binding!!.bottomNavigationView.setOnItemSelectedListener{
            when (it.itemId) {
                R.id.home_menu -> {
                    openFragment(HomeFragment())
                    return@setOnItemSelectedListener true
                }
                R.id.deals -> {
                   // openFragment(DealsFragment())
                    openFragment(ProductPagginationFragment())
                    return@setOnItemSelectedListener true
                }
                R.id.history -> {
                    openFragment(HistoryFragment())
                    return@setOnItemSelectedListener true
                }
                R.id.page_2 -> {
                    openFragment(AccountFragment())
                    return@setOnItemSelectedListener true
                }
                R.id.chat -> {
                    openFragment(NewsFragment())
                    return@setOnItemSelectedListener true
                }
            }
            false
        }
    }

    private fun openFragment(selectedFragment: Fragment) {


        supportFragmentManager.commit {
            replace(R.id.fl_container, selectedFragment)
            addToBackStack(null)
        }



    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 1) {
            finish()
        } else {
            super.onBackPressed()
        }
    }
}