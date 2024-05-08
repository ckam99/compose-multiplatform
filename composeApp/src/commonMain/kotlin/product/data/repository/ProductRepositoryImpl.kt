package product.data.repository

import product.data.datasource.ProductDataSource
import product.data.datasource.network.ProductNetworkSource
import product.data.mapper.toModel
import product.domain.models.Product
import product.domain.repository.ProductRepository


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
