package com.example.noteapp.ui.screens.notes.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.noteapp.ui.theme.NoteAppTheme

@Composable
fun NoteItem(
    onClickItem: () -> Unit,
    content: String,
    date: String
) {
    Card(
        colors = CardDefaults.cardColors(Color.White),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.elevatedCardElevation(4.dp),
        onClick = onClickItem,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.Start,
        ) {
            Text(
                text = content,
                textAlign = TextAlign.Start,
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .fillMaxWidth(),
            )
            Text(
                text = date,
                textAlign = TextAlign.End,
                fontSize = 14.sp,
                modifier = Modifier
                    .fillMaxWidth(),
                color = Color.Gray,
            )
        }
    }
}

@Preview(showBackground = false, showSystemUi = true)
@Composable
private fun NoteItemPreview() {
    NoteAppTheme {
        NoteItem(
            onClickItem = {},
            content = "This is a note",
            date = "2021-09-01"
        )
    }
}