package com.example.testmovies.view.ui.details.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.example.testmovies.models.Keyword
import com.example.testmovies.models.Resource
import com.example.testmovies.models.Review
import com.example.testmovies.models.Video
import com.example.testmovies.repository.TvRepository
import com.example.testmovies.utils.AbsentLiveData
import timber.log.Timber
import javax.inject.Inject

class TvDetailViewModel @Inject
constructor(private val repository: TvRepository) : ViewModel() {

    private val tvIdLiveData: MutableLiveData<Int> = MutableLiveData()
    val keywordListLiveData: LiveData<Resource<List<Keyword>>>
    val videoListLiveData: LiveData<Resource<List<Video>>>
    val reviewListLiveData: LiveData<Resource<List<Review>>>

    init {
        Timber.d("Injection TvDetailViewModel")

        this.keywordListLiveData = tvIdLiveData.switchMap {
            tvIdLiveData.value?.let {
                repository.loadKeywordList(it)
            } ?: AbsentLiveData.create()
        }

        this.videoListLiveData = tvIdLiveData.switchMap {
            tvIdLiveData.value?.let {
                repository.loadVideoList(it)
            } ?: AbsentLiveData.create()
        }

        this.reviewListLiveData = tvIdLiveData.switchMap {
            tvIdLiveData.value?.let {
                repository.loadReviewsList(it)
            } ?: AbsentLiveData.create()
        }
    }

    fun postTvId(id: Int) = tvIdLiveData.postValue(id)
}