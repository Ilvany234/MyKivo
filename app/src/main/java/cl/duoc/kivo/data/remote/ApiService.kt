package cl.duoc.kivo.data.remote

import cl.duoc.kivo.data.remote.dto.*
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    // --- Autenticación ---
    @POST("auth/signup")
suspend fun signup(@Body signupRequest: SignupRequest): LoginResponse

    @POST("auth/login")
suspend fun login(@Body loginRequest: LoginRequest): LoginResponse

    // --- Reseñas ---
    @GET("resenas")
suspend fun getReviews(): List<ReviewResponse>

    @POST("resenas")
suspend fun postReview(@Body reviewRequest: ReviewRequest): ReviewResponse
}
