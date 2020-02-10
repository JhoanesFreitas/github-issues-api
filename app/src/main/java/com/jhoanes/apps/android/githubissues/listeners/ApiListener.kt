package com.jhoanes.apps.android.githubissues.listeners

import android.util.Log
import com.jhoanes.apps.android.githubissues.models.BaseModel
import com.jhoanes.apps.android.githubissues.models.IssueModel
import com.jhoanes.apps.android.githubissues.services.ApiCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.reflect.KFunction

class ApiListener : Callback<Any> {

    var callback: ApiCallback<BaseModel>? = null
    var deserializer: KFunction<Any>? = null

    override fun onFailure(call: Call<Any>, t: Throwable) {
        Log.e(ApiListener::class.java.name, "${t.message}")
        callback?.error()
    }

    @Suppress("NO_REFLECTION_IN_CLASS_PATH", "UNCHECKED_CAST")
    override fun onResponse(call: Call<Any>, response: Response<Any>) {
        Log.d(ApiListener::class.java.name, response.message())

        when {
            response.isSuccessful -> {
                val array = mutableListOf<IssueModel>()
                Thread {

                    (response.body() as ArrayList<Any>).forEach {
                        array.add(deserializer?.call(it) as IssueModel)
                    }

                    callback?.result(array)
                }.start()
            }
            else -> callback?.error()
        }

    }
}