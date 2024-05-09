package org.example.grocery.core.database

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import org.example.grocery.cache.GroceryDatabase
import org.example.grocery.core.AndroidApp


@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(
            schema = GroceryDatabase.Schema,
            context =  AndroidApp.INSTANCE,
            name = groceryDatabaseName
        )
    }
}