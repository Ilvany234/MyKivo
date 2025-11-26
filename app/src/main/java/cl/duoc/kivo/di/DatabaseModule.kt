package cl.duoc.kivo.di

import android.content.Context
import androidx.room.Room
import cl.duoc.kivo.data.local.KivoDatabase
import cl.duoc.kivo.data.local.KivoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): KivoDatabase =
        Room.databaseBuilder(context, KivoDatabase::class.java, "kivo_db").build()

    @Provides
    fun provideDao(db: KivoDatabase): KivoDao = db.kivoDao()
}
