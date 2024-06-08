package yansin.test.moviemate.presentation.movieDetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import coil.compose.AsyncImage
import yansin.test.moviemate.domain.model.Movies
import yansin.test.moviemate.presentation.moviesList.MovieUiState
import yansin.test.moviemate.presentation.moviesList.MovieViewModel

@Composable
fun MovieDetailsScreen(
    navController: NavController,
    movieViewModel: MovieViewModel,
    movieUiState: MovieUiState
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val arguments = navBackStackEntry?.arguments
    val item  = arguments?.getString("singleId")

    Surface(modifier = Modifier.fillMaxSize()) {
        if (movieUiState.loading) {
            LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
        } else {
            if (movieUiState.movieData == null) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Details Not found")
                }
            } else {

                val singleMovie = movieUiState.movieData.filter { it.movieId == item }

                if (singleMovie.isNotEmpty()){
                    DetailsScreenData(item = singleMovie[0])
                }
            }
        }
    }
}


@Composable
fun DetailsScreenData(item: Movies?)  {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = "https://cdn-icons-png.flaticon.com/512/11183/11183243.png",
            contentDescription =
            " ",
            modifier = Modifier
                .statusBarsPadding()
                .height(293.dp)
                .fillMaxWidth(),
            contentScale = ContentScale.Crop

        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = item?.movieTitle ?: " ",
                modifier = Modifier.fillMaxWidth(),
                fontWeight = FontWeight.SemiBold,
                style = MaterialTheme.typography.titleLarge,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis

            )

            Row(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = item?.movieGenre.toString(),
                    modifier = Modifier.weight(1f),
                    fontWeight = FontWeight.Normal,
                    color = Color.Gray,
                    style = MaterialTheme.typography.titleSmall,
                )
                Row(modifier = Modifier.wrapContentSize()) {
                    Icon(
                        imageVector = Icons.Outlined.DateRange,
                        contentDescription = "",
                        modifier = Modifier.size(16.dp),
                        tint = Color.Gray
                    )

                    Text(
                        text = item?.release ?: " ",
                        modifier = Modifier
                            .wrapContentSize()
                            .padding(start = 4.dp),
                        fontWeight = FontWeight.Normal,
                        color = Color.Gray,
                        style = MaterialTheme.typography.titleSmall,
                    )
                }
            }
            Text(
                text = item?.description ?: " ",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                fontWeight = FontWeight.Normal,
                style = MaterialTheme.typography.titleMedium,
                color = Color.Black,

                )
        }

    }
}

