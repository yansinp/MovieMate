package yansin.test.moviemate.presentation.moviesList.item

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import timber.log.Timber
import yansin.test.moviemate.domain.model.Movies

@Composable
fun MovieItem(item: Movies, onItemClick: (String) -> Unit) {
    Timber.tag("MoviesData").d("item.movieTitle: ${item.movieTitle}")
    if (item.movieTitle.isNotEmpty()) {
        Surface(
            modifier = Modifier
                .clickable {
                    onItemClick.invoke(item.movieId)
                }
                .padding(vertical = 8.dp, horizontal = 8.dp)
                .wrapContentHeight()
                .background(MaterialTheme.colors.onSurface)
        ) {
            Column(
                modifier = Modifier
                    .padding(vertical = 8.dp, horizontal = 8.dp)
                    .fillMaxWidth()
                    .wrapContentHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("https://cdn-icons-png.flaticon.com/512/11183/11183243.png")
                        .crossfade(true)
                        .build(),
                    contentDescription = "",
                    modifier = Modifier
                        .size(150.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = item.movieTitle,
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .fillMaxWidth(),
                    fontWeight = FontWeight.Light,
                    color = Color.Black,
                    style = androidx.compose.material3.MaterialTheme.typography.titleLarge,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 18.sp,
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )
            }
        }
    } else {
        Text("No title available")
    }
}
