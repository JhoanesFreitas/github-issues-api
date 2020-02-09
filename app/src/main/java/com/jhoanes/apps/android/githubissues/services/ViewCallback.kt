package com.jhoanes.apps.android.githubissues.services

interface ViewCallback<T> {

    fun result(t: T)
    fun result(t: List<T>)
    fun error()
}