package com.ly.wanfun.ui.ac

import android.os.Bundle
import android.widget.TextView
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.ly.wanfun.R
import com.ly.wanfun.base.BaseActivity
import com.ly.wanfun.ui.fr.frist.FirstFragment
import com.ly.wanfun.ui.fr.sec.SecondFragment
import com.ly.wanfun.ui.fr.thrid.ThridFragment
import com.ly.wanfun.utils.Utils
import kotlinx.android.synthetic.main.activity_index.*

class IndexActivity : BaseActivity<IndexVm, ViewDataBinding>() {


    private val fragments = ArrayList<Fragment>()


    override fun layoutId(): Int = R.layout.activity_index

    override fun initView(savedInstanceState: Bundle?) {
        fragments.add(SecondFragment.newInstance())
        fragments.add(FirstFragment.newInstance())
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
        vpMainContainer.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                updateTabMenuStyle(position)
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }
        })
        vpMainContainer.currentItem = 1
        tvIndexSquare.setOnClickListener { vpMainContainer.currentItem = 2 }
        tvIndexArticle.setOnClickListener { vpMainContainer.currentItem = 1 }
        tvIndexMine.setOnClickListener { vpMainContainer.currentItem = 0 }
    }

    fun updateTabMenuStyle(position: Int) {
        fun selectEd(tv: TextView) {
            tv.textSize = Utils.px2dip(this, resources.getDimension(R.dimen.font_20)).toFloat()
            tv.setTextColor(resources.getColor(R.color.color_18C8A1))
        }

        fun unSelectEd(tv: TextView) {
            tv.textSize = Utils.px2dip(this, resources.getDimension(R.dimen.font_18)).toFloat()
            tv.setTextColor(resources.getColor(R.color.color_48586D))
        }
        when (position) {
            0 -> {
                selectEd(tvIndexMine)
                unSelectEd(tvIndexArticle)
                unSelectEd(tvIndexSquare)
            }
            1 -> {
                unSelectEd(tvIndexMine)
                selectEd(tvIndexArticle)
                unSelectEd(tvIndexSquare)
            }
            2 -> {
                unSelectEd(tvIndexMine)
                unSelectEd(tvIndexArticle)
                selectEd(tvIndexSquare)
            }
        }
    }

    override fun initData() {

    }
}