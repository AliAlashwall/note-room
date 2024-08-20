package com.example.testroomdatabase.ui.screens.notes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.testroomdatabase.ui.theme.TestRoomDatabaseTheme

@Composable
fun NotesScreen(viewModel: NoteViewModel) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Notes Screen",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth()
        )

        Button(onClick = {}) {
            Text(text = "Add Note")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun NotesPreview() {
    TestRoomDatabaseTheme {
        val viewModel = NoteViewModel()
        NotesScreen(viewModel)
    }
}