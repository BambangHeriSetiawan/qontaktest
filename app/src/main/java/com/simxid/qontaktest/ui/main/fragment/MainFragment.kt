package com.simxid.qontaktest.ui.main.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager

import com.simxid.qontaktest.R
import com.simxid.qontaktest.databinding.MainFragmentBinding
import com.simxid.qontaktest.ui.main.adapter.AdapterMovie
import com.simxid.qontaktest.ui.main.detail.DetailMovieActivity

class MainFragment : Fragment() {
    private lateinit var binding:MainFragmentBinding
    private lateinit var vm:MainFragmentViewModel
    lateinit var adapterMovie: AdapterMovie
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.main_fragment, container,false)
        vm = MainFragmentViewModel()
        binding.lifecycleOwner = this
        binding.mainFragVm = vm
        adapterMovie = AdapterMovie(listOf(), listener)
        return binding.root
    }

    val listener = object : AdapterMovie.OnAdapterMovieListener {
        override fun onAdapterMovieClicked(movie: String) {
            DetailMovieActivity.start(this@MainFragment.context!!, movie)
        }

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rcvMovie.let {
            it.hasFixedSize()
            it.layoutManager = GridLayoutManager(this@MainFragment.context, 2)
            it.itemAnimator = DefaultItemAnimator()
        }
    }
    companion object {
        private const val KEY_TYPE = "main"
        fun instance(type: String): MainFragment {
            var arg = Bundle()
            arg.putString(KEY_TYPE,type)
            var fragment = MainFragment()
            fragment.arguments = arg
            return fragment
        }
    }
}
