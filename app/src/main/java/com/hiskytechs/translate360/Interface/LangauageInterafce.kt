package com.hiskytechs.translate360.Interface

import com.hiskytechs.translate360.ApiModels.ModelLanguage
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface LangauageInterafce {
    @Headers("x-rapidapi-host: google-translate113.p.rapidapi.com","x-rapidapi-key: 87f35efdb3msh587f556e6d3964bp12e498jsn4afd6fcfa215" )
    @GET("search")
    fun getData(): Call<ModelLanguage>

}
