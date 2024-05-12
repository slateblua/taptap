package com.slateblua.taptap.feature.addtap

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import com.slateblua.taptap.components.TapButton
import com.slateblua.taptap.components.TapGoalButton
import com.slateblua.taptap.data.local.model.Tap

class AddTapScreen : Screen {
    @Composable
    override fun Content() {
        AddTapScreenContent()
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun AddTapScreenContent(screenModel: AddTapScreenModel = getScreenModel()) {
        val tapName = screenModel.tapName.collectAsState().value
        val tapGoal = screenModel.tapGoal.collectAsState().value

        Scaffold(
            topBar = { TopAppBar(title = { Text(text = "TapTap") }) },
        ) { paddValues ->
            Column(
                modifier =
                    Modifier
                        .padding(paddValues)
                        .fillMaxSize(),
            ) {
                OutlinedTextField(
                    value = tapName,
                    onValueChange = { name -> screenModel.setTapName(name) },
                    modifier =
                        Modifier
                            .align(alignment = Alignment.CenterHorizontally)
                            .fillMaxWidth()
                            .padding(all = 16.dp),
                )
                Row(
                    modifier =
                        Modifier
                            .padding(all = 16.dp)
                            .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    TapGoalButton(
                        onClick = { screenModel.subtractFromGoal() },
                        imageVector = Icons.Default.KeyboardArrowDown,
                    )
                    Text(
                        text = "$tapGoal",
                        modifier =
                            Modifier
                                .align(alignment = Alignment.CenterVertically)
                                .padding(horizontal = 16.dp),
                        textAlign = TextAlign.Center,
                    )
                    TapGoalButton(
                        onClick = { screenModel.addToGoal() },
                        imageVector = Icons.Default.KeyboardArrowUp,
                    )
                }
                TapButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        screenModel.addTap(tap = Tap(name = tapName, goal = tapGoal))
                    },
                    text = "Save Tap",
                )
            }
        }
    }
}
