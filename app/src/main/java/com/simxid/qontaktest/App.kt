package com.simxid.qontaktest

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.simxid.data.local.ObjectBox

/**
 * Created by simx on 27,March,2019
 */
class App: Application() {
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this@App)
        ObjectBox.init(this@App.applicationContext)
    }
}