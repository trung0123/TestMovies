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
import com.example.testmovies.databinding.FragmentTvListBinding
import com.example.testmovies.extension.vm
import com.example.testmovies.models.Status
import com.example.testmovies.models.entity.Tv
import com.example.testmovies.view.adapter.TvListAdapter
import com.example.testmovies.view.viewholder.TvListViewHolder
import com.skydoves.baserecyclerviewadapter.RecyclerViewPaginator
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_tv_list.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 *
 */
class TvListFragment : Fragment(), TvListViewHolder.Delegate {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by lazy { vm(viewModelFactory, MainActivityViewModel::class) }
    private lateinit var binding: FragmentTvListBinding
    private lateinit var paginator: RecyclerViewPaginator

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tv_list, container, false)
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

    private fun initializeUI() {
        recyclerView.adapter = TvListAdapter(this)
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        paginator = RecyclerViewPaginator(
            recyclerView = recyclerView,
            isLoading = { viewModel.getTvListValues()?.status == Status.LOADING },
            loadMore = { loadMore(it) },
            onLast = { viewModel.getTvListValues()?.onLastPage!! }
        )
        paginator.currentPage = 1
    }

    private fun loadMore(page: Int) {
        viewModel.postTvPage(page)
    }

    override fun onItemClick(tv: Tv) {

    }
}
