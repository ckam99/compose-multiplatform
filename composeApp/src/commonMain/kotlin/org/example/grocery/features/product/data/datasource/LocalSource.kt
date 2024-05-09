package org.example.grocery.features.product.data.datasource

import org.example.grocery.core.database.SharedDatabase
import org.example.grocery.features.product.data.dto.ProductDto
import org.example.grocery.features.product.data.mapper.toDto

class ProductLocalSource(
    private val database: SharedDatabase,
)  {
     suspend fun getAll(limit: Long): Result<List<ProductDto>> {
        return try {
            val items = database.withDatabase {
                it.groceryDatabaseQueries.getListProducts(limit).executeAsList().map { it.toDto() }
            }
            Result.success(items)
        } catch (ex: Exception){
            ex.printStackTrace()
            Result.failure(ex)
        }
     }

     suspend fun findById(id: Long): Result<ProductDto?> {
        return try {
            val item = database.withDatabase { it.groceryDatabaseQueries.getProductById(id).executeAsOne().toDto() }
            Result.success(item)
        } catch (ex: Exception){
            ex.printStackTrace()
            Result.failure(ex)
        }
     }

     suspend fun insert(product: ProductDto): Result<Boolean> {
         return try {
            Result.success(database.withDatabase {
                it.groceryDatabaseQueries.insertProduct(
                    id = product.id,
                    title = product.title,
                    category = product.category,
                    description = product.description,
                    price = product.price,
                    image = product.image
                )
                true
            })
        } catch (ex: Exception){
            ex.printStackTrace()
            Result.failure(ex)
        }
     }


     suspend fun removeAll(): Result<Boolean> {
       return try {
           Result.success(database.withDatabase {db ->
               db.groceryDatabaseQueries.removeAllProducts()
               true
            })
       }catch (ex: Exception){
           ex.printStackTrace()
           Result.failure(ex)
       }
     }

}