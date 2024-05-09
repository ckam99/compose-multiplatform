package org.example.grocery.features.product.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import org.example.grocery.core.ui.State
import org.example.grocery.features.product.domain.models.Product


class ProductScreen : Screen {

    @Composable
    override fun Content() {
        val screenModel = getScreenModel<ProductScreenModel>()
        val state by screenModel.state.collectAsState()

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
                LazyColumn(
                    modifier = Modifier.padding(20.dp).fillMaxSize()
                ) {
                    items((state as State.Result<List<Product>>).data, key = {it.id}){ product->
                        Text(product.title)
                    }
                }
            }
            else -> {}
        }
    }

}