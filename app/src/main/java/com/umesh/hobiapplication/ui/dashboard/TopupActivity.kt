package com.umesh.hobiapplication.ui.dashboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.umesh.hobiapplication.utils.ammountStringWithDollar
import com.umesh.hobiapplication.R
import com.umesh.hobiapplication.databinding.ActivityTopupBinding
import com.umesh.hobiapplication.ui.adapter.PaymentAdapter
import com.umesh.hobiapplication.ui.adapter.onPaymentItemClick
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopupActivity : AppCompatActivity(), onPaymentItemClick {
    var binding: ActivityTopupBinding? = null
    var list = listOf(
        "App Pet",
        "Bank Account",
        "Credit/Debit Card",
        "Pay Anyone",
        "PayNow",
        "Cash-by QR"
    )
    var adapter = PaymentAdapter(list, this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_topup)
//        binding!!.tvCv.text=intent.getIntExtra("c_ammount",0).toString()
        ammountStringWithDollar(
            binding!!.tvCv,
            "S$ " + intent.getIntExtra("c_ammount", 0).toString()+".00"
        )

        setupRecyclerView()
        binding!!.imBack.setOnClickListener {
            finish()
        }
    }

    private fun setupRecyclerView() {
//        binding!!.rvPayment.layoutManager=LinearLayoutManager(this,RecyclerView.VERTICAL,false)

        binding!!.rvPayment.adapter = adapter
        adapter.list=list
        adapter.notifyDataSetChanged()


    }

    override fun onPaymentTypeClick() {
        //TODO("Not yet implemented")
    }

}