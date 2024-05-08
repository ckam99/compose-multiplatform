package product.domain.repository

import product.domain.models.Product


interface ProductRepository {
    
    suspend fun GetAll(limit: Int) : Result<List<Product>>
    suspend fun FindById(id: Int) : Result<Product>
}