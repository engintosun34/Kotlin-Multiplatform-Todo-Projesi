package org.example.project
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


class MainActivity : AppCompatActivity() {
    private val todoRepository = ToDoRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //onCreate metodu: Uygulama oluşturulduğunda çalışan ve temel yapılandırmayı gerçekleştiren metottur.
        // Burada todoRepository'e örnek veriler eklenir ve TodoApp adlı Composable fonksiyon çağrılır.
        todoRepository.addToDoItem("Görev 1", "Görev Tanımı 1")
        todoRepository.addToDoItem("Görev 2", "Görev Tanımı 2")
        setContent {  // setContent() metodu, içeriği ekrana yerleştirir.
            TodoApp(todoRepository) //TodoApp Composable'ı çağrılır ve içeriği ekrana yerleştirilir.
        }
    }

    @Composable
    fun TodoApp(todoRepository: ToDoRepository) {
        MaterialTheme {
            val todoList = remember { todoRepository.getToDoList() } //remember işlevi, bu değerin değiştiğinde veya yeniden oluşturulduğunda değişmemesini sağlar.
            Surface(modifier = Modifier.fillMaxSize()) {
                TodoList(todoList, todoRepository)
            }
        }
    }

    @Composable
    fun TodoList(todoList: List<ToDoItem>, todoRepository: ToDoRepository) {
        var currentList by remember { mutableStateOf(todoList) } // Mutable state

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(currentList) { todo ->
                TodoItem(todo, todoRepository) {
                    currentList = currentList.filter { it.id != todo.id } // Seçilen ToDoItem'ı sil.
                }
            }
        }
    }

    @Composable
    fun TodoItem(todo: ToDoItem, todoRepository: ToDoRepository, onRemove: () -> Unit) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Column(modifier = Modifier.padding(8.dp)) {
                Text(text = todo.title, style = MaterialTheme.typography.h6)
                Text(text = todo.description, style = MaterialTheme.typography.body1)
                Button(onClick = {
                    todoRepository.removeToDoItem(todo.id)
                    onRemove() // OnRemove callback'i çağır
                }) {
                    Text("Sil")
                }
            }
        }
    }
}