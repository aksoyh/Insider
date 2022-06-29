package com.aksoyhasan.insider

import android.app.Application
import android.content.Context
import com.aksoyhasan.insider.util.InternetConnectionHelper
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class InsiderApp : Application() {

    private lateinit var mContext: Context

    override fun onCreate() {
        super.onCreate()
        mContext = this@InsiderApp
        InternetConnectionHelper.init(this@InsiderApp)
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
    }
}