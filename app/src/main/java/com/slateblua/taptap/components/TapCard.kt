package com.slateblua.taptap.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.slateblua.taptap.data.local.model.Tap
import com.slateblua.taptap.theme.TapTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TapCard(
    tap: Tap,
    onTap: (Tap) -> Unit = { },
    onLongPressed: (Int) -> Unit = { },
) {
    Card(
        modifier =
        Modifier
            .combinedClickable(
                onClick = { onTap(tap) },
                onLongClick = { onLongPressed(tap.def) },
            )
            .height(120.dp)
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
    ) {
        Row(
            modifier =
            Modifier
                .fillMaxSize()
                .padding(all = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = tap.name,
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Normal),
            )
            CircularProgressIndicator(
                progress = tap.current.toFloat() / tap.goal.toFloat(),
                trackColor = MaterialTheme.colorScheme.outline,
            )
            Checkbox(checked = tap.completed, onCheckedChange = { })
        }
    }
}

@Composable
@Preview
fun TapCardPrev() {
    TapTheme {
        TapCard(tap = Tap.fakeTap)
    }
}
