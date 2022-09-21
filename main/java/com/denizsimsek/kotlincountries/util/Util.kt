package com.denizsimsek.kotlincountries.util

import android.content.Context
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.denizsimsek.kotlincountries.R

public fun ImageView.downloadFromUrl(url: String,progressDrawable:CircularProgressDrawable)
{
    var options=RequestOptions.placeholderOf(progressDrawable).error(R.mipmap.ic_launcher)
    Glide.with(context).load(url).into(this)
}

public fun placeholderProgress(context: Context):CircularProgressDrawable
{
    return CircularProgressDrawable(context).apply {
        strokeWidth=4f
        centerRadius=40f
        start()
    }
}

@BindingAdapter("android:downloadImage")
fun downloadImage(view:ImageView,url:String?)
{
    url?.let {
        view.downloadFromUrl(it, placeholderProgress(view.context))

    }
}
