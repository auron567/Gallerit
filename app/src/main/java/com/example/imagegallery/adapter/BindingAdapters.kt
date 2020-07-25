package com.example.imagegallery.adapter

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("isGone")
fun bindIsGone(view: View, isGone: Boolean) {
    view.visibility = if (isGone) View.GONE else View.VISIBLE
}

@BindingAdapter("isInvisible")
fun bindIsInvisible(view: View, isInvisible: Boolean) {
    view.visibility = if (isInvisible) View.INVISIBLE else View.VISIBLE
}