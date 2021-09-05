package com.umesh.hobiapplication.ui.auth

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.umesh.hobiapplication.utils.debugPrintln
import com.umesh.hobiapplication.utils.PrefUtil
import com.umesh.hobiapplication.R
import com.umesh.hobiapplication.databinding.ActivitySignupBinding
import com.umesh.hobiapplication.localdb.modeldata.Users
import com.umesh.hobiapplication.resource.Status
import com.umesh.hobiapplication.ui.dashboard.DashboardActivity
import com.umesh.hobiapplication.viewmodel.auth.SignUpViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking
import android.widget.ArrayAdapter
import com.umesh.hobiapplication.utils.hideKeyboard


@AndroidEntryPoint
class SignupActivity : AppCompatActivity() ,AdapterView.OnItemSelectedListener {
    private var binding: ActivitySignupBinding? = null
    private val viewModel: SignUpViewModel by viewModels()
    private lateinit var profession: String
    var Profession= emptyArray<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_signup)

        binding!!.root.setOnClickListener {
            hideKeyboard()
        }
        setProfession()
        clickForSignup()
        clickForLogin()

    }

    private fun setProfession() {
        Profession = arrayOf(
            "ENGINEER", "FARMER", "TEACHER", "STUDENT"
        )

        val adapter2: ArrayAdapter<String> = ArrayAdapter<String>(
            this,
            android.R.layout.simple_spinner_item, Profession
        )
        binding!!.autoTextViewProfession.apply {
            adapter = adapter2
            onItemSelectedListener=this@SignupActivity
        }

    }

    private fun clickForSignup() {
        binding!!.btnSignup.setOnClickListener {
            if (validation()) {
                val users = Users(
                    name = binding!!.etUserName.text.toString(),
                    mobNum = binding!!.etMobNum.text.toString(),
                    password = binding!!.etPassword.text.toString(),
                    email = binding!!.etUserEmail.text.toString(),
                    profession = profession

                )
                viewModel.insertUserData(users)
                viewModel.insertUsersDataStatus.observe(this, Observer {
                    when (it.status) {
                        Status.SUCCESS -> {
                            binding!!.progressCircular.visibility = View.GONE
                            startActivity(Intent(this, DashboardActivity::class.java))
                            runBlocking {
                                PrefUtil(this@SignupActivity).saveLastLogin("Yes")
                                PrefUtil(this@SignupActivity).saveLastLoginID(it.data!!)
                            }
                            finish()
                        }
                        Status.LOADING -> {
                            binding!!.progressCircular.visibility = View.VISIBLE
                        }
                        Status.ERROR -> {
                            debugPrintln("it.message = ${it.message}")
                            Snackbar.make(
                                binding!!.root,
                                it.message.toString(),
                                Snackbar.LENGTH_LONG
                            ).show()
                        }

                        else -> {

                        }
                    }
                })
            }
        }

    }

    private fun clickForLogin() {
        binding!!.tvLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    private fun validation(): Boolean {
        return when {
            binding!!.etUserEmail.text.isNullOrEmpty() -> {
                false
            }
            binding!!.etUserName.text.isNullOrEmpty() -> {
                false
            }
            binding!!.etMobNum.text.isNullOrEmpty() -> {
                false
            }
            binding!!.etPassword.text.isNullOrEmpty() -> {
                false
            }
            profession.isEmpty() -> {
                false
            }
            else -> {
                true
            }
        }
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        profession=Profession[p2]
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
//        TODO("Not yet implemented")
    }
}