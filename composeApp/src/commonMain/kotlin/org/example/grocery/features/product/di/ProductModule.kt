package product.di


import org.koin.dsl.module
import org.example.grocery.features.product.data.datasource.ProductDataSource
import org.example.grocery.features.product.data.datasource.remote.ProductRemoteSource
import org.example.grocery.features.product.data.repository.ProductRepositoryImpl
import org.example.grocery.features.product.domain.repository.ProductRepository
import org.example.grocery.features.product.ui.screens.ProductScreenModel

val productModule = module {
    single<ProductDataSource> { ProductRemoteSource() }
    single<ProductRepository> {
        ProductRepositoryImpl(
            dataSource = get(),
        )
    }
    factory { ProductScreenModel(repository = get()) }
}