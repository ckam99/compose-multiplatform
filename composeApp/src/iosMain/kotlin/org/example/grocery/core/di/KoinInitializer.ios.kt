package org.example.grocery.core.di

import org.example.grocery.features.product.di.productModule
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module


@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class KoinInitializer {
    actual fun init() {
        startKoin {
            modules( iosAppModule + productModule)
        }
    }
}