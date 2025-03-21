package com.elekiwi.moviesappprometeo.core.di

import android.app.Application
import androidx.room.Room
import com.elekiwi.moviesappprometeo.core.data.local.MovieDao
import com.elekiwi.moviesappprometeo.core.data.local.MovieDatabase
import com.elekiwi.moviesappprometeo.core.data.remote.services.FirebaseMovieService
import com.elekiwi.moviesappprometeo.core.data.repositories.MovieListRepositoryImpl
import com.elekiwi.moviesappprometeo.core.domain.repositories.MovieListRepository
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMovieDatabase(application: Application): MovieDatabase {
        return Room.databaseBuilder(
            application,
            MovieDatabase::class.java,
            "movies_db.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideMovieDao(db: MovieDatabase): MovieDao {
        return db.movieDao
    }

    @Provides
    @Singleton
    fun provideFirebaseDatabase(): FirebaseDatabase {
        return FirebaseDatabase.getInstance()
    }

    @Provides
    @Singleton
    fun provideFirebaseMovieService(firebaseDatabase: FirebaseDatabase): FirebaseMovieService {
        return FirebaseMovieService(firebaseDatabase)
    }

    @Provides
    @Singleton
    fun provideMovieRepository(
        movieDao: MovieDao,
        firebaseService: FirebaseMovieService
    ): MovieListRepository {
        return MovieListRepositoryImpl(movieDao, firebaseService)
    }
}