package yansin.test.moviemate.core.navigation

sealed class NavigationScreen(val route:String){
    object NavigationMovieScreen:NavigationScreen(route="MovieScreen")
    object NavigationMovieDetailsScreen:NavigationScreen(route="MovieDetailsScreen")
}