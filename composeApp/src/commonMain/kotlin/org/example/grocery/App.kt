package org.example.grocery

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import org.example.grocery.core.theme.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.example.grocery.features.product.ui.screens.ProductScreen

@Composable
@Preview
internal fun App() {
    AppTheme {
       Navigator(ProductScreen()){
           SlideTransition(it)
       }
    }
}