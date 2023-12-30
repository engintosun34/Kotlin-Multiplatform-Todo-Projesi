package org.example.project
import java.awt.BorderLayout
import javax.swing.*

class DesktopApp(private val todoRepository: ToDoRepository) {
    private val frame: JFrame = JFrame("ToDo List")
    private val todoListModel: DefaultListModel<String> = DefaultListModel()

    fun init() {
        todoRepository.addToDoItem("Görev 1", "Görev Tanımı 1")
        todoRepository.addToDoItem("Görev 2", "Görev Tanımı 2")
        // ToDoList'teki öğeler alınır ve bir JList'e eklenir
        val todoList = todoRepository.getToDoList()
        for (todo in todoList) {
            todoListModel.addElement("${todo.title}: ${todo.description}")
        }

        val todoJList = JList(todoListModel)
        val scrollPane = JScrollPane(todoJList)

        val removeButton = JButton("Sil")
        removeButton.addActionListener {
            // Seçili öğenin indeksi
            val selectedIdx = todoJList.selectedIndex
            if (selectedIdx >= 0) {
                val selectedId = todoList[selectedIdx].id
                // Seçilen öğenin kimliği alınır ve ToDoRepository üzerinden silinir
                todoRepository.removeToDoItem(selectedId)
                todoListModel.removeElementAt(selectedIdx)
            }
        }

        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.add(scrollPane, BorderLayout.CENTER)
        frame.add(removeButton, BorderLayout.SOUTH)
        frame.pack()
        frame.isVisible = true
    }
}

fun main() {
    val todoRepository = ToDoRepository()
    val desktopApp = DesktopApp(todoRepository)
    SwingUtilities.invokeLater { desktopApp.init() }
}
