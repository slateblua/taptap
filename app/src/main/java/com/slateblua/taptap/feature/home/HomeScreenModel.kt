package com.slateblua.taptap.feature.home

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.slateblua.taptap.data.TapRepo
import com.slateblua.taptap.data.local.model.Tap
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeScreenModel(private val tapRepo: TapRepo) : ScreenModel {

    val tapsState = tapRepo.getAllTaps().map {
        taps -> TapsState.Success(
            taps = taps
        )
    }.stateIn(
        scope = screenModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = TapsState.Load,
    )

    fun deleteTap(def: Int) {
        screenModelScope.launch {
            tapRepo.deleteTapByDef(def)
        }
    }

    fun updateTapCurrent(def: Int) {
        screenModelScope.launch {
            tapRepo.updateTapCurrent(def)
        }
    }
}

sealed class TapsState {

    data object Load : TapsState()

    data class Success(
        val taps: List<Tap>,
    ) : TapsState()

}