package com.example.lab5

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.lab5.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {
    private lateinit var _binding: FragmentDetailBinding
    private var _listener: OnDataPassListener? = null
    private lateinit var _note: Note

    interface OnDataPassListener {
        fun back()
        fun saveChanges(note: Note)
        fun delete(noteId: String)
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

        arguments?.let {
            _note = it.getParcelable(ARG_NOTE) ?: Note()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater)
        val view = _binding.root

        _binding.titleInput.setText(_note.title)
        _binding.shortDescriptionInput.setText(_note.shortDescription)


        _binding.backButton.setOnClickListener {
            _listener?.back()
        }
        _binding.saveChangesButton.setOnClickListener {
            val note = Note(
                id = _note.id,
                title = _binding.titleInput.text.toString(),
                shortDescription = _binding.shortDescriptionInput.text.toString(),
            )

            _listener?.saveChanges(note)
        }
        _binding.deleteButton.setOnClickListener {
            _listener?.delete(_note.id)
        }

        return view
    }

    override fun onDetach() {
        super.onDetach()
        _listener = null
    }

    companion object {
        private const val ARG_NOTE = "note"

        fun newInstance(note: Note): DetailFragment {
            val fragment = DetailFragment()
            val args = Bundle()
            args.putParcelable(ARG_NOTE, note)
            fragment.arguments = args
            return fragment
        }
    }
}
