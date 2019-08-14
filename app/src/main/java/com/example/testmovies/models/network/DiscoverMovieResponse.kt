package com.example.testmovies.models.network

import com.example.testmovies.models.NetworkResponseModel
import com.example.testmovies.models.entity.Movie

data class DiscoverMovieResponse(
    val page: Int,
    val results: List<Movie>,
    val total_results: Int,
    val total_pages: Int
) : NetworkResponseModel