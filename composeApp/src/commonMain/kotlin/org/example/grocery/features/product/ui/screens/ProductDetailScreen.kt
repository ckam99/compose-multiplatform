package org.example.grocery.features.product.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.example.grocery.core.theme.uiSurfaceColor
import org.example.grocery.core.ui.State
import org.example.grocery.features.product.domain.models.Product
import org.example.grocery.features.product.ui.components.ProductImage
import org.example.grocery.features.product.ui.screens.models.ProductDetailScreenModel
import kotlin.math.round


data class ProductDetailScreen(private val productId: Long,) : Screen {
    
    
    
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val screenModel = getScreenModel<ProductDetailScreenModel>()
        val navigator = LocalNavigator.current
        val state by screenModel.state.collectAsState()
        
        var chart : Int by remember {
            mutableStateOf(1)
        }
        
        LaunchedEffect(productId) {
            screenModel.loadProduct(productId)
        }
        
    Scaffold(
        topBar = {
        TopAppBar(
            title = { Text("Details") },
            navigationIcon = {
                IconButton(
                    onClick = {
                        navigator?.pop()
                    }
                ){
                    Icon(Icons.AutoMirrored.Default.ArrowBack, contentDescription = "Arrow back")
                }
            }
        )
        }
    ) { padding ->
        when(state){
            is State.Loading -> {
                Box(modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center ) {
                    CircularProgressIndicator() }
            }
            is State.Failed -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text((state as State.Failed).error, color = Color.Red)
                    Button(onClick = {
                        screenModel.loadProduct(productId)
                    }){Text("Retry")}
                }
            }
        is State.Result ->{
            Column(
                modifier = Modifier.padding(
                    top = padding.calculateTopPadding(),
                    bottom = padding.calculateBottomPadding()
                ).background(color = uiSurfaceColor)
            ) {
                val product =(state as State.Result<Product?>).data!!
                ProductImage(
                    modifier = Modifier.fillMaxWidth()
                        .height(300.dp),
                    imageUrl = product.image
                )
                
                Box(
                    modifier = Modifier.fillMaxWidth().padding(top = 5.dp)
                        .weight(1f)
                        .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                        .background(Color.White)
                ) {

                    Column(
                        modifier = Modifier.fillMaxWidth().padding(16.dp)
                    ) {
                        Text(product.title)
                        Text(product.category,  modifier = Modifier.alpha(0.6f))
                        Row(
                            modifier = Modifier.padding(0.dp, 20.dp)
                        ){

                            Text("$${product.price}", fontWeight = FontWeight.Bold)
                            Spacer(modifier = Modifier.width(5.dp))
                            Text("$${round(product.price-1)}", modifier = Modifier.alpha(0.6f))
                            Row(
                                modifier = Modifier.weight(1f),
                                horizontalArrangement = Arrangement.End,
                                verticalAlignment = Alignment.CenterVertically
                                ) {
                                IconButton(
                                    colors = IconButtonDefaults.filledIconButtonColors(),
                                    onClick = {
                                        if (chart == 0) return@IconButton
                                        chart -= 1
                                    },
                                ){
                                    Text("-")
                                }
                                    Text("$chart")
                                    IconButton(
                                        colors = IconButtonDefaults.filledIconButtonColors(),
                                        onClick = {chart += 1}){
                                        Text("+")
                                    }
                            }
                        }

                        Text("Description", fontWeight = FontWeight.Bold)
                        Text(product.description, maxLines = 6, overflow = TextOverflow.Ellipsis)
                        
                        Button(
                            modifier = Modifier.fillMaxWidth().padding(0.dp, 5.dp),
                            onClick = {},
                        ){
                            Row {
                                Icon(Icons.Default.ShoppingCart, contentDescription = "Shop")
                                Text("Add to Cart")
                            }
                        }
                    }
                }
                
            }
        }
            else -> {}
        }
    }
    }
}
