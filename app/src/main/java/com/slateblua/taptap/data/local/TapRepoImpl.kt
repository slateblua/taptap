package com.slateblua.taptap.data.local

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.slateblua.db.TapDatabase
import com.slateblua.taptap.data.TapRepo
import com.slateblua.taptap.data.local.model.Tap
import com.slateblua.taptap.data.local.model.toTap
import com.slateblua.taptap.data.local.model.toTapEnt
import comslatebluadb.TapEnt
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TapRepoImpl(tapDatabase: TapDatabase) : TapRepo {

    private val dbQuery = tapDatabase.tapQueries

    override fun getAllTaps(): Flow<List<Tap>> {
        return dbQuery
            .getAllTaps()
            .asFlow()
            .mapToList(Dispatchers.Main)
            .map { taps ->
                taps.map { it.toTap() }
            }
    }

    override suspend fun deleteTapByDef(def: Int) {
        dbQuery.deleteTapByDef(def)
    }

    override suspend fun addTap(tap: Tap) {
        tap.toTapEnt().let { tapEnt: TapEnt ->
            dbQuery.addTap(
                name = tapEnt.name,
                goal = tapEnt.goal,
                current = tapEnt.current,
            )
        }
    }

    override suspend fun updateTap(tap: Tap) {
        tap.toTapEnt().let { tapEnt: TapEnt ->
            dbQuery.updateTap(
                name = tapEnt.name,
                goal = tapEnt.goal,
                def = tapEnt.def
            )
        }
    }

    override suspend fun updateTapCurrent(def: Int) {
        dbQuery.updateTapCurrent(def)
    }
}