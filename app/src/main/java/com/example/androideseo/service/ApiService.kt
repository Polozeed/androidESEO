package com.example.androideseo.service

import android.text.Editable
import com.example.androideseo.BuildConfig
import com.example.androideseo.data.models.Client
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit


/**
 * ApiService
 */
interface ApiService {

    data class Post (
            val user : String,
            val password : String
    )

    @Headers("Content-Type: application/json")
    @POST("/client/connexion")
    suspend fun postconnexion(@Body userData: ServiceClient.UserInfo) : Client

    @GET("/api/users/{")
    suspend fun getUser(@Path("id") id: Int): Client

    @GET("/api/test/martin")
    suspend fun test(): String

    @GET("/client/liste")
    suspend fun test2(): String

    companion object {
        /**
         * Création d'un singleton pour la simplicité, mais normalement nous utilisons plutôt un
         * injecteur de dépendances.
         */
        val instance = build()

        /**
         * Création de l'objet qui nous permettra de faire les appels d'API
         */
        private fun build(): ApiService {
            val gson = GsonBuilder().create() // JSON deserializer/serializer

            // Create the OkHttp Instance
            val okHttpClient = OkHttpClient.Builder()
                    .readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .addInterceptor(HttpLoggingInterceptor().setLevel(if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE))
                    .addInterceptor(Interceptor { chain: Interceptor.Chain ->
                        val request =
                                chain.request().newBuilder().addHeader("Accept", "application/json").build()

                        chain.proceed(request)
                    })
                    .build()

            return Retrofit.Builder()
                    .baseUrl("http://localhost:8083")
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
                    .create(ApiService::class.java)
        }
    }
}