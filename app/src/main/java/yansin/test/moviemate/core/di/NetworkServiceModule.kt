package yansin.test.moviemate.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import yansin.test.moviemate.data.remote.source.MovieApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkServiceModule {
    @Provides
    @Singleton
    fun provideMovieService(retrofit: Retrofit): MovieApi {
        return retrofit.create(MovieApi::class.java)
    }

}