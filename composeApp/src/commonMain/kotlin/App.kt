import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import product.di.productModule
import product.ui.screens.ProductScreen

@Composable
@Preview
fun App() {
       initKoin()
       MaterialTheme {
            Navigator(ProductScreen())
        }
}

fun initKoin(appDeclaration: KoinAppDeclaration = {}) {
  startKoin {
    appDeclaration()
      modules(productModule)
  }
}