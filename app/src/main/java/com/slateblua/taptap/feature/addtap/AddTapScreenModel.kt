package com.slateblua.taptap.feature.addtap

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.slateblua.taptap.data.TapRepo
import com.slateblua.taptap.data.local.model.Tap
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AddTapScreenModel(private val tapRepo: TapRepo) : ScreenModel {
    private val _tapName = MutableStateFlow("")
    val tapName = _tapName.asStateFlow()

    private val _tapGoal = MutableStateFlow(MIN_TAP_GOAL)
    val tapGoal = _tapGoal.asStateFlow()

    fun setTapName(name: String) {
        _tapName.value = name
    }

    private fun setTapGoal(goal: Int) {
        _tapGoal.value = goal.coerceAtLeast(MIN_TAP_GOAL)
    }

    fun addToGoal() {
        setTapGoal(tapGoal.value + 1)
    }

    fun subtractFromGoal() {
        setTapGoal(tapGoal.value - 1)
    }

    fun addTap(tap: Tap) {
        screenModelScope.launch {
            tapRepo.addTap(tap)
        }
        clear()
    }

    private fun clear() {
        setTapName("")
        setTapGoal(MIN_TAP_GOAL)
    }
}

const val MIN_TAP_GOAL = 1
