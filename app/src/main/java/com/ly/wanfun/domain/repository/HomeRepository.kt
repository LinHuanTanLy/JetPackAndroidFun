package com.ly.wanfun.domain.repository

import com.ly.wanfun.base.BaseResult
import com.ly.wanfun.domain.bean.BannerBean
import com.ly.wanfun.net.api.NetApi

class HomeRepository private constructor(private val netWork: NetApi) {


    suspend fun getBannerData(): BaseResult<List<BannerBean>> {
        return netWork.getBannerData()
    }

    companion object {
        private var INSTANCE: HomeRepository? = null
        fun getInstance(netWork: NetApi) = INSTANCE ?: synchronized(this) {
            INSTANCE ?: HomeRepository(netWork).also { INSTANCE = it }
        }
    }
}