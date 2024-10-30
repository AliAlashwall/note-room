package com.example.noteapp.ui.screens.notes

import android.content.res.Configuration
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.noteapp.R
import com.example.noteapp.data.database.Note
import com.example.noteapp.ui.component.NotesTopBar
import com.example.noteapp.ui.screens.notes.component.NoteInsertionModal
import com.example.noteapp.ui.screens.notes.component.NoteItem
import com.example.noteapp.ui.screens.notes.model.NoteUiState
import com.example.noteapp.ui.theme.NoteAppTheme

@ExperimentalMaterial3Api
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NotesScreen(viewModel: NoteViewModel) {
    LaunchedEffect(Unit) {
        viewModel.getNotes()
    }
    val notesList by viewModel.notesUiState.collectAsState()
    val notesUiState = viewModel.noteUiState.collectAsState()

    NoteScreenContainer(
        notesUiState = notesUiState.value,
        notesList = notesList,
        onInsert = { title, content, color ->
            viewModel.insertNote(title, content, color)
        },
        onDelete = { viewModel.deleteNote(it) },
        updateNoteTitle = { viewModel.updateNoteTitle(it) },
        updateNoteContent = { viewModel.updateNoteContent(it) },
        updateNoteColor = { viewModel.updateNoteColor(it) },
        clearNoteHistory = { viewModel.clearNoteHistory() })
}

@ExperimentalMaterial3Api
@Composable
fun NoteScreenContainer(
    notesUiState: NoteUiState,
    notesList: List<Note>,
    onInsert: (String, String, Long) -> Unit,
    onDelete: (Note) -> Unit,
    updateNoteTitle: (String) -> Unit,
    updateNoteContent: (String) -> Unit,
    updateNoteColor: (Long) -> Unit,
    clearNoteHistory: () -> Unit
) {

    var showState by remember { mutableStateOf(false) }
    val colorList = remember {
        listOf(
            0xFFFFC107, // Amber
            0xFF8BC34A, // Light Green
            0xFF03A9F4, // Light Blue
            0xFF673AB7, // Deep Purple
            0xFFFF5722, // Deep Orange
            0xFF795548, // Brown
            0xFF607D8B, // Blue Grey
            0xFF9E9E9E, // Grey
            0xFF3F51B5, // Indigo
            0xFFFFEB3B  // Yellow
        )
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    showState = true
                    clearNoteHistory()
                },
                containerColor = Color(0xFFDCE4E7),
            ) {
                Text(
                    text = "+",
                    style = MaterialTheme.typography.displayMedium,
                    color = Color.Black,
                )
            }
        },
        topBar = { NotesTopBar(title = stringResource(R.string.notes)) }
    ) { paddingValues ->
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (notesList.isEmpty()) {
                EmptyNotes()
            } else {
                LazyColumn {
                    items(notesList) { note ->
                        NoteItem(
                            onClickItem = { onDelete(note) },
                            content = note.content,
                            title = note.title,
                            date = note.date.toString().substringBefore("GMT"),
                            color = Color(note.color)
                        )
                    }
                }
            }

            if (showState) {
                NoteInsertionModal(
                    notesUiState = notesUiState,
                    updateNoteTitle = updateNoteTitle,
                    updateNoteContent = updateNoteContent,
                    updateNoteColor = updateNoteColor,
                    onInsert = onInsert,
                    colorList = colorList,
                    dismissRequest = { showState = false }
                )
            }
        }
    }
}

@ExperimentalMaterial3Api
@RequiresApi(Build.VERSION_CODES.O)
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL,
)
@Composable
private fun NotesPreview() {
    NoteAppTheme {
        NoteScreenContainer(
            NoteUiState(),
            listOf(
                Note(
                    id = 0,
                    title = "Note Title",
                    content = "Note Content",
                    date = java.util.Date(),
                    isImportant = false,
                    color = 0xFF44474F
                )
            ),
            onInsert = { _, _, _ -> },
            onDelete = {},
            updateNoteTitle = {},
            updateNoteContent = {},
            updateNoteColor = {},
            clearNoteHistory = {}
        )
    }
}