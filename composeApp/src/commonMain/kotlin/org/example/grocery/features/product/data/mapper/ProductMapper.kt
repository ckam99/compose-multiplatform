package org.example.grocery.features.product.data.mapper

import org.example.grocery.cache.ProductEntity
import org.example.grocery.features.product.data.dto.ProductDto
import org.example.grocery.features.product.data.dto.RatingDto
import org.example.grocery.features.product.domain.models.Product
import org.example.grocery.features.product.domain.models.Rating


fun ProductEntity.toDto() : ProductDto {
    return ProductDto(
        id = this.id,
        title = this.title,
        price = this.price,
        description = this.description,
        category = this.category,
        image = this.image,
        rating = RatingDto(rate = 0.0, count = 0)
    )
}

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