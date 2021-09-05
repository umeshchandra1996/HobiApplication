package com.umesh.hobiapplication.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import com.umesh.hobiapplication.utils.debugPrintln
import com.umesh.hobiapplication.utils.PrefUtil
import com.umesh.hobiapplication.R
import com.umesh.hobiapplication.databinding.ActivityMainBinding
import com.umesh.hobiapplication.localdb.modeldata.Product
import com.umesh.hobiapplication.resource.Status
import com.umesh.hobiapplication.ui.auth.LoginActivity
import com.umesh.hobiapplication.ui.dashboard.DashboardActivity
import com.umesh.hobiapplication.viewmodel.ProductViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    private val viewModel: ProductViewModel by viewModels()
    private var dataLogin = "";
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        PrefUtil(this).getLastLogin.asLiveData().observe(this, Observer {
            dataLogin = it
        })

        setUpViewModel()

        Handler(Looper.getMainLooper()).postDelayed({
            LoginUI()
        }, 2000)

    }

    private fun setUpViewModel() {
        val product = Product(
            id=0,
            productName = "Pen",
            productPrice = 5,
            productType = "Study",
            companyName = "Likho Phekho"
        )
        val product1 = Product(
            id=1,
            productName = "Bottle",
            productPrice = 500,
            productType = "Home Accessories",
            companyName = "Milton"
        )
        val product2 = Product(
            id=2,
            productName = "Dell LapTop",
            productPrice = 50000,
            productType = "Electronics",
            companyName = "Dell"
        )
        val product3 = Product(
            id=4,
            productName = "Mac Air",
            productPrice = 125000,
            productType = "Electronics",
            companyName = "Apple USA"
        )
        viewModel.insertUserData(product)
        viewModel.insertProductsDataStatus.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    debugPrintln("it.messageSuccess = ${it.message}")
                }
                Status.ERROR -> {
                    debugPrintln("it.message = ${it.message}")
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }

                else -> {

                }
            }
        })
        viewModel.insertProductsDataList(listOf(product1, product2, product3))
        viewModel.insertProductsDataStatusList.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    debugPrintln("it.messageSuccess = ${it.message}")
                }
                Status.ERROR -> {
                    debugPrintln("it.message = ${it.message}")
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }

                else -> {

                }
            }
        })
    }


    private fun LoginUI() {

        if (dataLogin == "Yes") {
            startActivity(Intent(this, DashboardActivity::class.java))
        } else {
            startActivity(Intent(this, LoginActivity::class.java))
        }
        finish()
    }

}