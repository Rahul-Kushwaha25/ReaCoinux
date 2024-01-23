package com.rahul.titan.reacoinux.Retrofit

import com.rahul.titan.reacoinux.Utils.util
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object retrofit_instance {
    val api : retrofit_api by lazy {
        Retrofit.Builder()
            .baseUrl(util.BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(retrofit_api::class.java)
    }
}