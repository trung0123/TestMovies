package com.example.testmovies.view.ui.details.movie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.testmovies.R
import com.example.testmovies.databinding.ActivityMovieDetailBinding
import com.example.testmovies.extension.activityBinding
import com.example.testmovies.extension.vmDelegate
import dagger.android.AndroidInjection
import javax.inject.Inject

class MovieDetailActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val vmDelegate by vmDelegate(MovieDetailViewModel::class)
    private val binding by activityBinding<ActivityMovieDetailBinding>(R.layout.activity_movie_detail)

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this@MovieDetailActivity)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
    }
}
