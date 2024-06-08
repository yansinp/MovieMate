package yansin.test.moviemate.domain.model

data class Movies(
    val id: String,
    val genre: String,
    val type: Int,
    val movieId: String,
    val movieTitle: String,
    val description: String,
    val movieGenre: List<String>,
    val release: String,
    val posterUrl: String,
    val rating: String
)
