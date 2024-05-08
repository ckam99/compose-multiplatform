import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.ui.tooling.preview.Preview
import product.ui.screens.ProductScreen

@Composable
@Preview
fun App() {

    MaterialTheme {
          Navigator(ProductScreen())
    }
}