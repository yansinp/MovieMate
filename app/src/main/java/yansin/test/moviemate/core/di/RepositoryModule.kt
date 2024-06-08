package yansin.test.moviemate.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import yansin.test.moviemate.data.remote.repository.MovieRepositoryImpl
import yansin.test.moviemate.data.remote.source.MovieApi
import yansin.test.moviemate.domain.repository.MovieRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object RepositoryModule {

    @Provides
    @Singleton
    fun provideMovieRepository(
        movieApi: MovieApi
    ): MovieRepository {
        return MovieRepositoryImpl(movieApi)
    }
}