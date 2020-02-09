package com.jhoanes.apps.android.githubissues.applications

import android.app.Application
import android.content.Context
import com.jhoanes.apps.android.githubissues.modules.appModule
import org.koin.core.context.startKoin

class GIApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        context = applicationContext

        startKoin {
            printLogger()
            modules(appModule)
        }
    }

    companion object {
        var context: Context? = null
    }
}