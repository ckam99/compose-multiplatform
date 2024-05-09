package org.example.grocery

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import org.example.grocery.cache.GroceryDatabase
import org.example.grocery.core.database.DatabaseDriverFactory
import org.example.grocery.core.theme.AppTheme
import org.example.grocery.features.product.di.productModule
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.example.grocery.features.product.ui.screens.ProductScreen

@Composable
@Preview
internal fun App(databaseFactory : DatabaseDriverFactory) {
    initKoin(databaseFactory)
    AppTheme {
       Navigator(ProductScreen())
    }
}

fun initKoin(
    databaseFactory : DatabaseDriverFactory,
    appDeclaration: KoinAppDeclaration = {}) {
  startKoin {
    appDeclaration()
      modules(productModule(databaseFactory))
  }
}