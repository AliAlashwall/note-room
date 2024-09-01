package com.example.noteapp.ui.screens.notes.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.noteapp.R
import com.example.noteapp.ui.theme.NoteAppTheme

@Composable
fun NoteItem(
    onClickItem: () -> Unit,
    title: String,
    content: String,
    date: String
) {
    Card(
//        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.elevatedCardElevation(4.dp),
//        onClick = onClickItem,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.Start,
        ) {
            Text(
                text = title,
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .fillMaxWidth(),
            )


            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = content,
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .weight(1f),
                )

                Box(
                    modifier = Modifier.padding(start = 16.dp)
                        .shadow(shape = MaterialTheme.shapes.large, elevation = 1.dp)
                        .background(Color.White.copy(alpha = 0.8f))
                        .clickable { onClickItem() }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_delete),
                        modifier = Modifier
                            .padding(8.dp)
                            .size(24.dp),
                        contentDescription = stringResource(R.string.delete_note),
                    )

                }
            }
            Text(
                text = date,
                textAlign = TextAlign.End,
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier
                    .fillMaxWidth(),
                color = Color.Gray,
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun NoteItemPreview() {
    NoteAppTheme {
        NoteItem(
            title = "Note Title",
            onClickItem = {},
            content = " This is a note This is a note This is a note This is a note",
            date = "2021-09-01"
        )
    }
}