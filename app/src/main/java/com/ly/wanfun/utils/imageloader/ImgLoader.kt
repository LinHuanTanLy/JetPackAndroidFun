package com.ly.wanfun.utils.imageloader

import android.widget.ImageView
import androidx.constraintlayout.widget.Placeholder
import com.bumptech.glide.Glide

object ImgLoader {
    fun loadImg(imageView: ImageView, imgUrl: String) {
        loadImg(imageView, imgUrl, -1)
    }

    fun loadImg(imageView: ImageView, imgUrl: String, placeholderId: Int) {
        if (placeholderId != -1) {
            Glide.with(imageView.context).load(imgUrl).placeholder(placeholderId).into(imageView)
        } else {
            Glide.with(imageView.context).load(imgUrl).into(imageView)
        }
    }
}
