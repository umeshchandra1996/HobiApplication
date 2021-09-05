package com.umesh.hobiapplication.utils

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.umesh.hobiapplication.BuildConfig
import com.umesh.hobiapplication.R


fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}


fun Activity.checkStateSoftKeyBoard(): Boolean {
    val inputMethodManager =
        baseContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    return inputMethodManager.isActive

}

fun Context.checkStateSoftKeyBoard(context: Context): Boolean {
    val inputMethodManager =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    return inputMethodManager.isActive

}


fun debugPrintln(s: String) {
    if (BuildConfig.DEBUG) {
        println("s = ${s}")
    }


}

fun ammountStringWithDollar(view: TextView, s: String) {
    val wordtoSpan: Spannable =
        SpannableString(s)
    wordtoSpan.setSpan(
        ForegroundColorSpan(ContextCompat.getColor(view.context, R.color.app_dollar)),
        0,
        3,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    view.text = wordtoSpan
}


fun setStringForPrice(context: Context, price: String): String {

    return String.format(
        context.resources.getString(R.string.product_price),
        price
    )
}

fun simpleDialog(context: Context, message: String, title: String) {
//    var res=false
    val builder: AlertDialog.Builder = context.let {
        AlertDialog.Builder(it)
    }
    builder.apply {
        setPositiveButton("Ok",
            DialogInterface.OnClickListener { dialog, id ->
            })
        setNegativeButton("Cancel",
            DialogInterface.OnClickListener { dialog, id ->
                // User cancelled the dialog

            })
        setTitle(title)
        setMessage(message)
    }
    builder.show()
//    return res
}

fun setImageByGlide(context: Context,url:String,imageView: ImageView) {
    Glide.with(context)
        .load(url)
        .placeholder(R.drawable.app_logo)
        .into(imageView)

}