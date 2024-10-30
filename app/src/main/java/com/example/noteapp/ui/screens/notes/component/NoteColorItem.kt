package com.example.noteapp.ui.screens.notes.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun NoteColorItem(itemColor: Color, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(50.dp)
            .shadow(shape = RoundedCornerShape(25.dp), elevation = 1.dp)
            .background(color = itemColor)
            .clickable { onClick() },
    )
}