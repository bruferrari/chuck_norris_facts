package com.bferrari.data.injection

import com.bferrari.data.StoneAppApi
import com.bferrari.data.datasource.*
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val apiModule = module {
    single<StoneAppApi> {
        val interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        val retrofit = Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .baseUrl("https://api.chucknorris.io/")
            .build()

        retrofit.create(StoneAppApi::class.java)
    }
}

val dataModule = module {
    single<FactsDataSource> { FactsRepository(get()) }

    single<CategoryDataSource> { CategoryRepository(get(), get()) }

    single<PastSearchDataSource> { PastSearchRepository(get()) }
}