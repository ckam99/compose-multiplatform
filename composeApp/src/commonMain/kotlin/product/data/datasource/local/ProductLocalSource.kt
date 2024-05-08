package product.data.datasource.local

import product.data.datasource.ProductDataSource
import product.data.dto.ProductDto

class ProductLocalSource : ProductDataSource {
    override suspend fun GetAll(limit: Int): Result<List<ProductDto>> {
        TODO("Not yet implemented")
    }

    override suspend fun FindById(id: Int): Result<ProductDto> {
        TODO("Not yet implemented")
    }

}