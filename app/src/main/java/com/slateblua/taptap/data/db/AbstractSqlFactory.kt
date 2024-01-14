package com.slateblua.taptap.data.db

import app.cash.sqldelight.db.SqlDriver

abstract class AbstractSqlFactory {
    abstract fun createDriver(): SqlDriver
}