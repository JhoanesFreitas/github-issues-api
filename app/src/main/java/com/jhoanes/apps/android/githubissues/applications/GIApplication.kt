package com.jhoanes.apps.android.githubissues.applications

import android.app.Application
import com.jhoanes.apps.android.githubissues.modules.appModule
import org.koin.core.context.startKoin

class GIApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            printLogger()
            modules(appModule)
        }
    }
}