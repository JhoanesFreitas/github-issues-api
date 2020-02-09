package com.jhoanes.apps.android.githubissues.listeners

import android.util.Log
import com.jhoanes.apps.android.githubissues.services.ApiCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.reflect.KFunction

class ApiListener : Callback<Any> {

    var callback: ApiCallback<Any>? = null
    var deserializer: KFunction<Any>? = null

    override fun onFailure(call: Call<Any>, t: Throwable) {
        Log.e(ApiListener::class.java.name, t.message + "")
    }

    override fun onResponse(call: Call<Any>, response: Response<Any>) {
        Log.d(ApiListener::class.java.name, response.message())
    }

    private fun deserializableErrorMessage(response: Response<Any>) {

    }
}