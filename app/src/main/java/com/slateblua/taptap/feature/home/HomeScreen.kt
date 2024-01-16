package com.slateblua.taptap.feature.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.slateblua.taptap.components.TapCard
import com.slateblua.taptap.feature.addtap.AddTapScreen
import org.koin.androidx.compose.KoinAndroidContext
import org.koin.core.annotation.KoinExperimentalAPI

class HomeScreen : Screen {

    @OptIn(KoinExperimentalAPI::class)
    @Composable
    override fun Content() {
        KoinAndroidContext {
            HomeScreenContent()
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun HomeScreenContent(
        screenModel: HomeScreenModel = getScreenModel()
    ) {
        val tapsState = screenModel.tapsState.collectAsState().value
        val nav = LocalNavigator.currentOrThrow

        val shouldOpen = screenModel.showDropDown.collectAsState().value
        val dropDownId = screenModel.dropDownId.collectAsState().value

        Scaffold(
            topBar = { TopAppBar(title = { Text(text = "TapTap") })},
            floatingActionButton = {
                FloatingActionButton(onClick = { nav.push(AddTapScreen()) }) {
                    Text(text = "+ new tap")
                }
            }
        ) { paddValues ->
            Column(modifier = Modifier.padding(paddValues)) {

                if (shouldOpen) {
                    TapAlertMenu(tapDef = dropDownId, onDelete = { screenModel.deleteTap(dropDownId) }, onClose = { screenModel.closeDropDown() })
                }

                when (tapsState) {
                    is TapsState.Load -> {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .fillMaxSize()
                                .align(Alignment.CenterHorizontally)
                        )
                    }
                    is TapsState.Success -> {
                        LazyColumn {
                            items(tapsState.taps) { tap ->
                                TapCard(
                                    tap = tap,
                                    onTap = { screenModel.updateTapCurrent(tap.def) },
                                    onLongPressed = { def -> screenModel.openDropDown(def) }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun TapAlertMenu(
        tapDef: Int,
        onDelete: (Int) -> Unit,
        onClose: () -> Unit
    ) {
        AlertDialog(onDismissRequest = { onClose() }) {
            Button(
                onClick = {
                    onDelete(tapDef)
                    onClose()
                }
            ) {
                Text(text = "Delete")
            }
        }
    }
}