package com.example.noteapp.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.noteapp.ui.theme.NoteAppTheme

@Composable
fun NotesTopBar(title: String) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(24.dp)) {
        Text(
            text = title,
            fontSize = 28.sp,
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun NotesTopBarPrev() {
    NoteAppTheme {
        NotesTopBar(title = "Notes")
    }
}