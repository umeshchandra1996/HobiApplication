package com.umesh.hobiapplication.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.umesh.hobiapplication.R
import com.umesh.hobiapplication.databinding.PaymentLayoutQrCashBinding
import com.umesh.hobiapplication.databinding.PaymentListItemAdapterDarkBinding
import com.umesh.hobiapplication.databinding.PaymetListItemAdapterBinding

val NEW_PAYMENT_TYPE = 0
val OLD_PAYMENT_TYPE = 1
val QR_PAYMENT_TYPE = 2


class PaymentAdapter(var list: List<String>, var onPaymentItemClick: onPaymentItemClick) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    lateinit var binding: PaymetListItemAdapterBinding
    lateinit var bindingDark: PaymentListItemAdapterDarkBinding

     lateinit var bindingQR: PaymentLayoutQrCashBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            NEW_PAYMENT_TYPE -> {
                bindingDark = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.payment_list_item_adapter_dark,
                    parent,
                    false
                )
                return PaymentViewHolderDark(bindingDark)

            }
            QR_PAYMENT_TYPE -> {
                bindingQR = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.payment_layout_qr_cash,
                    parent,
                    false
                )
                return PaymentQRViewHolder(bindingQR)
            }
            else -> {
                binding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.paymet_list_item_adapter,
                    parent,
                    false
                )
                return PaymentViewHolder(binding)
            }
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = list[position]
        when (holder) {
            is PaymentViewHolderDark -> {
                holder.bind(data)
            }
            is PaymentViewHolder -> {

                holder.bind(data)

            }
            is PaymentQRViewHolder -> {

                holder.bind(data)

            }
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0) {
            return NEW_PAYMENT_TYPE
        } else if (position == list.size-1) {
            return QR_PAYMENT_TYPE
        } else {
            return OLD_PAYMENT_TYPE
        }
    }
}


class PaymentViewHolder(var binding: PaymetListItemAdapterBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(data: String) {
        binding.tvDpet.text = data
    }
}

class PaymentViewHolderDark(var binding: PaymentListItemAdapterDarkBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(data: String) {
        binding.tvDpet.text = data
    }

}


class PaymentQRViewHolder(var binding: PaymentLayoutQrCashBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(data: String) {
        binding.tvQr.text = data
    }

}

interface onPaymentItemClick {
    fun onPaymentTypeClick()
}