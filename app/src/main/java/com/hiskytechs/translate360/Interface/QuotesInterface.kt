package com.hiskytechs.translate360.Interface

import com.hiskytechs.translate360.ApiModels.ModelQuotes
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface QuotesInterface {
    @Headers(
        "x-rapidapi-key: f589ca786fmsh1a24528bc29a5e9p19db1bjsn646922a6f716",
        "x-rapidapi-host: quotes85.p.rapidapi.com"
    )
    @GET("/author?author=Albert-Einstein")
    fun getAuthorsQuotes(@Query("/author?author=Albert-Einstein") author: String): Call<ModelQuotes>
}
