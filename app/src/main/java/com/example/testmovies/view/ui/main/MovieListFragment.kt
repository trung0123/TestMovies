package com.example.testmovies.view.ui.main


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.testmovies.R
import com.example.testmovies.databinding.FragmentMovieListBinding
import com.example.testmovies.extension.vm
import com.example.testmovies.models.Status
import com.example.testmovies.models.entity.Movie
import com.example.testmovies.view.adapter.MovieListAdapter
import com.example.testmovies.view.viewholder.MovieListViewHolder
import com.skydoves.baserecyclerviewadapter.RecyclerViewPaginator
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_movie_list.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 *
 */
@Suppress("SpellCheckingInspection")
class MovieListFragment : Fragment(), MovieListViewHolder.Delegate {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by lazy { vm(viewModelFactory, MainActivityViewModel::class) }
    private lateinit var binding: FragmentMovieListBinding
    private lateinit var paginator: RecyclerViewPaginator


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_list, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeUI()
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
        loadMore(page = 1)
    }

    private fun loadMore(page: Int) {
        viewModel.postMoviePage(page)
    }

    private fun initializeUI() {
        recyclerView.adapter = MovieListAdapter(this)
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        paginator = RecyclerViewPaginator(
            recyclerView = recyclerView,
            isLoading = { viewModel.getMovieListValues()?.status == Status.LOADING },
            loadMore = { loadMore(it) },
            onLast = { viewModel.getMovieListValues()?.onLastPage!! }
        )
        paginator.currentPage = 1
    }

    override fun onItemClick(movie: Movie) {

    }
}
