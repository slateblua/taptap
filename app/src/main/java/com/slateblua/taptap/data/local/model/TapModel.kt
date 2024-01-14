package com.slateblua.taptap.data.local.model

data class Tap(
    val def: Int = 0,
    val name: String,
    val goal: Int,
    val current: Int = 0,
) {
    val completed: Boolean
        get() = current == goal
}