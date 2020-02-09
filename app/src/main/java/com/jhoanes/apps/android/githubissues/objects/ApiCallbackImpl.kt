package com.jhoanes.apps.android.githubissues.objects

import android.util.Log
import com.jhoanes.apps.android.githubissues.models.IssueModel
import com.jhoanes.apps.android.githubissues.services.ApiCallback
import com.jhoanes.apps.android.githubissues.services.ViewCallback

object ApiCallbackImpl : ApiCallback<IssueModel> {

    lateinit var callback: ViewCallback<IssueModel>

    override fun result(items: List<IssueModel>) {

        Log.d(ApiCallbackImpl::class.java.name, "${items.size}")

        if (::callback.isLateinit) {
            callback.result(items)
        }
    }

    override fun result(item: IssueModel) {
    }

    override fun error() {
    }

}