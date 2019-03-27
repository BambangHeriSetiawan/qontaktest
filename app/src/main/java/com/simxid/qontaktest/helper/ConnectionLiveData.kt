package com.simxid.qontaktest.helper

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities.TRANSPORT_CELLULAR
import android.net.NetworkCapabilities.TRANSPORT_WIFI
import android.net.NetworkInfo
import android.net.NetworkRequest
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData

/**
 * Created by simx on 27,March,2019
 */
class ConnectionLiveData(private val context: Context): LiveData<Boolean>() {
    private val intentFilter  = IntentFilter("android.net.ConnectivityManager.CONNECTIVITY_ACTION")
    private var cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    private lateinit var  networkCallback: ConnectivityManager.NetworkCallback
    init {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) networkCallback = ConnectionLiveData.NetworkCallback(this)
    }

    override fun onActive() {
        super.onActive()
        updateConnection()
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.N -> cm.registerDefaultNetworkCallback(networkCallback)
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP -> {
                val builder = NetworkRequest.Builder().addTransportType(TRANSPORT_CELLULAR).addTransportType(TRANSPORT_WIFI)
                cm.registerNetworkCallback(builder.build(), networkCallback)
            }
            else -> {
                context.registerReceiver(networkReceiver, intentFilter)
            }
        }
    }

    override fun onInactive() {
        super.onInactive()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            cm.unregisterNetworkCallback(networkCallback)
        } else{
            context.unregisterReceiver(networkReceiver)
        }
    }


    private val networkReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            Log.e("ConnectionLiveData","onReceive -> ")
            updateConnection()
        }
    }

    fun updateConnection() {
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        postValue(activeNetwork?.isConnected == true)
    }


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    class NetworkCallback(private var liveData : ConnectionLiveData) : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network?) { liveData.postValue(true) }
        override fun onLost(network: Network?) { liveData.postValue(false) }
    }
}
