package com.jhoanes.apps.android.githubissues.controllers

import com.jhoanes.apps.android.githubissues.extentions.toIssues
import com.jhoanes.apps.android.githubissues.extentions.toIssuesList
import com.jhoanes.apps.android.githubissues.listeners.ApiListener
import com.jhoanes.apps.android.githubissues.models.BaseModel
import com.jhoanes.apps.android.githubissues.models.IssueModel
import com.jhoanes.apps.android.githubissues.services.ApiCallback
import com.jhoanes.apps.android.githubissues.services.ApiService
import com.jhoanes.apps.android.githubissues.services.ControllerService
import com.jhoanes.apps.android.githubissues.utils.GsonUtil
import com.jhoanes.apps.android.githubissues.utils.Params.Companion.defaultHeader
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.Call
import retrofit2.Callback
import kotlin.reflect.KFunction

class ApiController private constructor() : ControllerService, KoinComponent {

    private val mApiService by inject<ApiService>()

    @Suppress("UNCHECKED_CAST")
    override fun getIssues(callback: ApiCallback<IssueModel>) {
        baseMethod(
            callback as ApiCallback<BaseModel>,
            GsonUtil.instantiate()::toIssues,
            ApiListener()
        ) {
            mApiService.getIssues(defaultHeader())
        }
    }

    private fun baseMethod(
        apiCallback: ApiCallback<BaseModel>, function: KFunction<Any>,
        callback: Callback<Any>, m: () -> Call<Any>?
    ) {
        callback as ApiListener
        callback.callback = apiCallback
        callback.deserializer = function
        m()?.enqueue(callback)
    }

    companion object {
        private lateinit var instance: ControllerService

        fun instantiate(): ControllerService {
            return when {
                ::instance.isInitialized -> instance
                else -> {
                    instance = ApiController()
                    instance
                }
            }
        }

    }
}