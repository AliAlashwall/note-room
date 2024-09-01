package com.example.noteapp.ui.screens.notes

import android.content.res.Configuration
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberModalBottomSheetState
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.noteapp.R
import com.example.noteapp.data.database.Note
import com.example.noteapp.ui.component.NotesTopBar
import com.example.noteapp.ui.screens.notes.component.MyModalBottomSheet
import com.example.noteapp.ui.screens.notes.component.NoteItem
import com.example.noteapp.ui.screens.notes.component.model.NoteUiState
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
        onInsert = { title, content ->
            viewModel.insertNote(title, content)
        },
        onDelete = { viewModel.deleteNote(it) },
        updateNoteTitle = { viewModel.updateNoteTitle(it) },
        updateNoteContent = { viewModel.updateNoteContent(it) })
}

@ExperimentalMaterial3Api
@Composable
fun NoteScreenContainer(
    notesUiState: NoteUiState,
    notesList: List<Note>,
    onInsert: (String, String) -> Unit,
    onDelete: (Note) -> Unit,
    updateNoteTitle: (String) -> Unit,
    updateNoteContent: (String) -> Unit,
) {
    val sheetState = rememberModalBottomSheetState()
    var showState by remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    showState = true
                    updateNoteTitle("")
                    updateNoteContent("")
                },
                containerColor = Color(0xFFDCE4E7),
            ) {
                Text(
                    text = "+",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.Black,
                    fontWeight = FontWeight.Normal
                )
            }
        },
        topBar = {NotesTopBar(title = stringResource(R.string.notes)) }
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
                            date = note.date.toString().substringBefore("GMT")
                        )
                    }
                }
            }

            if (showState) {
                MyModalBottomSheet(
                    sheetState = sheetState,
                    onDismissRequest = { showState = false }) {
                    Column {

                        TextField(value = notesUiState.noteTitle,
                            onValueChange = { updateNoteTitle(it) },
                            maxLines = 1,
                            modifier = Modifier
                                .padding(24.dp)
                                .fillMaxWidth(),
                            placeholder = { Text(stringResource(R.string.enter_note_title)) }
                        )

                        TextField(
                            value = notesUiState.noteContent,
                            onValueChange = { updateNoteContent(it) },
                            modifier = Modifier
                                .padding(24.dp)
                                .fillMaxWidth(),
                            placeholder = { Text(stringResource(R.string.enter_note_content)) })

                        Button(
                            modifier = Modifier.padding(24.dp),
                            onClick = {
                                onInsert(notesUiState.noteTitle, notesUiState.noteContent)
                                showState = false
                            }) {
                            Text(text = stringResource(R.string.add_note))
                        }
                    }

                }
            }
        }
    }
}


@Composable
fun EmptyNotes(
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.weight(1f))

        Image(
            modifier = Modifier.size(150.dp),
            painter = painterResource(id = R.drawable.ic_notes),
            contentDescription = stringResource(R.string.sticky_notes_image)
        )

        Spacer(modifier = Modifier.height(26.dp))

        Text(
            text = "No notes yet\n" + "Tab + to add a new note",
            style = MaterialTheme.typography.titleLarge,
            color = Color.Gray,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.weight(1f))


    }
}
/*
*   Column(
        modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val textState = remember { mutableStateOf("") }

        TextField(value = textState.value,
            onValueChange = { textState.value = it },
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth(),
            placeholder = { Text("Enter note content") })

        Button(modifier = Modifier.padding(24.dp), onClick = { onInsert(textState.value) }) {
            Text(text = "Add Note")
        }
        LazyColumn {
            items(notesUiState) { note ->
                NoteItem(
                    onClickItem = { onDelete(note) },
                    content = note.content,
                    date = note.date.toString().substringBefore("GMT")
                )
            }
        }
    }*/

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
            emptyList(),
            onInsert = { _, _ -> },
            {},
            {},
            {}
        )
    }
}