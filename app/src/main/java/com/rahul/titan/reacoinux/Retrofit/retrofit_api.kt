package com.rahul.titan.reacoinux.Retrofit

import com.rahul.titan.reacoinux.Models.CryptoData
import com.rahul.titan.reacoinux.Models.Data
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface retrofit_api {
    @GET("data-api/v3/cryptocurrency/listing")
    suspend fun getlisting() : Response<CryptoData>

//    @GET("static/img/coins/64x64/{id}.png")

}