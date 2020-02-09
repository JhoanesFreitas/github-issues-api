package com.jhoanes.apps.android.githubissues.modules

import com.jhoanes.apps.android.githubissues.applications.RetrofitClient.Companion.provideRetrofit
import com.jhoanes.apps.android.githubissues.controllers.ApiController.Companion.instantiate
import com.jhoanes.apps.android.githubissues.services.ApiService
import org.koin.dsl.module

val appModule = module(override = true) {
    factory { provideRetrofit().create(ApiService::class.java) }
    single { instantiate() }
}