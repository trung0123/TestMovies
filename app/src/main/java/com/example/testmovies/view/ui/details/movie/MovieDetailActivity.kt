package com.example.testmovies.view.ui.details.movie

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testmovies.R
import com.example.testmovies.databinding.ActivityMovieDetailBinding
import com.example.testmovies.extension.*
import com.example.testmovies.models.Video
import com.example.testmovies.models.entity.Movie
import com.example.testmovies.view.adapter.ReviewListAdapter
import com.example.testmovies.view.adapter.VideoListAdapter
import com.example.testmovies.view.viewholder.VideoListViewHolder
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.android.synthetic.main.layout_movie_detail_body.*
import org.jetbrains.anko.startActivity
import javax.inject.Inject

class MovieDetailActivity : AppCompatActivity(), VideoListViewHolder.Delegate {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val vmDelegate by vmDelegate(MovieDetailViewModel::class)
    private val binding by activityBinding<ActivityMovieDetailBinding>(R.layout.activity_movie_detail)

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this@MovieDetailActivity)
        super.onCreate(savedInstanceState)
        val vm = viewModel(vmDelegate, viewModelFactory)
        vm.postMovieId(getMovieFromIntent().id)
        with(binding) {
            lifecycleOwner = this@MovieDetailActivity
            viewModel = vm
            detailBody.viewModel = vm
            movie = getMovieFromIntent()
            detailHeader.movie = getMovieFromIntent()
            detailBody.movie = getMovieFromIntent()
        }
        initializeUI()
    }

    private fun initializeUI() {
        applyToolbarMargin(movie_detail_toolbar)
        simpleToolbarWithHome(movie_detail_toolbar, getMovieFromIntent().title)
        detail_body_recyclerView_trailers.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        detail_body_recyclerView_trailers.adapter = VideoListAdapter(this)
        detail_body_recyclerView_reviews.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        detail_body_recyclerView_reviews.adapter = ReviewListAdapter()
        detail_body_recyclerView_reviews.isNestedScrollingEnabled = false
        detail_body_recyclerView_reviews.setHasFixedSize(true)
    }

    private fun getMovieFromIntent(): Movie {
        return intent.getParcelableExtra(movieId) as Movie
    }

    override fun onItemClicked(video: Video) {

    }

    companion object {
        private const val movieId = "movie"
        fun startActivityModel(context: Context?, movie: Movie) {
            context?.startActivity<MovieDetailActivity>(movieId to movie)
        }
    }
}
