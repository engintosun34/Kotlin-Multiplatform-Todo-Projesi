package org.example.project
class ToDoRepository {
    private val todoList = mutableListOf<ToDoItem>()
    private var currentId = 0

    fun addToDoItem(title: String, description: String): Int {
        // Yeni bir ToDoItem oluşturulurken currentId değeri kullanılarak her bir öğeye benzersiz bir id atanır
        val todoItem = ToDoItem(currentId++, title, description)
        todoList.add(todoItem)
        return todoItem.id
    }

    fun getToDoList(): List<ToDoItem> {
        // listenin kopyası
        return todoList.toList()
    }

    fun removeToDoItem(id: Int) {
        todoList.removeIf { it.id == id }
    }
}
