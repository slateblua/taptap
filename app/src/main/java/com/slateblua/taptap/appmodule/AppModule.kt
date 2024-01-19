package com.slateblua.taptap.appmodule

import com.slateblua.db.TapDatabase
import com.slateblua.taptap.data.TapRepo
import com.slateblua.taptap.data.db.AbstractSqlFactory
import com.slateblua.taptap.data.db.SqlFactory
import com.slateblua.taptap.data.db.currentAdapter
import com.slateblua.taptap.data.db.defAdapter
import com.slateblua.taptap.data.db.goalAdapter
import com.slateblua.taptap.data.local.TapRepoImpl
import com.slateblua.taptap.feature.addtap.AddTapScreenModel
import com.slateblua.taptap.feature.home.HomeScreenModel
import comslatebluadb.TapEnt
import org.koin.dsl.module

val commonModule =
    module {

        single<TapRepo> { TapRepoImpl(get()) }

        single { HomeScreenModel(get()) }

        single { AddTapScreenModel(get()) }

        single<AbstractSqlFactory> { SqlFactory(get()) }

        single {
            TapDatabase(
                get<AbstractSqlFactory>().createDriver(),
                TapEnt.Adapter(
                    defAdapter = defAdapter,
                    goalAdapter = goalAdapter,
                    currentAdapter = currentAdapter,
                ),
            )
        }
    }
