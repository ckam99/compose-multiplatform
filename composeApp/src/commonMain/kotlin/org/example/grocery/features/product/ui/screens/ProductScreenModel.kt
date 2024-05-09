package org.example.grocery.features.product.ui.screens

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.periodUntil
import org.example.grocery.core.ui.State
import org.example.grocery.features.product.domain.models.Product
import org.example.grocery.features.product.domain.repository.ProductRepository


class ProductScreenModel(
    private val repository: ProductRepository
) : StateScreenModel<State<List<Product>>>(State.Idle) {
    
    private var lastUpdate = MutableStateFlow(Clock.System.now())
    private var isLocalLoaded = MutableStateFlow(false)

    init {
        fetchProductList()
    }
    
    fun fetchProductList() {
        screenModelScope.launch {
            mutableState.value = State.Loading
            val result = repository.GetAll(reload = elapsedTime())
            if (result.isFailure) mutableState.value = State.Failed("Fail to fetch products")
            else mutableState.value = State.Result(result.getOrDefault(listOf()))
            lastUpdate.value = Clock.System.now()
            isLocalLoaded.value = true
        }
    }
    
    private fun elapsedTime(): Boolean {
        if (!isLocalLoaded.value) return true
        val endTime = Clock.System.now()
        val elapsedTime = endTime.periodUntil(lastUpdate.value, TimeZone.currentSystemDefault())
        return elapsedTime.minutes > 30
    }

}