package com.example.krone.data

import com.example.krone.domain.DatabaseRepository
import javax.inject.Inject

class DatabaseRepositoryImpl @Inject constructor(
    private val dao: NotesDao
): DatabaseRepository {
    override suspend fun insertNote(note: Note) {
        dao.insertNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        dao.deleteNote(note)
    }

    override suspend fun getAll(): List<Note> = dao.getAll()

    override suspend fun updateNote(note: Note) {
        dao.updateNote(note)
    }
}