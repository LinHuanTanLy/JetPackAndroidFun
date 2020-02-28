package com.ly.wanfun.net.api

import com.ly.wanfun.base.BaseResult
import com.ly.wanfun.domain.bean.BannerBean
import retrofit2.http.GET

interface ApiService {
    /**
     * 玩安卓轮播图
     */
    @GET("banner/json")
    suspend fun getBanner(): BaseResult<List<BannerBean>>
}