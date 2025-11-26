package cl.duoc.kivo.data.repository

import cl.duoc.kivo.data.local.KivoDao
import cl.duoc.kivo.data.local.entities.FavoriteEntity
import cl.duoc.kivo.data.local.entities.ReviewEntity
import cl.duoc.kivo.data.local.entities.UserEntity
import cl.duoc.kivo.data.remote.ApiService
import cl.duoc.kivo.data.remote.ReviewDto
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class KivoRepository @Inject constructor(private val dao: KivoDao, private val api: ApiService) {
    // Local
    suspend fun registerLocalUser(user: UserEntity) = dao.insertUser(user)
    suspend fun getLocalUser(email: String) = dao.getUserByEmail(email)
    suspend fun getAnyLocalUser() = dao.getAnyUser()
    suspend fun insertReviewLocal(review: ReviewEntity) = dao.insertReview(review)
    suspend fun getAllReviewsLocal() = dao.getAllReviews()
    suspend fun insertFavoriteLocal(fav: FavoriteEntity) = dao.insertFavorite(fav)
    suspend fun getAllFavoritesLocal() = dao.getAllFavorites()

    // Remote
    suspend fun postReviewRemote(authorEmail: String, text: String) = api.postReview(ReviewDto(authorEmail, text))
    suspend fun getRemoteUser(email: String) = api.getUser(email)
    suspend fun getRemoteFavorites() = api.getFavorites()
}
