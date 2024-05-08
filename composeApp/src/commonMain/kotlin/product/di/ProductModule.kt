package product.di


import org.koin.dsl.module
import product.data.datasource.ProductDataSource
import product.data.datasource.network.ProductRemoteSource
import product.data.repository.ProductRepositoryImpl
import product.domain.repository.ProductRepository
import product.ui.viewmodels.ProductScreenModel

val productModule = module {
    single<ProductDataSource> { ProductRemoteSource() }
    single<ProductRepository> {
        ProductRepositoryImpl(
            dataSource = get(),
        )
    }
    factory { ProductScreenModel(repository = get()) }
}