package com.slateblua.taptap.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TapAlertMenu(
    tapDef: Int,
    onDelete: (Int) -> Unit,
    onClose: () -> Unit,
) {
    AlertDialog(onDismissRequest = { onClose() }) {
        Button(
            onClick = {
                onDelete(tapDef)
                onClose()
            },
        ) {
            Text(text = "Delete")
        }
    }
}
