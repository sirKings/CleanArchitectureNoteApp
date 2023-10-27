package com.plcoding.cleanarchitecturenoteapp.feature_note.domain.use_case

import com.google.common.truth.Truth.assertThat
import com.plcoding.cleanarchitecturenoteapp.feature_note.data.repository.FakeNoteRepository
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.model.Note
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.repository.NoteRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest

import org.junit.Before
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class GetNoteTest {

    private lateinit var getNote: GetNote
    private lateinit var repository: NoteRepository


    @Before
    fun setUp() {
        repository = FakeNoteRepository()
        getNote = GetNote(repository)
    }

    @Test
    fun givenNoteInserted_returnInstertedNote() = runBlockingTest{
        val note = Note("test", "test content", 2L, 2, 1)
        repository.insertNote(note)
        assertThat(note).isEqualTo(repository.getNoteById(1))
    }
}