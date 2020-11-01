package com.example.legalassistant.di

import com.example.legalassistant.BuildConfig
import com.example.legalassistant.common.CiceroneHolder
import com.example.legalassistant.server.Api
import com.example.legalassistant.server.ApiService
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.schedulers.Schedulers.single
import io.realm.Realm
import io.realm.RealmConfiguration
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val appModule = module{
    single {
        OkHttpClient.Builder()
            .connectTimeout(40, TimeUnit.SECONDS)
            .readTimeout(40, TimeUnit.SECONDS)
            .build()
    }

    single {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(get())
    }

    single {
        get<Retrofit.Builder>()
            .baseUrl(BuildConfig.SERVER_URL)
            .build()
    }

    single {
        Realm.init(androidContext())
        val config = RealmConfiguration.Builder()
            .deleteRealmIfMigrationNeeded()
            .allowWritesOnUiThread(true)
            .name("legal_assistant.db")
            .build()
        Realm.getInstance(config)
    }

    single {
        CiceroneHolder()
    }

   single {
        get<Retrofit>().create(Api::class.java)
    }

    single {
        ApiService(get())
    }
}