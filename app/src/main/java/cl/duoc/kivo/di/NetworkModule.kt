package cl.duoc.kivo.di

import cl.duoc.kivo.data.remote.ApiService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val BASE_URL = "https://api.example.com/"

    @Provides fun provideGson(): Gson = GsonBuilder().create()
    @Provides @Singleton fun provideOkHttp(): OkHttpClient {
        val logging = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
        return OkHttpClient.Builder().addInterceptor(logging).build()
    }

    @Provides @Singleton fun provideRetrofit(gson: Gson, client: OkHttpClient): Retrofit =
        Retrofit.Builder().baseUrl(BASE_URL).client(client).addConverterFactory(GsonConverterFactory.create(gson)).build()

    @Provides @Singleton fun provideApi(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)
}
