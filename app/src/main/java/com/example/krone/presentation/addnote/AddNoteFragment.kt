package com.example.krone.presentation.addnote

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.krone.data.Note
import com.example.krone.databinding.FragmentAddNoteBinding
import com.example.krone.presentation.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddNoteFragment : Fragment() {

    private lateinit var binding: FragmentAddNoteBinding
    private val viewModel: AddNoteViewModel by viewModels()
    private var onNoteInsertedListener: OnNoteInsertedListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnNoteInsertedListener) {
            onNoteInsertedListener = context
        } else {
            throw RuntimeException("$context must implement OnNoteInsertedListener")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSave.setOnClickListener {
            viewModel.insert(
                Note(
                    id = 0,
                    header = binding.vHeaderInput.text.toString(),
                    body = binding.vTvNoteBodyInput.text.toString()
                )
            )
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        onNoteInsertedListener?.onNoteInserted()
    }
    interface OnNoteInsertedListener {
        fun onNoteInserted()
    }
}