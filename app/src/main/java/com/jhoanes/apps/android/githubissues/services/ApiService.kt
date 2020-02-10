package com.jhoanes.apps.android.githubissues.services

import com.jhoanes.apps.android.githubissues.constants.ApiConstants.GET_ISSUES
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.HeaderMap

interface ApiService {

    @GET(GET_ISSUES)
    fun getIssues(@HeaderMap header: Map<String, String>): Call<Any>
}