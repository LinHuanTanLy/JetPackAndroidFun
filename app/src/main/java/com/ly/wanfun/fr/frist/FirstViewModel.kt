package com.ly.wanfun.fr.frist

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.ly.wanfun.base.BaseVm
import com.ly.wanfun.domain.bean.BannerBean
import com.ly.wanfun.domain.repository.HomeRepository
import com.ly.wanfun.net.api.NetApi

class FirstViewModel : BaseVm() {

    private val homeRepository by lazy { HomeRepository.getInstance(NetApi.getInstance()) }
    /**
     * banner数据
     */
    val mBannerSource = MutableLiveData<List<BannerBean>>()


    fun getBanner() {
        launchGo({
            val result = homeRepository.getBannerData()
            if (result.isSuccess()) {
                mBannerSource.value = result.data
            }
        })
    }


}
