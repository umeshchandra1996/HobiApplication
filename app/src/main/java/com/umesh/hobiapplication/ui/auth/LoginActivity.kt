package com.umesh.hobiapplication.ui.auth

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.umesh.hobiapplication.*
import com.umesh.hobiapplication.databinding.ActivityLoginBinding
import com.umesh.hobiapplication.resource.Status
import com.umesh.hobiapplication.ui.dashboard.DashboardActivity
import com.umesh.hobiapplication.utils.PrefUtil
import com.umesh.hobiapplication.utils.checkStateSoftKeyBoard
import com.umesh.hobiapplication.utils.debugPrintln
import com.umesh.hobiapplication.utils.hideKeyboard
import com.umesh.hobiapplication.viewmodel.auth.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private var binding: ActivityLoginBinding? = null
    private val viewModel: LoginViewModel by viewModels()
    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

       //   setBackgroundForLL1()
        binding!!.parent.setOnClickListener {
            hideKeyboard()
        }

        onLoginClick()
        onSignupCall()

       // ApiCallChekup()
    }

    private fun onSignupCall() {
        binding!!.tvSignUp.setOnClickListener {
            startActivity(Intent(this,SignupActivity::class.java))
        }
    }

    private fun setUpViewModel() {
        viewModel.getUserLoginDataStatus(
            binding!!.etMobNum.text.toString(),
            binding!!.etDashPin.text.toString()
        )

        viewModel.getUserLoginDataStatus.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    debugPrintln("it.messageSuccess = ${it.message}")
                    debugPrintln("it.data = ${it.data}")
                    if (it.data != null) {
                        runBlocking {
                            PrefUtil(this@LoginActivity).saveLastLoginID(it.data.id)
                            PrefUtil(this@LoginActivity).saveLastLogin("Yes")
                        }
                        startActivity(Intent(this, DashboardActivity::class.java))
                        finish()
                    } else {
//                        Toast.makeText(this, "User does not exist in database.", Toast.LENGTH_LONG)
//                            .show()
                        Snackbar.make(binding!!.root,"User does not exist in database.",Snackbar.LENGTH_LONG).show()
                    }

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


    private fun onLoginClick() {
        binding!!.login.setOnClickListener {
            apply {
                if(checkStateSoftKeyBoard()){
                    hideKeyboard()
                }
                setUpViewModel()
//                startActivity(Intent(this, DashboardActivity::class.java))
            }
        }
    }


















    private fun ApiCallChekup() {
    viewModel.getDataFormNetwork()
    viewModel.getNetworkDataStatus.observe(this, Observer {
        when (it.status) {
            Status.SUCCESS -> {
                debugPrintln("it.messageSuccess = ${it.message}")
                debugPrintln("it.data = ${it.data.toString()}")

            }
            Status.ERROR -> {
                debugPrintln("it.message = ${it.message}")
                Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
            }

            else -> {

            }
    }
    }
    )

    }

    private fun setBackgroundForLL1() {
        val gradientDrawable = GradientDrawable(
            GradientDrawable.Orientation.LEFT_RIGHT,
            intArrayOf(
                Color.parseColor("#000000"),
                Color.parseColor("#ffffff")
            ),
        )
        gradientDrawable.cornerRadius = 0f

        binding!!.llHead.background = gradientDrawable
    }

}