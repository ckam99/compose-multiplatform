package org.example.grocery.features.product.data.datasource

import org.example.grocery.features.product.data.dto.ProductDto


class ProductDataSource(
    private val local: ProductLocalSource,
    private val remote: ProductRemoteSource
) {
    suspend fun getAll(limit: Long = 0, refresh: Boolean = false) : Result<List<ProductDto>>{
        if (refresh){
            return loadRemoveData()
        }
        return local.getAll(limit)
    }

    suspend fun findById(id: Long) : Result<ProductDto?>{
       return local.findById(id = id)
    }

    private suspend fun loadRemoveData() : Result<List<ProductDto>> {
        return try {
            val items = remote.GetAll().getOrDefault(listOf())
            local.removeAll()
            items.forEach {
                local.insert(it)
            }
            Result.success(items)
        } catch (ex: Exception){
            ex.printStackTrace()
            Result.failure(ex)
        }
    }

}