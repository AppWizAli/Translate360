package com.hiskytechs.translate360.ApiModels

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    val quoteApi = "https://quotes85.p.rapidapi.com/"

    fun getInstance() : Retrofit{

        return  Retrofit.Builder()
            .baseUrl(quoteApi)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }
}