package yansin.test.moviemate.presentation.moviesList

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import timber.log.Timber
import yansin.test.moviemate.presentation.moviesList.item.MovieItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieListScreen(movieUiState: MovieUiState,onItemClick: (String) -> Unit) {

    var text by remember { mutableStateOf("Search Here ") }
    var active by remember { mutableStateOf(false) }
    val lazyListState = rememberLazyListState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
    ) {
        CenterAlignedTopAppBar(title = {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(end = 5.dp)
            ) {
                SearchBar(
                    query = text,
                    onQueryChange = {
                        text = it
                    },
                    onSearch = {
                        // Optionally handle the search action here
                    },
                    active = active,
                    onActiveChange = {
                        active = it
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search Icon"
                        )
                    },
                    trailingIcon = {
                        if (active) {
                            Icon(
                                modifier = Modifier.clickable {
                                    if (text.isNotEmpty()) {
                                        text = ""
                                    } else {
                                        active = false
                                    }
                                },
                                imageVector = Icons.Default.Clear,
                                contentDescription = "Close Icon"
                            )
                        }
                    }
                ) {}
            }
        })

        if (movieUiState.loading) {
            LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
        } else {
            Timber.tag("MoviesData").d("movieUiState.movieData: ${movieUiState.movieData}")
            if (!movieUiState.movieData.isNullOrEmpty()) {
                val actionMovies = movieUiState.movieData.filter { it.id == "1" }
                val dramaMovies = movieUiState.movieData.filter { it.id == "2" }
                val comedyMovies = movieUiState.movieData.filter { it.id == "3" }

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .navigationBarsPadding(),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                    state = lazyListState
                ) {
                    item {
                        Text(
                            text = "Action",
                            style = MaterialTheme.typography.titleLarge,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        LazyRow(
                            modifier = Modifier.fillMaxWidth(),
                            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                        ) {
                            items(actionMovies) { movie ->
                                MovieItem(movie) {singleMovie->
                                    onItemClick.invoke(singleMovie)

                                }
                            }
                        }
                    }

                    item {
                        Text(
                            text = "Drama",
                            style = MaterialTheme.typography.titleLarge,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        LazyRow(
                            modifier = Modifier.fillMaxWidth(),
                            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                        ) {
                            items(dramaMovies) { movie ->
                                MovieItem(movie){singleMovie->
                                    onItemClick.invoke(singleMovie)
                                }
                            }
                        }
                    }

                    item {
                        Text(
                            text = "Comedy",
                            style = MaterialTheme.typography.titleLarge,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        LazyRow(
                            modifier = Modifier.fillMaxWidth(),
                            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                        ) {
                            items(comedyMovies) { movie ->
                                MovieItem(movie){singleMovie->
                                    onItemClick.invoke(singleMovie)

                                }

                                Timber.tag("MoviesData")
                                    .d("Passed from MovieScreen to Lazy Column $movie")
                            }
                        }
                    }
                }
            } else {
                Text("No data available")
            }
        }
    }
}
