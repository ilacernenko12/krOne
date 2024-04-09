package com.example.krone.presentation.addnote

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
class AddNoteViewModel @Inject constructor(
    private val repository: DatabaseRepository
): ViewModel() {

    fun insert(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertNote(note = note)
        }
    }
}