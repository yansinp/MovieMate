package yansin.test.moviemate.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import yansin.test.moviemate.presentation.movieDetails.MovieDetailsScreen
import yansin.test.moviemate.presentation.moviesList.MovieListScreen
import yansin.test.moviemate.presentation.moviesList.MovieViewModel

@Composable
fun moviesNavigation(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = NavigationScreen.NavigationMovieScreen.route,
        modifier = Modifier
    ) {
        composable(NavigationScreen.NavigationMovieScreen.route){
            val viewModel: MovieViewModel = hiltViewModel()
            val uiState = viewModel.movieState.value
            MovieListScreen(uiState){
                navController.navigate(NavigationScreen.NavigationMovieDetailsScreen.route + "/$it")
            }
        }

        composable(NavigationScreen.NavigationMovieDetailsScreen.route + "/{singleId}") {
            val viewModel: MovieViewModel = hiltViewModel()
            val movieUiState = viewModel.movieState.value
            MovieDetailsScreen(navController,viewModel,movieUiState)
        }

    }

}