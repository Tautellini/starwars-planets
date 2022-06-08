package com.example.starwarsplanets.di

import android.content.Context
import androidx.room.Room
import com.example.starwarsplanets.datasources.database.Database
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideDatabase(context: Context): Database =
        Room.databaseBuilder(context, Database::class.java, "star-wars-planets.db").fallbackToDestructiveMigration()
            .build()

}