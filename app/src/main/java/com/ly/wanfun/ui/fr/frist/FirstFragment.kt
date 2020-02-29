package com.ly.wanfun.ui.fr.frist

import android.os.Bundle
import android.util.Log
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer

import com.ly.wanfun.R
import com.ly.wanfun.base.BaseFragment
import com.ly.wanfun.utils.imageloader.GlideImageLoader
import kotlinx.android.synthetic.main.first_fragment.*

class FirstFragment : BaseFragment<FirstViewModel, ViewDataBinding>() {

    companion object {
        fun newInstance() = FirstFragment()
    }

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)

        vm.run {
            getBanner()
            getChapters()
        }
        vm.mBannerSource.observe(this, Observer {
            with(bannerFirstAd) {
                setImages(it)
                setImageLoader(GlideImageLoader())
                start()
            }
        })
        vm.mBannerSource.observe(this, Observer {
            Log.d("lht",it.toString())
        })
    }

    override fun lazyLoadData() {
        super.lazyLoadData()


    }


    override fun layoutId() = R.layout.first_fragment

}
