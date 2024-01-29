package com.muhammad.coutry.list.coutryinfo.util

import android.content.Context
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.muhammad.coutry.list.coutryinfo.R

fun ImageView.downloadFromUrl(url:String ,progress:CircularProgressDrawable){

    val option = RequestOptions().
    placeholder(progress).
    error(R.mipmap.ic_launcher_round)

    Glide.with(context).applyDefaultRequestOptions(option).load(url).into(this)
}

fun placeHolderProgressDrawable(context: Context): CircularProgressDrawable {

    return CircularProgressDrawable(context).apply{

        strokeWidth = 8f
        centerRadius = 40f
        start()
    }
}
@BindingAdapter("android:downloadUrl")
fun downloadImage(view:ImageView, url:String) {


     view.downloadFromUrl(url, placeHolderProgressDrawable(view.context))
}