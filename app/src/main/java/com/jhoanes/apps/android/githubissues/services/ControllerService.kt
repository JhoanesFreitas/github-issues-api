package com.jhoanes.apps.android.githubissues.services

import com.jhoanes.apps.android.githubissues.models.IssueModel

interface ControllerService {
    fun getIssues(callback: ApiCallback<IssueModel>)
}