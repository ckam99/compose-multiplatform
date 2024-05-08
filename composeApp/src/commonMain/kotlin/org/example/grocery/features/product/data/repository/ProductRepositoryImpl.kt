package org.example.grocery.features.product.data.repository

import org.example.grocery.features.product.data.datasource.ProductDataSource
import org.example.grocery.features.product.data.mapper.toModel
import org.example.grocery.features.product.domain.models.Product
import org.example.grocery.features.product.domain.repository.ProductRepository


class ProductRepositoryImpl(
    val dataSource: ProductDataSource
) : ProductRepository {
    override suspend fun GetAll(limit: Int): Result<List<Product>> {
       return dataSource.runCatching {
           this.GetAll(limit).getOrThrow().map { it.toModel() }
       }
    }

    override suspend fun FindById(id: Int): Result<Product> {
        return dataSource.runCatching {
            this.FindById(id).getOrThrow().toModel() 
        }
    }

}
