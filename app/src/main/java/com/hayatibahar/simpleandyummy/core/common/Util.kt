package com.hayatibahar.simpleandyummy.core.common

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.Toast
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.hayatibahar.simpleandyummy.R
import org.jsoup.Jsoup

fun ImageView.loadFromUrlByGlide(url: String) {
    Glide.with(this.context.applicationContext)
        .load(url)
        .error(R.drawable.test_image)
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)
}

fun View.hideKeyboard(context: Context?) {
    val inputMethodManager =
        context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(this.windowToken, 0)
}

inline fun <T : ViewBinding> ViewGroup.inflateAdapterItem(
    crossinline inflater: (LayoutInflater, ViewGroup, Boolean) -> T,
    attachToParent: Boolean = false,
): T {
    return inflater.invoke(LayoutInflater.from(context), this, attachToParent)
}

fun View.makeGone() {
    visibility = View.GONE
}

fun View.makeVisible() {
    visibility = View.VISIBLE
}

fun View.makeInvisible() {
    visibility = View.INVISIBLE
}

fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}

fun parseSummaryHtml(summary: String): String = Jsoup.parse(summary).text()


