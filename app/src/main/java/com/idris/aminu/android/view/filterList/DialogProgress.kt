package com.idris.aminu.android.view.filterList


import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import com.idris.aminu.android.R


class DialogProgress(activity: Context?) {

    private val dialog = Dialog(activity!!)
    fun showDialog() {
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.loading_view)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
    }

    fun dismiss() {
        dialog.dismiss()
    }
}