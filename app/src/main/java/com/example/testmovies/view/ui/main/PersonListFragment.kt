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
import com.example.testmovies.databinding.FragmentPersonListBinding
import com.example.testmovies.extension.vm
import com.example.testmovies.models.Status
import com.example.testmovies.models.entity.Person
import com.example.testmovies.view.adapter.PeopleAdapter
import com.example.testmovies.view.viewholder.PeopleViewHolder
import com.skydoves.baserecyclerviewadapter.RecyclerViewPaginator
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_person_list.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 *
 */
class PersonListFragment : Fragment(), PeopleViewHolder.Delegate {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by lazy { vm(viewModelFactory, MainActivityViewModel::class) }
    private lateinit var binding: FragmentPersonListBinding
    private lateinit var paginator: RecyclerViewPaginator

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_person_list, container, false)
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
        viewModel.postPeoplePage(page)
    }

    private fun initializeUI() {
        recyclerView.adapter = PeopleAdapter(this)
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        paginator = RecyclerViewPaginator(
            recyclerView = recyclerView,
            isLoading = { viewModel.getPeopleValues()?.status == Status.LOADING },
            loadMore = { loadMore(it) },
            onLast = { viewModel.getPeopleValues()?.onLastPage!! })
    }

    override fun onItemClick(person: Person, view: View) {
    }
}
