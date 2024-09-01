package com.example.noteapp.ui.screens.notes.component

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.imePadding
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.noteapp.ui.theme.darkOverlay
import com.example.noteapp.ui.theme.lightOverlay

@ExperimentalMaterial3Api
@Composable
fun MyModalBottomSheet(
    sheetState: SheetState,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
        modifier = modifier.imePadding(),
        containerColor = if (isSystemInDarkTheme()) darkOverlay else lightOverlay
    ) {
        content()
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun MyModalBottomSheetPrev() {
    val sheetState = rememberModalBottomSheetState()
    MyModalBottomSheet(
        sheetState =sheetState ,
        onDismissRequest = {},
        content = {}
    )
}