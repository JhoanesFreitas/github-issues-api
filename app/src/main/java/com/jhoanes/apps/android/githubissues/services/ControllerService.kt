package com.jhoanes.apps.android.githubissues.services

interface ControllerService {
    fun getIssues(callback: ApiCallback<Any>)
}