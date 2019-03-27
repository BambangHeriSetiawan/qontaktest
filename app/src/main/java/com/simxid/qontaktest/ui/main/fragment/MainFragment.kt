package com.simxid.qontaktest.ui.main.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.simxid.data.remote.models.ResultsItem

import com.simxid.qontaktest.R
import com.simxid.qontaktest.databinding.MainFragmentBinding
import com.simxid.qontaktest.helper.ConnectionLiveData
import com.simxid.qontaktest.ui.main.adapter.AdapterMovie
import com.simxid.qontaktest.ui.main.detail.DetailMovieActivity
import io.reactivex.disposables.CompositeDisposable

class MainFragment : Fragment() {
    private lateinit var binding:MainFragmentBinding
    private lateinit var vm:MainFragmentViewModel
    private lateinit var adapterMovie: AdapterMovie
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.main_fragment, container,false)
        vm = MainFragmentViewModel(CompositeDisposable())
        binding.lifecycleOwner = this
        binding.mainFragVm = vm
        adapterMovie = AdapterMovie(listOf(), listener)
        checkConnection()
        return binding.root
    }

    fun checkConnection(){
        ConnectionLiveData(this@MainFragment.context!!).observe(this, Observer {
            if (it == false) vm.getLocalData(arguments!!.getString(KEY_TYPE)!!)
            else vm.getRemoteData(arguments!!.getString(KEY_TYPE)!!,1)
        })
    }
    private val listener = object : AdapterMovie.OnAdapterMovieListener {
        override fun onAdapterMovieClicked(movie: ResultsItem) {
            DetailMovieActivity.start(this@MainFragment.context!!, movie)
        }


    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.errorMsg.observe(this, Observer {
            snack(it)
        })
        vm.movies.observe(this, Observer {
            adapterMovie.update(it)
        })

        binding.rcvMovie.let {
            it.hasFixedSize()
            it.adapter = adapterMovie
            it.layoutManager = GridLayoutManager(this@MainFragment.context, 2)
            it.itemAnimator = DefaultItemAnimator()
        }
    }

    private fun snack(it: String?) {
        Snackbar.make(binding.rootMainFragment, it!!,Snackbar.LENGTH_INDEFINITE)
            .setActionTextColor(resources.getColor(R.color.abc_primary_text_disable_only_material_dark))
            .setAction("OK") {
                checkConnection()
            }
    }

    override fun onDestroy() {
        vm.clear()
        super.onDestroy()
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
