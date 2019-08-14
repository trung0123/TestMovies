package com.example.testmovies.view.adapter

import android.view.View
import com.example.testmovies.R
import com.example.testmovies.models.Resource
import com.example.testmovies.models.entity.Movie
import com.example.testmovies.view.viewholder.MovieListViewHolder
import com.skydoves.baserecyclerviewadapter.BaseAdapter
import com.skydoves.baserecyclerviewadapter.BaseViewHolder
import com.skydoves.baserecyclerviewadapter.SectionRow

class MovieListAdapter(private val delegate: MovieListViewHolder.Delegate) : BaseAdapter() {
    init {
        addSection(ArrayList<Movie>())
    }

    fun addMovieList(resource: Resource<List<Movie>>) {
        resource.data?.let {
            sections()[0].addAll(it)
            notifyDataSetChanged()
        }
    }

    override fun layout(sectionRow: SectionRow): Int {
        return R.layout.item_poster
    }

    override fun viewHolder(layout: Int, view: View): BaseViewHolder {
        return MovieListViewHolder(view, delegate)
    }


}