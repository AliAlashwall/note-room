package com.example.noteapp

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.noteapp.data.database.NoteDatabase
import com.example.noteapp.ui.screens.notes.NoteViewModel
import com.example.noteapp.ui.screens.notes.NotesScreen
import com.example.noteapp.ui.theme.NoteAppTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        NoteDatabase.getInstance(applicationContext)
        setContent {
            NoteAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    val viewModel = NoteViewModel()
                    NotesScreen(viewModel = viewModel)
                }
            }
        }
    }
}
