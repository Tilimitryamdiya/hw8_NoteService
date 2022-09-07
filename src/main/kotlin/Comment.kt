import exceptions.NoteNotFoundException

class Comment(
    override var id: Int = 0,
    val noteId: Int,
    val ownerId: Int = 0,
    val replyTo: Int = 0,
    val message: String = "",
    val guid: String = "0"
) : Item(id) {
    override fun toString(): String {
        return "Note Id: $noteId, comment id: $id. Message: $message"
    }
}

object CommentService : Service<Comment>() {
    private val comments = mutableListOf<Comment>()
    private val deletedComments = mutableListOf<Comment>()
    private var cid = 0

    override fun setId(): Int {
        cid++
        return cid
    }


    override fun add(comment: Comment): Int {
        val findNote = NotesService.getById(comment.noteId) ?: throw NoteNotFoundException()
        findNote.comments++
        comment.id = setId()
        comments += comment
        return comment.id
    }

    fun getComments(noteId: Int): List<Comment> {
        val commentsList = mutableListOf<Comment>()
        for ((index, comment) in comments.withIndex()) {
            if (comment.noteId == noteId) {
                commentsList += comments[index]
            }
        }
        return commentsList
    }

    override fun edit(editComment: Comment): Boolean {
        for ((index, comment) in comments.withIndex()) {
            if (comment.id == editComment.id) {
                comments[index] = editComment
                return true
            }
        }
        return false
    }

    override fun delete(delCommentId: Int): Boolean {
        for ((index, comment) in comments.withIndex()) {
            if (comment.id == delCommentId) {
                deletedComments += comments[index]
                comments.remove(comments[index])
                var findNote =
                    NotesService.getById(comment.noteId) ?: throw NoteNotFoundException()
                findNote.comments --
                return true
            }
        }
        return false
    }

    fun deleteByNoteId(noteId: Int): Boolean {
        for ((index, comment) in comments.withIndex()) {
            if (comment.noteId == noteId) {
                deletedComments += comments[index]
                comments.remove(comments[index])
                return true
            }
        }
        return false
    }

    fun restoreComment(commentId: Int): Boolean {
        for ((index, deletedComment) in deletedComments.withIndex()) {
            if (deletedComment.id == commentId) {
                comments += deletedComments[index]
                deletedComments.remove(deletedComments[index])
                return true
            }
        }
        return false
    }

    fun printComments() {
        for (comment in comments) {
            println("ID ${comment.id}:  ${comment.message}")
        }

    }
}