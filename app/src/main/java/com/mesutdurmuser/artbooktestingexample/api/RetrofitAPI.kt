package com.mesutdurmuser.artbooktestingexample.api

import com.mesutdurmuser.artbooktestingexample.model.ImageResponse
import com.mesutdurmuser.artbooktestingexample.util.Util.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitAPI {

    //Apimize sorgularımızı atacağımız query interface'imiz
    @GET("/api/")
    suspend fun imageSearch(
        @Query("q") searchQuery: String,
        @Query("key") apiKey : String = API_KEY
    ) : Response<ImageResponse>
}