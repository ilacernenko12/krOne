package com.example.krone.presentation.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.krone.presentation.addnote.AddNoteFragment
import com.example.krone.presentation.recycler.NotesAdapter
import com.example.krone.R
import com.example.krone.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), AddNoteFragment.OnNoteInsertedListener {

    private val binding: ActivityMainBinding by viewBinding(ActivityMainBinding::bind)
    private val viewModel: MainViewModel by viewModels()
    private val adapter = NotesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initAdapter()
        setListeners()
    }

    private fun setListeners() {
        viewModel.notes.observe(this) { notes ->
            adapter.updateData(notes)
        }

        binding.vBtnAdd.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, AddNoteFragment())
                .addToBackStack(null)
                .commit()
        }

        adapter.setOnItemClickListener { note ->
            viewModel.delete(note)
        }

        adapter.setOnPinClickListener { note ->
            viewModel.updateNote(note)
        }
    }

    private fun initAdapter() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }

    override fun onNoteInserted() {
        viewModel.getAll()
    }
}