package com.slateblua.taptap

import android.app.Application
import com.slateblua.taptap.appmodule.commonModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class TapTapApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@TapTapApp)
            modules(commonModule)
        }
    }
}