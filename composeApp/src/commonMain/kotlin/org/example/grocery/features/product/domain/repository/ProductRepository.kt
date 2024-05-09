package org.example.grocery.features.product.domain.repository

import org.example.grocery.features.product.domain.models.Product


interface ProductRepository {
    suspend fun GetAll(limit: Long = 0, reload: Boolean = false) : Result<List<Product>>
    suspend fun FindById(id: Long) : Result<Product?>
}