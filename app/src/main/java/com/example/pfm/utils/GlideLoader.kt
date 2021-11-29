package com.example.pfm.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

fun loadImage(context: Context, url: String, imageView: ImageView) {
    Glide.with(context)
        .load(url)
        .fitCenter()
        .centerCrop()
        .into(imageView)
}

fun loadImage(context: Context, drawable: Int, imageView: ImageView) {
    Glide
        .with(context)
        .load(drawable)
        .fitCenter()
        .centerCrop()
        .into(imageView)
}


fun loadImage(context: Context, url: String, placeholder: Int, imageView: ImageView) {
    Glide.with(context)
        .load(url)
        .placeholder(placeholder)
        .fitCenter()
        .centerCrop()
        .into(imageView)
}