package com.simxid.qontaktest.helper

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog

/**
 * Created by simx on 27,March,2019
 */
object DialogHelper {

    fun createDialogSingleChoice(context: Context, listener: DialogInterface.OnClickListener): AlertDialog {
        var dialog = AlertDialog.Builder(context)
        dialog.setSingleChoiceItems(arrayOf("POPULAR MOVIE","TOP RATING MOVIE"),0, listener)
        return dialog.create()
    }
}