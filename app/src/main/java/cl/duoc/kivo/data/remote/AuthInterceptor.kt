package cl.duoc.kivo.data.remote

import cl.duoc.kivo.data.SessionManager
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val sessionManager: SessionManager
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        // Obtenemos el token de nuestro SessionManager
        // Usamos runBlocking porque estamos fuera de un scope de corrutina
        val token = runBlocking {
            sessionManager.tokenFlow.first()
        }

        val requestBuilder = chain.request().newBuilder()

        // Si tenemos un token, lo añadimos a la cabecera "Authorization"
        if (token != null) {
            requestBuilder.addHeader("Authorization", "Bearer $token")
        }

        return chain.proceed(requestBuilder.build())
    }
}
