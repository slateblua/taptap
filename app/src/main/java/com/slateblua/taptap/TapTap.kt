package com.slateblua.taptap

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import com.slateblua.taptap.feature.home.HomeScreen
import com.slateblua.taptap.theme.TapTheme

class TapTap : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TapTheme {
                Navigator(HomeScreen()) { nav ->
                    SlideTransition(nav)
                }
            }
        }
    }
}
