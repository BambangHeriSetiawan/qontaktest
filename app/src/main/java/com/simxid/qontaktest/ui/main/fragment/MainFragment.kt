package com.simxid.qontaktest.ui.main.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import com.simxid.qontaktest.R

class MainFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    companion object {
        const val KEY_TYPE = "main"
        fun instance(type: String): MainFragment {
            var arg = Bundle()
            arg.putString(KEY_TYPE,type)
            var fragment = MainFragment()
            fragment.arguments = arg
            return fragment
        }
    }
}
