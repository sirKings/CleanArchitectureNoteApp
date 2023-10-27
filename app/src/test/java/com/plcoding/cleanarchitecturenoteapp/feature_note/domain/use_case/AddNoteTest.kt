package com.plcoding.cleanarchitecturenoteapp.feature_note.domain.use_case

import com.google.common.truth.Truth.assertThat
import com.plcoding.cleanarchitecturenoteapp.feature_note.data.repository.FakeNoteRepository
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.model.InvalidNoteException
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.model.Note
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.repository.NoteRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class AddNoteTest {

    private lateinit var addNote: AddNote
    private lateinit var repository: NoteRepository


    @Before
    fun setUp() {
        repository = FakeNoteRepository()
        addNote = AddNote(repository)
    }

    @Test
    fun givenNoteWithBlankTitle_throwException(){
        val note = Note("", "Content", 2L, 3)
        val exception = assertThrows(InvalidNoteException::class.java){
            runBlockingTest{ addNote(note)}
        }
        assertThat(exception).hasMessageThat().contains(AddNote.INVALID_NOTE_BLANK_TITLE)
    }

    @Test
    fun givenNoteWithBlankContent_throwException(){
        val note = Note("Title", "", 2L, 3)
        val exception = assertThrows(InvalidNoteException::class.java){
            runBlockingTest{ addNote(note)}
        }
        assertThat(exception).hasMessageThat().contains(AddNote.INVALID_NOTE_BLANK_CONTENT)
    }

}