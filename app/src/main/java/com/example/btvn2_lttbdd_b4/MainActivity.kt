/*
package com.example.btvn2_lttbdd_b4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.btvn2_lttbdd_b4.Screens.Page1
import com.example.btvn2_lttbdd_b4.ui.theme.BTVN2_LTTBDD_B4Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BTVN2_LTTBDD_B4Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)  // 👈 Sử dụng innerPadding ở đây
                            .fillMaxSize()
                    ) {
                        Page1()
                    }
                }
            }
        }
    }
}

@Composable
fun Demo2(){

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BTVN2_LTTBDD_B4Theme {
        Demo2()
    }
}*/
package com.example.btvn2_lttbdd_b4


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BookManagementScreen()
        }
    }
}

data class Book(val id: Int, val title: String)
data class Student(val name: String, val borrowedBooks: MutableList<Book>)

@Composable
fun BookManagementScreen() {
    // Sample data
    val allBooks = listOf(
        Book(1, "Sách 01"),
        Book(2, "Sách 02")
    )

    // Map to store borrowed books for each student
    val studentBorrowedBooksMap = remember { mutableStateMapOf<String, MutableList<Book>>() }
    var studentName by remember { mutableStateOf("Nguyen Van A") }
    var tempName by remember { mutableStateOf(studentName) }
    var showBooks by remember { mutableStateOf(false) }

    // State for checkbox selections
    val checkedBooks = remember { mutableStateMapOf<Int, Boolean>() }

    // Load or initialize borrowed books for the current student
    val currentBorrowedBooks = studentBorrowedBooksMap.getOrPut(studentName) { mutableListOf() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Hệ thống Quản lý Thư viện",
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Student name input and "Thay đổi" button
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Sinh viên",
                modifier = Modifier.padding(end = 8.dp)
            )
            OutlinedTextField(
                value = tempName,
                onValueChange = { tempName = it },
                modifier = Modifier.weight(1f)
            )
            Button(
                onClick = {
                    studentName = tempName
                    showBooks = true
                    // Load existing borrowed books for the new student
                    checkedBooks.clear()
                    val borrowedBooks = studentBorrowedBooksMap[studentName] ?: mutableListOf()
                    allBooks.forEach { book ->
                        checkedBooks[book.id] = borrowedBooks.contains(book)
                    }
                },
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Text("Thay đổi")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Book list section
        if (showBooks) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "Danh sách sách",
                        fontSize = 18.sp,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    if (currentBorrowedBooks.isEmpty()) {
                        Text(
                            text = "Bạn chưa mượn quyển sách nào",
                            color = Color.Red,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                        )
                    }

                    LazyColumn {
                        items(allBooks) { book ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Checkbox(
                                    checked = checkedBooks[book.id] ?: false,
                                    onCheckedChange = { isChecked ->
                                        checkedBooks[book.id] = isChecked
                                    },
                                    colors = CheckboxDefaults.colors(
                                        checkedColor = Color.Green
                                    )
                                )
                                Text(
                                    text = book.title,
                                    modifier = Modifier.padding(start = 8.dp)
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // "Thêm" button to save changes
            Button(
                onClick = {
                    currentBorrowedBooks.clear()
                    checkedBooks.forEach { (bookId, isChecked) ->
                        if (isChecked) {
                            val book = allBooks.find { it.id == bookId }
                            book?.let { currentBorrowedBooks.add(it) }
                        }
                    }
                    studentBorrowedBooksMap[studentName] = currentBorrowedBooks
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Thêm")
            }
        }
    }
}