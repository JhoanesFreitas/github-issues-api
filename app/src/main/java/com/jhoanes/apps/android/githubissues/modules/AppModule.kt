package com.jhoanes.apps.android.githubissues.modules

import com.jhoanes.apps.android.githubissues.applications.RetrofitClient.Companion.provideRetrofit
import org.koin.dsl.module

val appModule = module(override = true) {
    single { provideRetrofit() }
}