package product.data.datasource

import product.data.dto.ProductDto


interface ProductDataSource {
    suspend fun GetAll(limit: Int) : Result<List<ProductDto>>
    suspend fun FindById(id: Int) : Result<ProductDto>
}