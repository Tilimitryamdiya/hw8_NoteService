fun main() {
    NotesService.add(Note(title = "one", text = "text1"))
    NotesService.add(Note(title = "two", text = "text2"))
    NotesService.add(Note(title = "three", text = "text3"))
    NotesService.add(Note(title = "four", text = "text4"))
    NotesService.add(Note(title = "five", text = "text5"))

    NotesService.printNotes()

    println(NotesService.getList(listOf(2, 3)))

    NotesService.edit(Note(id = 5, title = "New five", text = "New text5"))

    println(NotesService.getById(5))

    NotesService.delete(4)
    NotesService.printNotes()

    println()

    CommentService.add(Comment(message = "Comment1", noteId = 1))
    CommentService.add(Comment(message = "Comment2", noteId = 2))
    CommentService.add(Comment(message = "Comment3", noteId = 3))

    CommentService.printComments()
    println(NotesService.getList(listOf(1, 2, 3)))
    println()

    CommentService.edit(Comment(noteId = 3, id = 3, message = "New comment 3"))
    println(CommentService.getComments(3))

    CommentService.delete(1)
    CommentService.printComments()

    println(CommentService.restoreComment(1))
    CommentService.printComments()
    println()

    println(NotesService.getList(listOf(1, 2, 3)))

}