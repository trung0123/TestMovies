package com.example.testmovies.api

import androidx.lifecycle.LiveData
import com.example.testmovies.models.network.DiscoverMovieResponse
import com.example.testmovies.models.network.DiscoverTvResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TheDiscoverService {

    @GET("/3/discover/movie?language=en&sort_by=popularity.desc")
    fun fetchDiscoverMovie(@Query("page") page: Int): LiveData<ApiResponse<DiscoverMovieResponse>>

    @GET("/3/discover/tv?language=en&sort_by=popularity.desc")
    fun fetchDiscoverTv(@Query("page") page: Int): LiveData<ApiResponse<DiscoverTvResponse>>
}