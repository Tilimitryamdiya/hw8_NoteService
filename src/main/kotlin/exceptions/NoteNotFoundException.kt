package exceptions

class NoteNotFoundException (message: String = "180 Note not found"): RuntimeException(message)