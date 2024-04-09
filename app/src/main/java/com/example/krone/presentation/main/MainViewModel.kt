package com.example.krone.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.krone.data.Note
import com.example.krone.domain.DatabaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: DatabaseRepository
): ViewModel() {

    init {
        getAll()
    }

    private val _notes: MutableLiveData<List<Note>> = MutableLiveData()
    val notes: LiveData<List<Note>> = _notes

    fun delete(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteNote(note = note)
            _notes.postValue(repository.getAll())
        }
    }

    fun getAll() {
        viewModelScope.launch(Dispatchers.IO) {
            _notes.postValue(repository.getAll())
        }
    }

    fun updateNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateNote(Note(id = note.id, header = note.header, body = note.body, isPinned = !note.isPinned))
            _notes.postValue(repository.getAll())
        }
    }
}