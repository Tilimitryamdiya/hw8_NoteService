abstract class Service<T : Item> {
    private var elements: List<T> = mutableListOf()
    private var curId: Int = 0

    open fun setId(): Int {
        curId++
        return curId
    }

    open fun add(elem: T): Int {
        elem.id = setId()
        elements += elem
        return elem.id
    }

    open fun getById(elemId: Int): T? {
        for ((index, elem) in elements.withIndex()) {
            if (elem.id == elemId) {
                return elements[index]
            }
        }
        return null
    }

    open fun edit(editedElem: T): Boolean {
        for (elem in elements) {
            if (elem.id == editedElem.id) {
                return true
            }
        }
        return false
    }

    open fun delete(elemId: Int): Boolean {
        for (elem in elements) {
            if (elem.id == elemId) {
                return true
            }
        }
        return false
    }
}