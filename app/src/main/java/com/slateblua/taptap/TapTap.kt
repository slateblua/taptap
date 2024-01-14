package com.slateblua.taptap

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import cafe.adriel.voyager.navigator.Navigator
import com.slateblua.taptap.theme.TapTheme
import com.slateblua.taptap.feature.home.HomeScreen

class TapTap : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TapTheme {
                Navigator(HomeScreen())
            }
        }
    }
}

