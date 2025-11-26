package cl.duoc.kivo.data.remote

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

data class UserDto(val name: String, val email: String, val age: Int)
data class ReviewDto(val authorEmail: String, val text: String)
data class FavoriteDto(val word: String, val description: String)

interface ApiService {
    @GET("users/{email}")
    suspend fun getUser(@Path("email") email: String): Response<UserDto>

    @POST("reviews")
    suspend fun postReview(@Body review: ReviewDto): Response<Void>

    @GET("favorites")
    suspend fun getFavorites(): Response<List<FavoriteDto>>
}
