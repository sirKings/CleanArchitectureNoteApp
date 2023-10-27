package com.plcoding.cleanarchitecturenoteapp.feature_note.data.repository

import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.model.Note
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeNoteRepository: NoteRepository {

    private val notesList = mutableListOf<Note>()

    override fun getNotes(): Flow<List<Note>> {
        return flow {
            emit(notesList)
        }
    }

    override suspend fun getNoteById(id: Int): Note? {
        return notesList.firstOrNull { note: Note -> note.id == id }
    }

    override suspend fun insertNote(note: Note) {
        notesList.add(note)
    }

    override suspend fun deleteNote(note: Note) {
        notesList.remove(note)
    }

}