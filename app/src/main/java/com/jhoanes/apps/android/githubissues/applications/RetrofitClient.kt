package com.jhoanes.apps.android.githubissues.applications

import com.jhoanes.apps.android.githubissues.constants.Constants.Companion.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.KoinComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient : KoinComponent {

    companion object {

        fun provideRetrofit(): Retrofit =
            Retrofit.Builder().baseUrl(BASE_URL)
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        private fun getOkHttpClient(): OkHttpClient {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(logging)
            return httpClient.build()
        }

    }
}