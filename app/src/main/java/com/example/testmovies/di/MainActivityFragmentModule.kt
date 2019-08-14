package com.example.testmovies.di

import com.example.testmovies.view.ui.main.MovieListFragment
import com.example.testmovies.view.ui.main.PersonListFragment
import com.example.testmovies.view.ui.main.TvListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class MainActivityFragmentModule {
    @ContributesAndroidInjector
    abstract fun contributeMovieListFragment(): MovieListFragment

    @ContributesAndroidInjector
    abstract fun contributeTvListFragment(): TvListFragment

    @ContributesAndroidInjector
    abstract fun contributePersonListFragment(): PersonListFragment
}