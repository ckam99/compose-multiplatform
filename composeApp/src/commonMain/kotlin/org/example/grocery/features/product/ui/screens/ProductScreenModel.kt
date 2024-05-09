package org.example.grocery.features.product.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.example.grocery.core.ui.State
import kotlinx.coroutines.launch
import org.example.grocery.features.product.domain.models.Product
import org.example.grocery.features.product.domain.repository.ProductRepository


  

class ProductScreenModel(
    private val repository: ProductRepository
) : StateScreenModel<State<List<Product>>>(State.Idle) {
    
    private var lastUpdate = MutableStateFlow(true)

    init {
        fetchProductList()
    }
    
    fun fetchProductList() {
        screenModelScope.launch {
            mutableState.value = State.Loading
            val result = repository.GetAll(reload = lastUpdate.value)
            if (result.isFailure) mutableState.value = State.Failed("Fail to fetch products")
            else mutableState.value = State.Result(result.getOrDefault(listOf()))
            lastUpdate.value = false
        }
    }
    
}