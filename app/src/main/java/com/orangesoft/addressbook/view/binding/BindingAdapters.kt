package com.orangesoft.addressbook.view.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.orangesoft.addressbook.R


@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String?) {
    Glide.with(view)
        .load(url)
        .placeholder(R.drawable.ic_baseline_person_24)
        .error(R.drawable.ic_baseline_person_24)
        .into(view)
}