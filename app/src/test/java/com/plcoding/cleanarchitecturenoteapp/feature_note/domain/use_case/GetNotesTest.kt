package com.plcoding.cleanarchitecturenoteapp.feature_note.domain.use_case

import com.google.common.truth.Truth.assertThat
import com.plcoding.cleanarchitecturenoteapp.feature_note.data.repository.FakeNoteRepository
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.model.Note
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.repository.NoteRepository
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.util.NoteOrder
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.util.OrderType
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest

import org.junit.Before
import org.junit.Test
import kotlin.random.Random

@OptIn(ExperimentalCoroutinesApi::class)
class GetNotesTest {

    private lateinit var getNotes: GetNotes
    private lateinit var repository: NoteRepository
    private lateinit var notesToInsert: MutableList<Note>

    @Before
    fun setUp() {
        repository = FakeNoteRepository()
        getNotes = GetNotes(repository)

        notesToInsert = mutableListOf()
        ('a'..'z').forEachIndexed { index, c ->
            val note = Note(c.toString(),c.toString(), index.toLong(), Random.Default.nextInt())
            notesToInsert.add(note)
        }
        notesToInsert.shuffle()
        runBlockingTest {  notesToInsert.forEach { repository.insertNote(it) }}
    }

    @Test
    fun callingGetNotesWithDateAscending_returnsNotesOrderedDateAscending() = runBlockingTest{
        val notes = getNotes(NoteOrder.Date(OrderType.Ascending)).first()
        assertThat(notes).isEqualTo(notesToInsert.sortedBy { it.timestamp })
    }

    @Test
    fun callingGetNotesWithDateDescending_returnsNotesOrderedDateDescending() = runBlockingTest{
        val notes = getNotes(NoteOrder.Date(OrderType.Descending)).first()
        assertThat(notes).isEqualTo(notesToInsert.sortedByDescending { it.timestamp })
    }

    @Test
    fun callingGetNotesWithTitleAscending_returnsNotesOrderedTitleAscending() = runBlockingTest{
        val notes = getNotes(NoteOrder.Title(OrderType.Ascending)).first()
        assertThat(notes).isEqualTo(notesToInsert.sortedBy { it.title })
    }

    @Test
    fun callingGetNotesWithTitleDescending_returnsNotesOrderedTitleDescending() = runBlockingTest{
        val notes = getNotes(NoteOrder.Title(OrderType.Descending)).first()
        assertThat(notes).isEqualTo(notesToInsert.sortedByDescending { it.title })
    }

    @Test
    fun callingGetNotesWithColorAscending_returnsNotesOrderedColorAscending() = runBlockingTest{
        val notes = getNotes(NoteOrder.Color(OrderType.Ascending)).first()
        assertThat(notes).isEqualTo(notesToInsert.sortedBy { it.color })
    }

    @Test
    fun callingGetNotesWithColorDescending_returnsNotesOrderedColorDescending() = runBlockingTest{
        val notes = getNotes(NoteOrder.Color(OrderType.Descending)).first()
        assertThat(notes).isEqualTo(notesToInsert.sortedByDescending { it.color })
    }


}