package com.plcoding.cleanarchitecturenoteapp.feature_note.domain.use_case

import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.model.InvalidNoteException
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.model.Note
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.repository.NoteRepository

class AddNote(
    private val repository: NoteRepository
) {

    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note) {
        if(note.title.isBlank()) {
            throw InvalidNoteException(INVALID_NOTE_BLANK_TITLE)
        }
        if(note.content.isBlank()) {
            throw InvalidNoteException(INVALID_NOTE_BLANK_CONTENT)
        }
        repository.insertNote(note)
    }


    companion object{
        const val INVALID_NOTE_BLANK_TITLE = "The title of the note can't be empty."
        const val INVALID_NOTE_BLANK_CONTENT = "The content of the note can't be empty."
    }
}