package yansin.test.moviemate.data.remote.dto

import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import yansin.test.moviemate.domain.model.Movies

@Keep
@JsonClass(generateAdapter = true)
data class MovieResponse(

    @Json(name = "title")
    val title: String?,

    @Json(name = "homeData")
    val homeData: List<HomeData>?


) {
    @Keep
    @JsonClass(generateAdapter = true)
    data class HomeData(

        @Json(name = "id")
        val id: String?,

        @Json(name = "genre")
        val genre: String?,

        @Json(name = "type")
        val type: Int?,

        @Json(name = "movieslist")
        val movieslist: List<MovieList>?
    ) {


        @Keep
        @JsonClass(generateAdapter = true)
        data class MovieList(

            @Json(name = "id")
            val id: String?,

            @Json(name = "title")
            val title: String?,

            @Json(name = "desc")
            val desc: String?,

            @Json(name = "genre")
            val genre: List<String>,

            @Json(name = "release")
            val release: String?,

            @Json(name = "posterurl")
            val posterurl: String?,

            @Json(name = "rating")
            val rating: String?,
        )

        fun toMoviesList(): List<Movies> {
            return movieslist?.map { movie ->
                Movies(
                    id = id ?: "",
                    genre = genre ?: "",
                    type = type ?: 0,
                    movieId = movie.id ?: "",
                    movieTitle = movie.title ?: "",
                    description = movie.desc ?: "",
                    movieGenre = movie.genre ?: listOf(),
                    release = movie.release ?: "",
                    posterUrl = movie.posterurl ?: "",
                    rating = movie.rating ?: ""
                )
            } ?: emptyList()
        }
    }
}
