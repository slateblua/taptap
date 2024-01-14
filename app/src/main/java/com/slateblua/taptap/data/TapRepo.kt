package com.slateblua.taptap.data

import com.slateblua.taptap.data.local.model.Tap
import kotlinx.coroutines.flow.Flow

interface TapRepo {

    fun getAllTaps(): Flow<List<Tap>>

    suspend fun deleteTapByDef(def: Int)

    suspend fun addTap(tap: Tap)

    suspend fun updateTap(tap: Tap)

    suspend fun updateTapCurrent(def: Int)

}