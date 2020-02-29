package com.ly.wanfun.ui.fr.frist

import android.util.Log
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import com.ly.wanfun.BR
import com.ly.wanfun.R
import com.ly.wanfun.base.BaseVm
import com.ly.wanfun.domain.bean.BannerBean
import com.ly.wanfun.domain.bean.ChaptersBean
import com.ly.wanfun.domain.repository.HomeRepository
import com.ly.wanfun.net.api.NetApi
import me.tatarka.bindingcollectionadapter2.ItemBinding

class FirstViewModel : BaseVm() {

    private val homeRepository by lazy { HomeRepository.getInstance(NetApi.getInstance()) }


    private val itemOnclickList = object : OnItemClickListener {
        override fun onItemClick(item: ChaptersBean) {

        }

    }
    /**
     * banner数据
     */
    val mBannerSource = MutableLiveData<List<BannerBean>>()

    val mChaptersSource = ObservableArrayList<ChaptersBean>()

    val itemBinding = ItemBinding.of<ChaptersBean>(BR.itemBean, R.layout.item_chapters_list)
        .bindExtra(BR.listener, itemOnclickList)

    fun getChapters() {
        launchGo({
            val result = homeRepository.getChaptersData()
            if (result.isSuccess()) {
                mChaptersSource.addAll(result.data)
                Log.d("lht----","the mChaptersSource is ${mChaptersSource.toString()} ")
            }
        })
    }

    fun getBanner() {
        launchGo({
            val result = homeRepository.getBannerData()
            if (result.isSuccess()) {
                mBannerSource.value = result.data
            }
        })
    }

    interface OnItemClickListener {
        fun onItemClick(item: ChaptersBean)
    }
}
