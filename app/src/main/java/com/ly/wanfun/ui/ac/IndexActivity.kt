package com.ly.wanfun.ui.ac

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ly.wanfun.R
import com.ly.wanfun.base.BaseActivity
import com.ly.wanfun.ui.adapter.index.IndexContainerAdapter
import com.ly.wanfun.ui.fr.frist.FirstFragment
import com.ly.wanfun.ui.fr.sec.SecondFragment
import com.ly.wanfun.ui.fr.thrid.ThridFragment
import kotlinx.android.synthetic.main.activity_index.*

class IndexActivity : BaseActivity<IndexVm, ViewDataBinding>() {


    private val fragments = ArrayList<Fragment>()

    override fun layoutId(): Int = R.layout.activity_index

    override fun initView(savedInstanceState: Bundle?) {
        fragments.add(FirstFragment.newInstance())
        fragments.add(SecondFragment.newInstance())
        fragments.add(ThridFragment.newInstance())

        initViewPage()


    }

    /**
     * 初始化viewpager
     */
    private fun initViewPage() {
        vpMainContainer.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount() = fragments.size
            override fun createFragment(position: Int) = fragments[position]
        }
        vpMainContainer.offscreenPageLimit = fragments.size - 1
        vpMainContainer.isUserInputEnabled = false
        btmNavigationMenu.setOnNavigationItemSelectedListener { it ->
            when (it.itemId) {
                R.id.firstFragment -> vpMainContainer.currentItem = 0
                R.id.secondFragment -> vpMainContainer.currentItem = 1
                R.id.thridFragment -> vpMainContainer.currentItem = 2
                else -> vpMainContainer.currentItem = 0
            }
            true
        }

    }

    override fun initData() {

    }
}