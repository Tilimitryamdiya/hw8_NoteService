import exceptions.NoteNotFoundException

class Note(
    override var id: Int = 0,
    val ownerId: Int = 0,
    val title: String,
    val text: String = "No text",
    val date: Int = 0,
    var comments: Int = 0,
    val readComments: Int = 0,
    val view_url: String = "No url",
    val privacyView: String = "default",
    val canComment: Int = 0,
    val textWiki: String = "No tags"
) : Item(id) {
    override fun toString(): String {
        return "ID $id $title:  $text. Comments: $comments"
    }
}

object NotesService : Service<Note>() {

    private var notes = mutableListOf<Note>()
    private var deletedNotes = mutableListOf<Note>()
    private var nid = 0


    fun printNotes() {
        for (note in notes) {
            println("ID ${note.id} ${note.title}:  ${note.text}")
        }

    }

    override fun setId(): Int {
        nid++
        return nid
    }

    override fun add(note: Note): Int {
        note.id = setId()
        notes += note
        return note.id
    }

    override fun getById(noteId: Int): Note? {
        for ((index, note) in notes.withIndex()) {
            if (note.id == noteId) {
                return notes[index]
            }
        }
        throw NoteNotFoundException()
    }

    fun getList(noteIds: List<Int>): List<Note> {
        val getNotes = mutableListOf<Note>()
        for (noteId in noteIds) {
            for ((index, note) in notes.withIndex()) {
                if (note.id == noteId) {
                    getNotes.add(notes[index])
                }
            }
        }
        return getNotes
    }

    override fun edit(editedNote: Note): Boolean {
        for ((index, note) in notes.withIndex()) {
            if (note.id == editedNote.id) {
                notes[index] = editedNote
                return true
            }
        }
        return false
    }

    override fun delete(noteId: Int): Boolean {
        for ((index, addedNote) in notes.withIndex()) {
            if (addedNote.id == noteId) {
                deletedNotes.add(notes[index])
                notes.remove(notes[index])
                CommentService.deleteByNoteId(noteId)
                return true
            }
        }
        return false
    }

}
