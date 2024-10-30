package com.example.noteapp.ui.screens.notes.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.noteapp.R
import com.example.noteapp.ui.screens.notes.model.NoteUiState
import com.example.noteapp.ui.theme.NoteAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteInsertionModal(
    notesUiState: NoteUiState,
    updateNoteTitle: (String) -> Unit,
    updateNoteContent: (String) -> Unit,
    updateNoteColor: (Long) -> Unit,
    onInsert: (String, String, Long) -> Unit,
    colorList: List<Long>,
    dismissRequest: () -> Unit,

    ) {
    val sheetState = rememberModalBottomSheetState()
    MyModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = { dismissRequest() }) {
        Column {
            TextField(
                value = notesUiState.noteTitle,
                onValueChange = { updateNoteTitle(it) },
                maxLines = 1,
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth(),
                placeholder = { Text(stringResource(R.string.enter_note_title)) },
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    unfocusedPlaceholderColor = Color.Gray,
                    focusedPlaceholderColor = Color.Gray,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.Gray,
                )
            )

            TextField(
                value = notesUiState.noteContent,
                onValueChange = { updateNoteContent(it) },
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth(),
                placeholder = { Text(stringResource(R.string.enter_note_content)) },
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    unfocusedPlaceholderColor = Color.Gray,
                    focusedPlaceholderColor = Color.Gray,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.Gray,
                )
            )

            LazyRow(
                modifier = Modifier
                    .padding(horizontal = 18.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                items(colorList) { color ->
                    NoteColorItem(
                        itemColor = Color(color),
                        onClick = { updateNoteColor(color) }
                    )
                    if (color != colorList.last()) {
                        Spacer(modifier = Modifier.size(16.dp))
                    }
                }
            }

            Button(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 22.dp)
                    .fillMaxWidth(),
                onClick = {
                    onInsert(
                        notesUiState.noteTitle,
                        notesUiState.noteContent,
                        notesUiState.noteColor
                    )
                    dismissRequest()
                }) {
                Text(
                    text = stringResource(R.string.add_note),
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}


@Preview
@Composable
private fun NoteInsertionModalPreview() {
    NoteAppTheme {
        NoteInsertionModal(
            notesUiState = NoteUiState(),
            updateNoteTitle = {},
            updateNoteContent = {},
            updateNoteColor = {},
            onInsert = { _, _, _ -> },
            colorList = listOf(),
            dismissRequest = {}
        )
    }
}