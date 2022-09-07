import exceptions.NoteNotFoundException
import org.junit.Test

import org.junit.Assert.*

class CommentServiceTest {

    @Test
    fun add() {
        val testNote = Note(title = "Test note", text = "Text")
        NotesService.add(testNote)
        val testComment = Comment(id = 0, message = "Test message", noteId = testNote.id)
        val result = CommentService.add(testComment)
        assertNotEquals(0, result)
    }

    @Test(expected = NoteNotFoundException::class)
    fun notAdded() {
        val testComment = Comment(id = 0, message = "Test message", noteId = 0)
        CommentService.add(testComment)
    }

    @Test
    fun getComments() {
        val testNote = Note(title = "Test note", text = "Text")
        NotesService.add(testNote)
        val noteId = testNote.id
        val testComment1 = Comment(message = "Test message", noteId = noteId)
        val testComment2 = Comment(message = "Test message", noteId = noteId)
        CommentService.add(testComment1)
        CommentService.add(testComment2)
        val result = CommentService.getComments(noteId)
        assertEquals(mutableListOf(testComment1, testComment2), result)

    }

    @Test
    fun getCommentsEmpty() {
        val testNote = Note(title = "Test note", text = "Text")
        NotesService.add(testNote)
        val noteId = testNote.id
        val result = CommentService.getComments(noteId)
        assertEquals(mutableListOf<Comment>(), result)
    }

    @Test
    fun editTrue() {
        val testNote = Note(title = "Test note", text = "Text")
        NotesService.add(testNote)
        val testComment = Comment(message = "Test message", noteId = testNote.id)
        CommentService.add(testComment)
        val newTestComment = Comment(id = testComment.id, message = "New message", noteId = testNote.id)
        val result = CommentService.edit(newTestComment)
        assertEquals(true, result)
    }

    @Test
    fun editFalse() {
        val testNote = Note(title = "Test note", text = "Text")
        NotesService.add(testNote)
        val testComment = Comment(message = "Test message", noteId = testNote.id)
        CommentService.add(testComment)
        val newTestComment = Comment(id = (testComment.id + 1), message = "New message", noteId = testNote.id)
        val result = CommentService.edit(newTestComment)
        assertEquals(false, result)
    }

    @Test
    fun deleteTrue() {
        val testNote = Note(title = "Test note", text = "Text")
        NotesService.add(testNote)
        val testComment = Comment(message = "Test message", noteId = testNote.id)
        CommentService.add(testComment)
        val id = testComment.id
        val result = CommentService.delete(id)
        assertEquals(true, result)
    }

    @Test
    fun deleteFalse() {
        val testNote = Note(title = "Test note", text = "Text")
        NotesService.add(testNote)
        val testComment = Comment(message = "Test message", noteId = testNote.id)
        CommentService.add(testComment)
        val id = testComment.id + 1
        val result = CommentService.delete(id)
        assertEquals(false, result)
    }

    @Test
    fun restoreCommentTrue() {
        val testNote = Note(title = "Test note", text = "Text")
        NotesService.add(testNote)
        val testComment = Comment(message = "Test message", noteId = testNote.id)
        CommentService.add(testComment)
        CommentService.delete(testComment.id)
        val result = CommentService.restoreComment(testComment.id)
        assertEquals(true, result)
    }

    @Test
    fun restoreCommentFalse() {
        val testNote = Note(title = "Test note", text = "Text")
        NotesService.add(testNote)
        val testComment = Comment(message = "Test message", noteId = testNote.id)
        CommentService.add(testComment)
        val result = CommentService.restoreComment(testComment.id)
        assertEquals(false, result)
    }
}