package com.aksoyhasan.insider.util

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.WindowManager
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.aksoyhasan.insider.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

fun Context.showCommonPopup(message: String) {
    MaterialAlertDialogBuilder(this).apply {
        setCancelable(false)
        setMessage(message)
        setNeutralButton("OK") { dialog, _ ->
            dialog.dismiss()
        }
        show()
    }
}

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun Context.showLoading() {
    when(this) {
        is Fragment -> {
            this.requireActivity().window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)

            val loadingView = this.requireActivity().findViewById<RelativeLayout>(R.id.loadingView)
            loadingView.visibility = View.VISIBLE
        }
        is Activity -> {
            window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)

            val loadingView = this.findViewById<RelativeLayout>(R.id.loadingView)
            loadingView.visibility = View.VISIBLE
        }
    }
}

fun Context.hideLoading() {
    when(this) {
        is Fragment -> {
            this.requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)

            val loadingView = this.requireActivity().findViewById<RelativeLayout>(R.id.loadingView)
            loadingView.visibility = View.GONE
        }
        is Activity -> {
            window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)

            val loadingView = this.findViewById<RelativeLayout>(R.id.loadingView)
            loadingView.visibility = View.GONE
        }
    }
}