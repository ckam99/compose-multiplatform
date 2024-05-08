package org.example.grocery.features.product.ui.screens

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import org.example.grocery.core.ui.State
import kotlinx.coroutines.launch
import org.example.grocery.features.product.domain.models.Product
import org.example.grocery.features.product.domain.repository.ProductRepository


  

class ProductScreenModel(
    private val repository: ProductRepository
) : StateScreenModel<State<List<Product>>>(State.Idle) {
    
    init {
        fetchProductList()
    }
    
    private fun fetchProductList() {
        screenModelScope.launch {
            mutableState.value = State.Loading
            val result = repository.GetAll(-1)
            if (result.isFailure) mutableState.value = State.Failed("Fail to fetch products")
            else mutableState.value = State.Result(result.getOrDefault(listOf()))
        }
    }
    
}