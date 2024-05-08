package org.example.grocery.core.database

import app.cash.sqldelight.db.SqlDriver


const val groceryDatabaseName = "SQLDelightGroceryDatabase.db"

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect class DatabaseDriverFactory {
    fun createDriver(): SqlDriver
} 