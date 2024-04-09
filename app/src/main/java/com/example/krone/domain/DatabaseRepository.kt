package com.example.krone.domain

import com.example.krone.data.Note

interface DatabaseRepository {
    suspend fun insertNote(note: Note)
    suspend fun deleteNote(note: Note)
    suspend fun getAll(): List<Note>
    suspend fun updateNote(note: Note)
}