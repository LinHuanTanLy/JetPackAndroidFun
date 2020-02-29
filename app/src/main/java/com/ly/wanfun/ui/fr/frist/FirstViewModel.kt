package com.ly.wanfun.ui.fr.frist

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import com.ly.wanfun.R
import com.ly.wanfun.base.BaseVm
import com.ly.wanfun.domain.bean.BannerBean
import com.ly.wanfun.domain.bean.ChaptersBean
import com.ly.wanfun.domain.repository.HomeRepository
import com.ly.wanfun.net.api.NetApi

class FirstViewModel : BaseVm() {

    private val homeRepository by lazy { HomeRepository.getInstance(NetApi.getInstance()) }
    /**
     * banner数据
     */
    val mBannerSource = MutableLiveData<List<BannerBean>>()

    val mChaptersSource = ObservableArrayList<List<ChaptersBean>>()





    fun getChapters() {
//        launchGo({
//            val result = homeRepository.getChaptersData()
//            if (result.isSuccess()) {
//                mChaptersSource.value = result.data
//            }
//        })
    }

    fun getBanner() {
        launchGo({
            val result = homeRepository.getBannerData()
            if (result.isSuccess()) {
                mBannerSource.value = result.data
            }
        })
    }


}
