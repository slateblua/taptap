package com.slateblua.taptap.feature.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
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
import com.slateblua.taptap.components.TapAlertMenu
import com.slateblua.taptap.components.TapCard
import com.slateblua.taptap.feature.addtap.AddTapScreen

class HomeScreen : Screen {
    @Composable
    override fun Content() {
        HomeScreenContent()
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun HomeScreenContent(screenModel: HomeScreenModel = getScreenModel()) {
        val tapsState = screenModel.tapsState.collectAsState().value
        val nav = LocalNavigator.currentOrThrow

        val shouldOpen = screenModel.showDropDown.collectAsState().value
        val dropDownDef = screenModel.dropDownDef.collectAsState().value

        Scaffold(
            topBar = { TopAppBar(title = { Text(text = "TapTap") }) },
            floatingActionButton = {
                FloatingActionButton(onClick = { nav.push(AddTapScreen()) }) {
                    Icon(Icons.Default.Add, contentDescription = "Add Tap")
                }
            },
        ) { paddValues ->
            Column(modifier = Modifier.padding(paddValues)) {
                if (shouldOpen) {
                    TapAlertMenu(
                        tapDef = dropDownDef,
                        onDelete = { screenModel.deleteTap(dropDownDef) },
                        onClose = { screenModel.closeDropDown() },
                    )
                }

                when (tapsState) {
                    is TapsState.Load -> {
                        CircularProgressIndicator(
                            modifier =
                                Modifier
                                    .fillMaxSize()
                                    .align(Alignment.CenterHorizontally),
                        )
                    }

                    is TapsState.Success -> {
                        LazyColumn {
                            items(
                                tapsState.taps,
                                key = { tap -> tap.def },
                            ) { tap ->
                                TapCard(
                                    tap = tap,
                                    onTap = { screenModel.updateTapCurrent(tap.def) },
                                    onLongPressed = { def -> screenModel.openDropDown(def) },
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
