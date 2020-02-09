package com.jhoanes.apps.android.githubissues.utils

import com.google.gson.Gson

class GsonUtil private constructor() {

    val gson = Gson()
    inline fun <reified T> GsonUtil.convertFromJson(json: Any?): T {
        return gson.fromJson(gson.toJson(json), T::class.java)
    }

    inline fun <reified T> GsonUtil.convertFromJsonDirect(json: Any): T {
        return gson.fromJson(json.toString(), T::class.java)
    }

    companion object {
        private lateinit var instance: GsonUtil

        fun instantiate(): GsonUtil {
            return if (::instance.isInitialized)
                instance
            else {
                instance = GsonUtil()
                instance
            }
        }
    }
}