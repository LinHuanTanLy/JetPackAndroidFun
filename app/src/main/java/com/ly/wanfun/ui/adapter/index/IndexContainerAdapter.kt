package com.ly.wanfun.ui.adapter.index

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.ly.wanfun.R

class IndexContainerAdapter(
    private val context: Context,
    private val fragments: ArrayList<Fragment>
) : RecyclerView.Adapter<IndexContainerAdapter.IndexContainerHolder>() {


    class IndexContainerHolder(view: View) : RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IndexContainerHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.item_banner_img, parent, false)
        return IndexContainerHolder(view)
    }

    override fun getItemCount() = fragments.size

    override fun onBindViewHolder(holder: IndexContainerHolder, position: Int) {
        with(holder){

        }
    }
}