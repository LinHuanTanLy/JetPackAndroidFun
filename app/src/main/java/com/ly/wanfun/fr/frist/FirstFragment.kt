package com.ly.wanfun.fr.frist

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

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
        }
        vm.mBannerSource.observe(this, Observer {
            with(bannerFirstAd) {
                setImages(it)
                setImageLoader(GlideImageLoader())
                start()
            }
        })
    }

    override fun lazyLoadData() {
        super.lazyLoadData()


    }


    override fun layoutId() = R.layout.first_fragment

}
