package com.example.lab5

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lab5.databinding.FragmentFormBinding
import java.util.UUID

class FormFragment : Fragment() {
    private lateinit var _binding: FragmentFormBinding
    private var _listener: OnDataPassListener? = null

    interface OnDataPassListener {
        fun add(newValue: Note)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnDataPassListener) {
            _listener = context
        } else {
            throw RuntimeException("$context must implement OnDataPassListener")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFormBinding.inflate(inflater)
        val view = _binding.root

        _binding.addNoteButton.setOnClickListener {
            val note = Note(
                id = UUID.randomUUID().toString(),
                title = _binding.titleInput.text.toString(),
                shortDescription = _binding.shortDescriptionInput.text.toString(),
                )

            _listener?.add(note)
        }

        return view
    }

    override fun onDetach() {
        super.onDetach()
        _listener = null
    }

    companion object {
        fun newInstance(): FormFragment {
            return FormFragment()
        }
    }
}
