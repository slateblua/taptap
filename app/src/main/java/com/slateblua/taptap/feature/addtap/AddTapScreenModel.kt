package com.slateblua.taptap.feature.addtap

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.slateblua.taptap.data.TapRepo
import com.slateblua.taptap.data.local.model.Tap
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class AddTapScreenModel(private val tapRepo: TapRepo) : ScreenModel {

    private val _tapName = MutableStateFlow("")
    val tapName = _tapName

    fun setTapName(name: String) {
        _tapName.value = name
    }

    private val _tapGoal = MutableStateFlow(0)
    val tapGoal = _tapGoal

    private fun setTapGoal(goal: Int) {
        _tapGoal.value = goal.coerceAtLeast(0)
    }

    fun addTap(tap: Tap) {
        screenModelScope.launch {
            tapRepo.addTap(tap)
        }
        clear()
    }

    fun addToGoal() {
        setTapGoal(tapGoal.value + 1)
    }

    fun subtractFromGoal() {
        setTapGoal(tapGoal.value - 1)
    }

    private fun clear() {
        setTapName("")
        setTapGoal(0)
    }

}