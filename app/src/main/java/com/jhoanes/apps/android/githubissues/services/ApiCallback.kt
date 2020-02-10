package com.jhoanes.apps.android.githubissues.services

interface ApiCallback<T> {

    fun result(items: List<T>)
    fun result(item: T)
    fun error()
    fun showProgress()
    fun hideProgress()
}