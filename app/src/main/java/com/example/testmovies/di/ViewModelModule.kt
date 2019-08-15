package com.example.testmovies.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testmovies.factory.AppViewModelFactory
import com.example.testmovies.view.ui.details.movie.MovieDetailViewModel
import com.example.testmovies.view.ui.details.person.PersonDetailViewModel
import com.example.testmovies.view.ui.details.tv.TvDetailViewModel
import com.example.testmovies.view.ui.main.MainActivityViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
internal abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    internal abstract fun bindMainActivityViewModels(mainActivityViewModel: MainActivityViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MovieDetailViewModel::class)
    internal abstract fun bindMovieDetailViewModel(movieDetailViewModel: MovieDetailViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TvDetailViewModel::class)
    internal abstract fun bindTvDetailViewModel(tvDetailViewModel: TvDetailViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PersonDetailViewModel::class)
    internal abstract fun bindPersonDetailViewModel(personDetailViewModel: PersonDetailViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: AppViewModelFactory): ViewModelProvider.Factory

}