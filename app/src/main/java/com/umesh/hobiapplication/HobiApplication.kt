package com.umesh.hobiapplication

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.lifecycle.LifecycleObserver
import androidx.multidex.MultiDex
import com.umesh.hobiapplication.utils.debugPrintln
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class HobiAppApplication : Application() ,LifecycleObserver{
    override fun onCreate() {
        super.onCreate()
        registerActivityLifecycleCallbacks(ApplicationLifeCycleTracker())
        MultiDex.install(this)
    }
    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }



}

class ApplicationLifeCycleTracker : Application.ActivityLifecycleCallbacks {
   override fun onActivityCreated(p0: Activity, p1: Bundle?) {
        debugPrintln("Activity Created ${p0}")
    }

    override fun onActivityStarted(p0: Activity) {
        debugPrintln("onActivityStarted ${p0}")
    }

    override fun onActivityResumed(p0: Activity) {
        debugPrintln("onActivityResumed ${p0}")
    }

    override fun onActivityPaused(p0: Activity) {
//        TODO("Not yet implemented")
    }

    override fun onActivityStopped(p0: Activity) {
        debugPrintln("onActivityStopped ${p0}")
    }

    override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {
//        TODO("Not yet implemented")
    }


    override fun onActivityDestroyed(p0: Activity) {
        debugPrintln("onActivityDestroyed ${p0}")
    }
}
