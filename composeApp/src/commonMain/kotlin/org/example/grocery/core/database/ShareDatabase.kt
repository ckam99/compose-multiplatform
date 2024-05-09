package org.example.grocery.core.database

import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import org.example.grocery.cache.GroceryDatabase

class SharedDatabase(
    private val driverFactory: DatabaseDriverFactory
) {
    private var database: GroceryDatabase? = null
    
    private val mutex = Mutex()
    
    suspend fun<Result: Any> withDatabase(block: suspend (GroceryDatabase) -> Result) = mutex.withLock {
        if (database == null){
            database = createDb(driverFactory)
        }
        return@withLock block(database!!)
    }

    private suspend fun createDb(driverProvider: DatabaseDriverFactory): GroceryDatabase {
       return GroceryDatabase(driver = driverProvider.createDriver())
    }

}