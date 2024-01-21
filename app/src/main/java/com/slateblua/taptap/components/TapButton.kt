package com.slateblua.taptap.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TapButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String,
) {
    Button(
        onClick = { onClick() },
        modifier =
            modifier
                .padding(all = 16.dp),
        shape = RoundedCornerShape(16.dp),
    ) {
        Text(text = text)
    }
}
