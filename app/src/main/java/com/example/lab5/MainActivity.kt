package com.example.lab5

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(),
    FormFragment.OnDataPassListener,
    ListFragment.OnDataPassListener,
    DetailFragment.OnDataPassListener
{
    private val _contacts = NoteData.notes

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        renderFormAndList()
    }

    override fun add(newValue: Note) {
        _contacts.add(newValue)
        renderFormAndList()
    }

    override fun openDetailFragment(note: Note) {
        val formFragment = DetailFragment.newInstance(note)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.firstFragment, formFragment)
            .remove(supportFragmentManager.findFragmentById(R.id.secondFragment) ?: return)
            .addToBackStack(null)
            .commit()
    }

    override fun back() {
        supportFragmentManager.popBackStack()
    }

    override fun saveChanges(note: Note) {
        val index = _contacts.indexOfFirst { it.id == note.id }
        if (index != -1) {
            _contacts[index] = note
            renderFormAndList()
        }
    }

    override fun delete(noteId: String) {
        val noteToRemove = _contacts.find { it.id == noteId }
        if (noteToRemove != null) {
            _contacts.remove(noteToRemove)
            renderFormAndList()
        }
    }

    private fun renderFormAndList() {
        val formFragment = FormFragment.newInstance()
        val listFragment = ListFragment.newInstance(_contacts)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.firstFragment, formFragment)
            .replace(R.id.secondFragment, listFragment)
            .addToBackStack(null)
            .commit()
    }
}
