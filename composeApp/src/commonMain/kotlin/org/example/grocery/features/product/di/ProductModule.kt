package org.example.grocery.features.product.di


import org.example.grocery.core.database.DatabaseDriverFactory
import org.example.grocery.core.database.SharedDatabase
import org.koin.dsl.module
import org.example.grocery.features.product.data.datasource.ProductDataSource
import org.example.grocery.features.product.data.datasource.ProductLocalSource
import org.example.grocery.features.product.data.datasource.ProductRemoteSource
import org.example.grocery.features.product.data.repository.ProductRepositoryImpl
import org.example.grocery.features.product.domain.repository.ProductRepository
import org.example.grocery.features.product.ui.screens.ProductScreenModel

fun productModule(dbDriverFactory: DatabaseDriverFactory) = module {
    single<SharedDatabase>{ SharedDatabase(dbDriverFactory) }
    single<ProductLocalSource> {ProductLocalSource(database = get())}
    single<ProductRemoteSource> {ProductRemoteSource()}
    single<ProductDataSource> { ProductDataSource(local = get(), remote = get()) }
    single<ProductRepository> {ProductRepositoryImpl(dataSource = get(),)}
    factory { ProductScreenModel(repository = get()) }
}