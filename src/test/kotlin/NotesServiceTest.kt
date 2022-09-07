import exceptions.NoteNotFoundException
import org.junit.Test

import org.junit.Assert.*

class NotesServiceTest {


    @Test
    fun add() {
        val testNote = Note(id = 0, title = "Test note1", text = "Test text")

        val result = NotesService.add(testNote)

        assertNotEquals(0, result)
    }

    @Test
    fun getById() {
        val testNote = Note(title = "Test note", text = "Text")
        NotesService.add(testNote)
        val id = testNote.id
        val result = NotesService.getById(id)

        assertEquals(testNote, result)
    }

    @Test(expected = NoteNotFoundException::class)
    fun getByIdException() {
        val id = 0
        NotesService.getById(id)
    }

    @Test
    fun getList() {
        val testNote1 = Note(title = "Test note1", text = "Text")
        val testNote2 = Note(title = "Test note2", text = "Text")
        val testNote3 = Note(title = "Test note3", text = "Text")
        NotesService.add(testNote1)
        NotesService.add(testNote2)
        NotesService.add(testNote3)
        val noteIds = mutableListOf(testNote3.id, testNote1.id)

        val result =  NotesService.getList(noteIds)

        assertEquals(mutableListOf(testNote3, testNote1), result)
    }

    @Test
    fun editTrue() {
        val testNote1 = Note(title = "Test note", text = "Text")
        NotesService.add(testNote1)
        val newTestNote1 = Note(id = testNote1.id, title = "New test note", text = "New text")

        val result = NotesService.edit(newTestNote1)

        assertEquals(true, result)
    }

    @Test
    fun editFalse() {
        val testNote1 = Note(title = "Test note", text = "Text")
        NotesService.add(testNote1)
        val newTestNote1 = Note(id = (testNote1.id + 1), title = "New test note", text = "New text")

        val result = NotesService.edit(newTestNote1)

        assertEquals(false, result)
    }

    @Test
    fun deleteTrue() {
        val testNote = Note(title = "Test note", text = "Text")
        NotesService.add(testNote)
        val id = testNote.id

        val result = NotesService.delete(id)
        assertEquals(true, result)
    }

    @Test
    fun deleteFalse() {
        val testNote = Note(title = "Test note", text = "Text")
        NotesService.add(testNote)
        val id = testNote.id + 1

        val result = NotesService.delete(id)
        assertEquals(false, result)
    }
}