package com.javichaques.core.network.di

import arrow.retrofit.adapter.either.EitherCallAdapterFactory
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.javichaques.core.common.Constants
import com.javichaques.core.network.BuildConfig
import com.javichaques.core.network.api.UsersApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.Locale
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkProvidersModule {
    @OptIn(ExperimentalSerializationApi::class)
    @Provides
    @Singleton
    fun providesJson(): Json =
        Json {
            ignoreUnknownKeys = true
            encodeDefaults = true
            isLenient = true
            explicitNulls = false
        }

    @Provides
    @Singleton
    fun providesOkHttpCallFactory(interceptor: Interceptor): Call.Factory =
        OkHttpClient.Builder()
            .callTimeout(Constants.DEFAULT_API_TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(Constants.DEFAULT_API_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(Constants.DEFAULT_API_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(Constants.DEFAULT_API_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    if (BuildConfig.DEBUG) {
                        level = HttpLoggingInterceptor.Level.BODY
                    }
                },
            )
            .build()

    @Provides
    @Singleton
    fun providesRetrofit(
        json: Json,
        okhttpCallFactory: Call.Factory,
    ): Retrofit {
        return Retrofit.Builder().baseUrl(BuildConfig.API_URL)
            .callFactory(okhttpCallFactory)
            .addCallAdapterFactory(EitherCallAdapterFactory.create())
            .addConverterFactory(
                json.asConverterFactory("application/json".toMediaType()),
            ).build()
    }

    @Provides
    @Singleton
    fun providesInterceptor(): Interceptor =
        Interceptor {
            val request = it.request()
            val builder = request.newBuilder()

            builder.header(Constants.HEADER_ACCEPT_LANGUAGE, Locale.getDefault().toLanguageTag())
            it.proceed(builder.build())
        }

    @Provides
    @Singleton
    fun providesUsersApi(retrofit: Retrofit): UsersApi {
        return retrofit.create(UsersApi::class.java)
    }
}
