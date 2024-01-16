package com.slateblua.taptap.feature.addtap

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import com.slateblua.taptap.data.local.model.Tap
import org.koin.androidx.compose.KoinAndroidContext
import org.koin.core.annotation.KoinExperimentalAPI

class AddTapScreen : Screen {
    @OptIn(KoinExperimentalAPI::class)
    @Composable
    override fun Content() {
        KoinAndroidContext {
            AddTapScreenContent()
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun AddTapScreenContent(
        screenModel: AddTapScreenModel = getScreenModel()
    ) {

        val tapName = screenModel.tapName.collectAsState().value
        val tapGoal = screenModel.tapGoal.collectAsState().value

        Scaffold(
            topBar = { TopAppBar(title = { Text(text = "TapTap") }) }
        ) { paddValues ->
            Column(
                modifier = Modifier
                    .padding(paddValues)
                    .fillMaxSize()
            ) {
                OutlinedTextField(
                    value = tapName,
                    onValueChange = { name -> screenModel.setTapName(name) },
                    modifier = Modifier
                        .align(alignment = Alignment.CenterHorizontally)
                        .fillMaxWidth()
                        .padding(all = 16.dp),
                )
                Row(
                    modifier = Modifier
                        .padding(all = 16.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    IconButton(
                        onClick = { screenModel.subtractFromGoal() },
                        modifier = Modifier
                            .clip(shape = CircleShape)
                            .background(color = MaterialTheme.colorScheme.primaryContainer)
                    ) {
                        Text(text = "-")
                    }
                    Text(
                        text = "$tapGoal",
                        modifier = Modifier
                            .align(alignment = Alignment.CenterVertically)
                            .padding(horizontal = 16.dp),
                        textAlign = TextAlign.Center
                    )
                    IconButton(
                        onClick = { screenModel.addToGoal() },
                        modifier = Modifier
                            .clip(shape = CircleShape)
                            .background(color = MaterialTheme.colorScheme.primaryContainer)
                    ) {
                        Text(text = "+")
                    }
                }
                Button(
                    onClick = { screenModel.addTap(tap = Tap(name = tapName, goal = tapGoal)) },
                    modifier = Modifier
                        .padding(all = 16.dp)
                        .fillMaxWidth()
                ) {
                    Text(text = "Save Tap")
                }
            }
        }
    }
}