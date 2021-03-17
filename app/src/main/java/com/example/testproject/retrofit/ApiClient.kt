package com.example.testproject.retrofit

import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

/**
 * Created by User on 2/5/2018.
 */
object ApiClient {
    private var retrofit: Retrofit? = null
    fun setBaseUrl(): ApiHelper {
        var apiInterface: ApiHelper
        return if (retrofit == null) {
            synchronized(ApiClient::class.java) {
                val httpClient = OkHttpClient.Builder()
                val interceptor2: Interceptor = object : Interceptor {
                    @Throws(IOException::class)
                    override fun intercept(chain: Interceptor.Chain): Response {
                        val original = chain.request()
                        val request =
                            original.newBuilder() //.addHeader("Authorization", Constants.AuthorizationKey)
//  .addHeader("", "")
                                .method(original.method, original.body)
                                .build()
                        return chain.proceed(request)
                    }
                }
                httpClient.addInterceptor(interceptor2)
                val interceptor = HttpLoggingInterceptor()
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
                httpClient.addInterceptor(interceptor)
                val okHttpClient = httpClient
                   /* .connectTimeout(45, TimeUnit.SECONDS)
                    .writeTimeout(45, TimeUnit.SECONDS)
                    .readTimeout(45, TimeUnit.SECONDS)*/.build()
                retrofit = Retrofit.Builder()
                    .baseUrl("http://www.json-generator.com")
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
                apiInterface = retrofit?.create(
                    ApiHelper::class.java)!!
                return apiInterface
            }
        } else {
            retrofit!!.create(
                ApiHelper::class.java)
        }
    }
}