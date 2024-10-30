package com.example.noteapp.ui.screens.notes.component

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.noteapp.R
import com.example.noteapp.ui.theme.NoteAppTheme

@Composable
fun NoteItem(
    onClickItem: () -> Unit,
    title: String,
    content: String,
    date: String,
    color: Color
) {
    val showMore = remember { mutableStateOf(false) }
    Card(
        colors = CardDefaults.cardColors(containerColor = color),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.elevatedCardElevation(4.dp),
        onClick = { showMore.value = true },
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
                    maxLines = if (showMore.value) 20 else 3,
                    overflow = TextOverflow.Ellipsis,
                )

                Image(
                    painter = painterResource(id = R.drawable.ic_delete),
                    modifier = Modifier
                        .padding(8.dp)
                        .padding(start = 16.dp)
                        .size(24.dp)
                        .clickable { onClickItem() },
                    contentDescription = stringResource(R.string.delete_note),
                    colorFilter = ColorFilter.tint(Color.LightGray)
                )

            }
            Text(
                text = date,
                textAlign = TextAlign.End,
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier
                    .fillMaxWidth(),
                color = Color.LightGray,
            )
        }
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
private fun NoteItemPreview() {
    NoteAppTheme {
        NoteItem(
            title = "Note Title",
            content = " This is a note This is a note This is a note This is a note",
            date = "2021-09-01",
            color = Color(0xFF44474F),
            onClickItem = {}
        )
    }
}