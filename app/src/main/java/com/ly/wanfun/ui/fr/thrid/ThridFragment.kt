package com.ly.wanfun.ui.fr.thrid

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.ly.wanfun.R

class ThridFragment : Fragment() {

    companion object {
        fun newInstance() = ThridFragment()
    }

    private lateinit var viewModel: ThridViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.thrid_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ThridViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
