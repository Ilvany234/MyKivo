package cl.duoc.kivo.data.remote

import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("reviews")
    suspend fun getReviews(): List<ReviewDto>

    @GET("favorites")
    suspend fun getFavorites(): List<FavoriteDto>
}

data class ReviewDto(val id: Long, val authorEmail: String, val text: String, val timestamp: Long)
data class FavoriteDto(val id: Long, val word: String, val description: String)
