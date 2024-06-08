package yansin.test.moviemate.presentation.moviesList

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.machinetest.core.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import yansin.test.moviemate.data.remote.repository.MovieRepositoryImpl
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieRepository: MovieRepositoryImpl,
) : ViewModel() {

    var movieState = mutableStateOf(MovieUiState())
        private set

    init {
        viewModelScope.launch {
            movieRepository.getMovies().collect {

                Timber.tag("MoviesData").d("in ViewModel $it")

                when (it) {
                    is Resource.Empty -> {}

                    is Resource.Error -> {
                        movieState.value =
                            movieState.value.copy(error = it.error, loading = false)
                    }

                    is Resource.Loading -> {
                        movieState.value = movieState.value.copy(loading = true)
                    }

                    is Resource.Success -> {
                        movieState.value = movieState.value.copy(
                            error = "",
                            loading = false,
                            movieData = it.value
                        )

                    }
                }
            }
        }
    }
}