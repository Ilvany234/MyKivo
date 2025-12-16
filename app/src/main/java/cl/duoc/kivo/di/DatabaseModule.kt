package cl.duoc.kivo.di

import android.content.Context
import androidx.room.Room
import cl.duoc.kivo.data.local.KivoDao
import cl.duoc.kivo.data.local.KivoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): KivoDatabase {
        return Room.databaseBuilder(
            context,
            KivoDatabase::class.java,
            "kivo_database"
        )
        // --- ¡SOLUCIÓN! ---
        // Permite a Room recrear la base de datos si las migraciones fallan
        .fallbackToDestructiveMigration()
        .build()
    }

    @Provides
    @Singleton
    fun provideDao(database: KivoDatabase): KivoDao {
        return database.kivoDao()
    }
}
