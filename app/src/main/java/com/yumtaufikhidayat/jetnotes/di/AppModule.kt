package com.yumtaufikhidayat.jetnotes.di

import android.app.Application
import androidx.room.Room
import com.yumtaufikhidayat.jetnotes.featurenotes.data.datasource.NotesDatabase
import com.yumtaufikhidayat.jetnotes.featurenotes.data.repository.NoteRepositoryImpl
import com.yumtaufikhidayat.jetnotes.featurenotes.domain.repository.INotesRepository
import com.yumtaufikhidayat.jetnotes.featurenotes.domain.usecase.AddNotesUseCase
import com.yumtaufikhidayat.jetnotes.featurenotes.domain.usecase.DeleteNotesUseCase
import com.yumtaufikhidayat.jetnotes.featurenotes.domain.usecase.GetNotes
import com.yumtaufikhidayat.jetnotes.featurenotes.domain.usecase.GetNotesUseCase
import com.yumtaufikhidayat.jetnotes.featurenotes.domain.usecase.NotesWrapperUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesNotesDatabase(app: Application): NotesDatabase {
        return Room.databaseBuilder(
            app,
            NotesDatabase::class.java,
            NotesDatabase.DB_NAME
        ).fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun providesNotesRepository(notesDatabase: NotesDatabase): INotesRepository {
        return NoteRepositoryImpl(notesDatabase.noteDao)
    }

    @Singleton
    @Provides
    fun providesNotesUseCase(repository: INotesRepository): NotesWrapperUseCase {
        return NotesWrapperUseCase(
            getNotesUseCase = GetNotesUseCase(repository),
            deleteNotesUseCase = DeleteNotesUseCase(repository),
            addNotesUseCase = AddNotesUseCase(repository),
            getNotes = GetNotes(repository)
        )
    }
}