package com.example.krone.di

import android.content.Context
import androidx.room.Room
import com.example.krone.domain.DatabaseRepository
import com.example.krone.data.AppDatabase
import com.example.krone.data.DatabaseRepositoryImpl
import com.example.krone.data.NotesDao
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
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "app_database")
            .build()
    }

    @Provides
    fun provideRatesDao(database: AppDatabase): NotesDao {
        return database.notesDao()
    }

    @Provides
    @Singleton
    fun provideDatabaseRepository(dao: NotesDao): DatabaseRepository {
        return DatabaseRepositoryImpl(dao = dao)
    }
}