package org.example.grocery.features.product.data.datasource

import org.example.grocery.features.product.data.dto.ProductDto


interface ProductDataSource {
    suspend fun GetAll(limit: Int) : Result<List<ProductDto>>
    suspend fun FindById(id: Int) : Result<ProductDto>
}