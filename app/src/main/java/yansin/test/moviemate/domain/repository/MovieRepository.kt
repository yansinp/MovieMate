package yansin.test.moviemate.domain.repository

import com.example.machinetest.core.util.Resource
import kotlinx.coroutines.flow.Flow
import yansin.test.moviemate.domain.model.Movies

interface MovieRepository {

    fun getMovies(): Flow<Resource<List<Movies>>>
}