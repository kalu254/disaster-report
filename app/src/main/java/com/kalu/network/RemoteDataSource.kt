package com.kalu.network

import com.kalu.ui.BuildConfig
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RemoteDataSource {
    companion object{
        private const val BASE_URL = "https://tana-report-disaster.herokuapp.com/"
    }

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    fun <Api> buildApi(
        api: Class<Api>,
        authToken: String? = null
    ): Api {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor { chain ->
                        chain.proceed(chain.request().newBuilder().also {
                            if (api != UserAuthApi::class.java) {
                                val token = "Bearer$authToken"
                                it.addHeader("Authorization",token )
                            }
                        }.build())
                    }.also { client ->
                        if (BuildConfig.DEBUG) {
                            val logging = HttpLoggingInterceptor()
                            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                            client.addInterceptor(logging)
                        }
                    }.build()
            )
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(api)
    }
}


