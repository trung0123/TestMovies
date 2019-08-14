package com.example.testmovies.repository

import androidx.lifecycle.LiveData
import com.example.testmovies.api.ApiResponse
import com.example.testmovies.api.TheDiscoverService
import com.example.testmovies.mappers.MovieResponseMapper
import com.example.testmovies.mappers.TvResponseMapper
import com.example.testmovies.models.Resource
import com.example.testmovies.models.entity.Movie
import com.example.testmovies.models.entity.Tv
import com.example.testmovies.models.network.DiscoverMovieResponse
import com.example.testmovies.models.network.DiscoverTvResponse
import com.example.testmovies.room.MovieDao
import com.example.testmovies.room.TvDao
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DiscoverRepository @Inject
constructor(
    val discoverService: TheDiscoverService,
    val movieDao: MovieDao,
    val tvDao: TvDao
) : Repository {
    init {
        Timber.d("Injection DiscoverRepository")
    }

    fun loadMovies(page: Int): LiveData<Resource<List<Movie>>> {
        return object : NetworkBoundRepository<List<Movie>, DiscoverMovieResponse, MovieResponseMapper>() {
            override fun saveFetchData(items: DiscoverMovieResponse) {
                for (item in items.results) {
                    item.page = page
                }
                movieDao.insertMovieList(movies = items.results)
            }

            override fun shouldFetch(data: List<Movie>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun loadFromDb(): LiveData<List<Movie>> {
                return movieDao.getMovieList(page_ = page)
            }

            override fun fetchService(): LiveData<ApiResponse<DiscoverMovieResponse>> {
                return discoverService.fetchDiscoverMovie(page = page)
            }

            override fun mapper(): MovieResponseMapper {
                return MovieResponseMapper()
            }

            override fun onFetchFailed(message: String?) {
                Timber.d("onFetchFailed $message")
            }
        }.asLiveData()
    }

    fun loadTvs(page: Int): LiveData<Resource<List<Tv>>> {
        return object : NetworkBoundRepository<List<Tv>, DiscoverTvResponse, TvResponseMapper>() {
            override fun saveFetchData(items: DiscoverTvResponse) {
                for (item in items.results) {
                    item.page = page
                }
                tvDao.insertTv(tvs = items.results)
            }

            override fun shouldFetch(data: List<Tv>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun loadFromDb(): LiveData<List<Tv>> {
                return tvDao.getTvList(page_ = page)
            }

            override fun fetchService(): LiveData<ApiResponse<DiscoverTvResponse>> {
                return discoverService.fetchDiscoverTv(page = page)
            }

            override fun mapper(): TvResponseMapper {
                return TvResponseMapper()
            }

            override fun onFetchFailed(message: String?) {
                Timber.d("oFetchFailed $message")
            }
        }.asLiveData()
    }
}