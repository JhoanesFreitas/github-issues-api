package com.jhoanes.apps.android.githubissues.controllers

import com.jhoanes.apps.android.githubissues.extentions.toIssues
import com.jhoanes.apps.android.githubissues.models.BaseModel
import com.jhoanes.apps.android.githubissues.models.IssueModel
import com.jhoanes.apps.android.githubissues.services.ApiCallback
import com.jhoanes.apps.android.githubissues.services.ApiService
import com.jhoanes.apps.android.githubissues.services.ControllerService
import com.jhoanes.apps.android.githubissues.utils.GsonUtil
import com.jhoanes.apps.android.githubissues.utils.Params.Companion.defaultHeader
import com.jhoanes.apps.android.githubissues.utils.RxUtil
import org.koin.core.KoinComponent
import org.koin.core.inject
import rx.Observable
import kotlin.reflect.KFunction

class ApiController private constructor() : ControllerService, KoinComponent {

    private val mApiService by inject<ApiService>()

    @Suppress("UNCHECKED_CAST")
    override fun getIssues(callback: ApiCallback<IssueModel>) {
        baseMethod(
            callback as ApiCallback<BaseModel>,
            GsonUtil.instantiate()::toIssues
        ) {
            RxUtil.applyHandlerStartFinish(mApiService.getIssues(defaultHeader()),
                Runnable {
                    callback.showProgress()
                },
                Runnable {
                    callback.hideProgress()
                })
                .compose(RxUtil.applySchedulers())
        }
    }

    private fun baseMethod(
        apiCallback: ApiCallback<BaseModel>, function: KFunction<Any>,
        m: () -> Observable<List<Any>>?
    ) {
        m()?.subscribe(
            {
                val array = mutableListOf<IssueModel>()
                it.forEach { issue ->
                    array.add(function.call(issue) as IssueModel)
                }
                apiCallback.result(array)
            },
            {
                apiCallback.error()
            }
        )
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