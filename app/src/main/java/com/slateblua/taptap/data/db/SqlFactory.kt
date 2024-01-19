package com.slateblua.taptap.data.db

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.slateblua.db.TapDatabase

class SqlFactory(private val context: Context) : AbstractSqlFactory() {
    override fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(TapDatabase.Schema, context, "tap.sq")
    }
}
