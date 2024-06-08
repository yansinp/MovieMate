package yansin.test.moviemate.presentation.moviesList

import yansin.test.moviemate.data.remote.dto.MovieResponse
import yansin.test.moviemate.domain.model.Movies

data class MovieUiState(
    val movieData: List<Movies>? = emptyList(),
    val error: String = "",
    val loading: Boolean = false
)