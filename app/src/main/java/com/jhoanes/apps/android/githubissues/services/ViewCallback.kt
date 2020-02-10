package com.jhoanes.apps.android.githubissues.services

import android.app.Activity
import kotlin.reflect.KClass

interface ViewCallback<T> {

    fun result(t: T)
    fun result(t: List<T>)
    fun error()
    fun startActivity(t: T, activity: KClass<out Activity>)
}