package com.jhoanes.apps.android.githubissues.objects

import android.util.Log
import com.jhoanes.apps.android.githubissues.models.IssueModel
import com.jhoanes.apps.android.githubissues.services.ApiCallback

object ApiCallbackImpl : ApiCallback<IssueModel> {
    override fun result(items: List<IssueModel>) {

        Log.d(ApiCallbackImpl::class.java.name, "${items.size}" )

    }

    override fun result(item: IssueModel) {
    }

    override fun error() {
    }

}