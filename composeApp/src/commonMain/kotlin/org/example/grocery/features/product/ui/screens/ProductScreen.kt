package org.example.grocery.features.product.ui.screens

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import org.example.grocery.core.ui.State
import org.example.grocery.features.product.domain.models.Product
import org.example.grocery.features.product.ui.components.ProductCard
import org.example.grocery.features.product.ui.components.ProductImage

class ProductScreen : Screen {

    @Composable
    override fun Content() {
        val screenModel = getScreenModel<ProductScreenModel>()
        val state by screenModel.state.collectAsState()
        val isDark = isSystemInDarkTheme()


        LaunchedEffect(isDark){
            println("is dark mode: $isDark")
        }

        val scrollState = rememberLazyGridState()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.safeDrawing)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when(state){
                is State.Loading -> {
                    Box(modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center ) {
                        CircularProgressIndicator() }
                }
                is State.Failed -> {
                    Box(modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center) {
                        Text((state as State.Failed).error, color = Color.Red) }
                }
                is State.Result -> {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        state = scrollState,
                        contentPadding = PaddingValues(16.dp)
                    ){
                        items((state as State.Result<List<Product>>).data, key = {it.id}){
                            ProductCard(
                                modifier = Modifier.height(200.dp).padding(5.dp),
                                product = it,
                            ){
                                
                            }
                        }
                    }
                }
                else -> {}
            }
        }
    }

}