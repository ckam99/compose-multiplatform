package product.data.mapper

import product.data.dto.ProductDto
import product.data.dto.RatingDto
import product.domain.models.Product
import product.domain.models.Rating


fun ProductDto.toModel() : Product {
    return Product(
        id = this.id,
        title = this.title,
        price = this.price,
        description = this.description,
        category = this.category,
        image = this.image,
        rating = this.rating.toModel()
    )
} 

fun RatingDto.toModel() : Rating {
    return Rating(
        rate = this.rate,
        count = this.count
    )
}

fun Product.toDto() : ProductDto {
    return ProductDto(
        id = this.id,
        title = this.title,
        price = this.price,
        description = this.description,
        category = this.category,
        image = this.image,
        rating = this.rating.toDto()
    )
}

fun Rating.toDto() : RatingDto {
    return RatingDto(
        rate = this.rate,
        count = this.count
    )
} 