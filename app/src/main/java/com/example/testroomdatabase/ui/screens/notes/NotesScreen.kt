package com.example.testroomdatabase.ui.screens.notes

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.testroomdatabase.ui.theme.TestRoomDatabaseTheme

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NotesScreen(viewModel: NoteViewModel) {
    LaunchedEffect(Unit) {
        viewModel.getNotes()
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val notesUiState by viewModel.notesUiState.collectAsState()
        val textState = remember { mutableStateOf("") }


        TextField(
            value = textState.value,
            onValueChange = { textState.value = it },
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth(),
            placeholder = { Text("Enter note content") }
        )

        Button(
            modifier = Modifier.padding(24.dp),
            onClick = { viewModel.insertNote(textState.value) }) {
            Text(text = "Add Note")
        }
        LazyColumn {
            items(notesUiState) { note ->
                Card(
                    colors = CardDefaults.cardColors(Color.LightGray),
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    onClick = { viewModel.deleteNote(note) }
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = note.content,
                            textAlign = TextAlign.Start,
                            modifier = Modifier
                                .padding(16.dp)
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            text = note.date,
                            textAlign = TextAlign.End,
                            modifier = Modifier
                                .padding(16.dp)
                        )
                    }
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
private fun NotesPreview() {
    TestRoomDatabaseTheme {
        val viewModel = NoteViewModel()
        NotesScreen(viewModel)
    }
}