package org.example.grocery.features.product.domain.repository

import org.example.grocery.features.product.domain.models.Product


interface ProductRepository {
    
    suspend fun GetAll(limit: Int) : Result<List<Product>>
    suspend fun FindById(id: Int) : Result<Product>
}