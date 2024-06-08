package yansin.test.moviemate.data.remote.repository

import com.example.machinetest.core.util.Constants
import com.example.machinetest.core.util.Constants.GENERAL_ERROR_MESSAGE
import com.example.machinetest.core.util.Resource
import com.skydoves.sandwich.StatusCode
import com.skydoves.sandwich.suspendOnError
import com.skydoves.sandwich.suspendOnException
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import yansin.test.moviemate.data.remote.source.MovieApi
import yansin.test.moviemate.domain.model.Movies
import yansin.test.moviemate.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieApi: MovieApi
) : MovieRepository {
    override fun getMovies(): Flow<Resource<List<Movies>>> = flow {

        emit(Resource.Loading)
        movieApi.getMovies()
            .suspendOnSuccess {
                val moviesResponse = this.data
                Timber.tag("MoviesData").d("in MovieRepositoryImpl ${this.data}")

                val movies = moviesResponse.homeData?.flatMap { it.toMoviesList() } ?: emptyList()
                emit(Resource.Success(movies))
            }
            .suspendOnError {
                try {

                    when (this.statusCode) {
                        StatusCode.InternalServerError -> emit(Resource.Error(Constants.SERVER_ERROR))
                        else -> {
                            emit(Resource.Error("Unable to fetch movie details"))
                        }
                    }
                } catch (e: Exception) {
                    emit(Resource.Error(GENERAL_ERROR_MESSAGE))
                }
            }
            .suspendOnException { emit(Resource.Error(Constants.NO_INTERNET_ERROR_MESSAGE)) }


    }
}