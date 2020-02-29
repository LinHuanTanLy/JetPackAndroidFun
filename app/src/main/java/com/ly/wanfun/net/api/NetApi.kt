package com.ly.wanfun.net.api

import com.ly.wanfun.net.retrofit.RetrofitClient

class NetApi {
    private val mService by lazy {
        RetrofitClient.getInstance().create(ApiService::class.java)
    }

    companion object {
        private var netApi: NetApi? = null

        fun getInstance() = netApi ?: synchronized(this) {
            netApi ?: NetApi().also { netApi = it }
        }
    }

    suspend fun getChaptersData() = mService.getChapters()
    suspend fun getBannerData() = mService.getBanner()
}