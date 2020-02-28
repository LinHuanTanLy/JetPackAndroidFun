package com.ly.wanfun.utils.imageloader

import android.content.Context
import android.util.Log
import android.widget.ImageView
import com.ly.wanfun.domain.bean.BannerBean
import com.youth.banner.loader.ImageLoader

class GlideImageLoader : ImageLoader() {
    override fun displayImage(context: Context?, path: Any?, imageView: ImageView?) {
        val imgPath = (path as BannerBean).imagePath
        Log.d("lht-----", "the path is $imgPath")
        imageView?.let { ImgLoader.loadImg(it, imgPath) }
    }


}