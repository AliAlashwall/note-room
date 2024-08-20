package com.example.testroomdatabase

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.testroomdatabase.data.database.NoteDatabase
import com.example.testroomdatabase.ui.screens.notes.NoteViewModel
import com.example.testroomdatabase.ui.screens.notes.NotesScreen
import com.example.testroomdatabase.ui.theme.TestRoomDatabaseTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        NoteDatabase.getInstance(applicationContext)
        setContent {
            TestRoomDatabaseTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    val viewModel = NoteViewModel()
                    NotesScreen(viewModel = viewModel)
                }
            }
        }
    }
}
