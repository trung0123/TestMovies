package com.example.testmovies.view.ui.details.tv

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testmovies.R
import com.example.testmovies.api.Api
import com.example.testmovies.databinding.ActivityTvDetailBinding
import com.example.testmovies.extension.*
import com.example.testmovies.models.Video
import com.example.testmovies.models.entity.Tv
import com.example.testmovies.view.adapter.ReviewListAdapter
import com.example.testmovies.view.adapter.VideoListAdapter
import com.example.testmovies.view.viewholder.VideoListViewHolder
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_tv_detail.*
import kotlinx.android.synthetic.main.layout_tv_detail_body.*
import org.jetbrains.anko.startActivity
import javax.inject.Inject

class TvDetailActivity : AppCompatActivity(), VideoListViewHolder.Delegate {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val vmDelegate by vmDelegate(TvDetailViewModel::class)
    private val binding by activityBinding<ActivityTvDetailBinding>(R.layout.activity_tv_detail)

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        val vm = viewModel(vmDelegate, viewModelFactory)
        vm.postTvId(getTvFromIntent().id)
        with(binding) {
            lifecycleOwner = this@TvDetailActivity
            viewModel = vm
            detailBody.viewModel = vm
            tv = getTvFromIntent()
            detailHeader.tv = getTvFromIntent()
            detailBody.tv = getTvFromIntent()
        }

        initializeUI()
    }

    private fun initializeUI() {
        applyToolbarMargin(tv_detail_toolbar)
        simpleToolbarWithHome(tv_detail_toolbar, getTvFromIntent().name)
        detail_body_recyclerView_trailers.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        detail_body_recyclerView_trailers.adapter = VideoListAdapter(this)
        detail_body_recyclerView_reviews.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        detail_body_recyclerView_reviews.adapter = ReviewListAdapter()
        detail_body_recyclerView_reviews.isNestedScrollingEnabled = false
        detail_body_recyclerView_reviews.setHasFixedSize(true)
    }

    private fun getTvFromIntent(): Tv {
        return intent.getParcelableExtra(tvId)!!
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) onBackPressed()
        return false
    }

    companion object {
        private const val tvId = "tv"
        fun startActivityModel(context: Context?, tv: Tv) {
            context?.startActivity<TvDetailActivity>(tvId to tv)
        }
    }

    override fun onItemClicked(video: Video) {
        val playVideoIntent = Intent(Intent.ACTION_VIEW, Uri.parse(Api.getYoutubeVideoPath(video.key)))
        startActivity(playVideoIntent)
    }
}
