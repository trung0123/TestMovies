package com.example.testmovies.di

import android.app.Application
import androidx.annotation.NonNull
import androidx.room.Room
import com.example.testmovies.room.AppDatabase
import com.example.testmovies.room.MovieDao
import com.example.testmovies.room.PeopleDao
import com.example.testmovies.room.TvDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PersistenceModule {
    @Provides
    @Singleton
    fun provideDatabase(@NonNull application: Application): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "TheMovies.db").allowMainThreadQueries().build()
    }

    @Provides
    @Singleton
    fun provideMovieDao(@NonNull database: AppDatabase): MovieDao {
        return database.movieDao()
    }

    @Provides
    @Singleton
    fun provideTvDao(@NonNull database: AppDatabase): TvDao {
        return database.tvDao()
    }

    @Provides
    @Singleton
    fun providePeopleDao(@NonNull database: AppDatabase): PeopleDao {
        return database.peopleDao()
    }
}