import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import grocery.composeapp.generated.resources.Res
import grocery.composeapp.generated.resources.compose_multiplatform
import product.data.datasource.network.ProductRemoteSource
import product.data.repository.ProductRepositoryImpl

@OptIn(ExperimentalResourceApi::class)
@Composable
@Preview
fun App() {

    val repo = ProductRepositoryImpl(ProductRemoteSource())

    var text : String by remember {
        mutableStateOf("")
    }

    LaunchedEffect(true){
       val result = repo.GetAll(2)
        if (result.isFailure){
            text = result.exceptionOrNull().toString()
            print(text)
        }else{
            text = result.getOrNull().toString()
            print(text)
        }
    }

    MaterialTheme {
        var showContent by remember { mutableStateOf(false) }
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = { showContent = !showContent }) {
                Text("Click me!")
            }
            AnimatedVisibility(showContent) {
                val greeting = remember { Greeting().greet() }
                Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(painterResource(Res.drawable.compose_multiplatform), null)
                    Text("Compose: $greeting")
                }
            }
            Text(text)
        }
    }
}