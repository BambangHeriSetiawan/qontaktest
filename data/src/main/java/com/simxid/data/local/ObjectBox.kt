package com.simxid.data.local

import android.content.Context
import android.util.Log
import com.simxid.data.MyObjectBox
import io.objectbox.BoxStore

/**
 * Created by simx on 27,March,2019
 */
object ObjectBox {
    lateinit var box: BoxStore
    private set

    fun init(context: Context) {
        box = MyObjectBox.builder().androidContext(context).build()
        Log.d("ObjectBox", "Using ObjectBox ${BoxStore.getVersion()} (${BoxStore.getVersionNative()})")
    }

}