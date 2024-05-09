package org.example.grocery.features.product.data.repository

import org.example.grocery.features.product.data.datasource.ProductDataSource
import org.example.grocery.features.product.data.mapper.toModel
import org.example.grocery.features.product.domain.models.Product
import org.example.grocery.features.product.domain.repository.ProductRepository


class ProductRepositoryImpl(
  private val dataSource: ProductDataSource
) : ProductRepository {
    override suspend fun GetAll(limit: Long, reload: Boolean): Result<List<Product>> {
       return dataSource.runCatching {
           this.getAll(limit, reload).getOrThrow().map { it.toModel() }
       }
    }

    override suspend fun FindById(id: Long): Result<Product?> {
        return dataSource.runCatching {
            this.findById(id).getOrThrow()?.toModel()
        }
    }

}
