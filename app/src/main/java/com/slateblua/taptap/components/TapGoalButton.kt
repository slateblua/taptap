package com.slateblua.taptap.components

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun TapGoalButton(
    onClick: () -> Unit,
    imageVector: ImageVector,
    contentDescription: String? = null,
) {
    IconButton(
        onClick = { onClick() },
        modifier =
            Modifier
                .clip(shape = RoundedCornerShape(16.dp))
                .background(color = MaterialTheme.colorScheme.primaryContainer),
    ) {
        Icon(imageVector = imageVector, contentDescription = contentDescription)
    }
}
