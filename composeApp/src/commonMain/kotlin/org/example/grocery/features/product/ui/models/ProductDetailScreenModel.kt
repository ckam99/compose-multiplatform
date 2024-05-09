package org.example.grocery.features.product.ui.screens.models

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.example.grocery.core.ui.State
import org.example.grocery.features.product.domain.models.Product
import org.example.grocery.features.product.domain.repository.ProductRepository


class ProductDetailScreenModel(
    private val repository: ProductRepository
) : ScreenModel {
    
    private var _state = MutableStateFlow<State<Product?>>(State.Idle)
    var state = _state
    
    fun loadProduct(id: Long) {
        screenModelScope.launch {
            _state.value = State.Loading
            val result = repository.FindById(id)
            if(result.isSuccess) {
                _state.value  = State.Result(result.getOrNull())
            }else{
                _state.value = State.Failed("Fail to load product by ID: $id")
            }
            
        }
    }
    
}