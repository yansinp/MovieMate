package yansin.test.moviemate.data.remote.source

import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query
import yansin.test.moviemate.data.remote.dto.MovieResponse

interface MovieApi {

    @GET("dummy.json")
    suspend fun getMovies(): ApiResponse<MovieResponse>

}