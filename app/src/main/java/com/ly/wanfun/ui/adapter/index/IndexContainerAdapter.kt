package com.ly.wanfun.ui.adapter.index

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ly.wanfun.R
import com.ly.wanfun.databinding.ItemChaptersListBinding
import com.ly.wanfun.domain.bean.BannerBean
import com.ly.wanfun.domain.bean.ChaptersBean

class IndexContainerAdapter(
    private val context: Context,
    private val list: List<ChaptersBean>
) : RecyclerView.Adapter<IndexContainerAdapter.IndexContainerHolder>() {


    class IndexContainerHolder(view: View) : RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IndexContainerHolder {
        val binding: ItemChaptersListBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.item_chapters_list,
            parent,
            false
        )
        return IndexContainerHolder(binding.root)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: IndexContainerHolder, position: Int) {

    }
}