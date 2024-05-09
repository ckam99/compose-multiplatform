package org.example.grocery.features.home.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator


object HomeScreen : Screen {
    
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
       Scaffold(
           topBar = {
               CenterAlignedTopAppBar(title = { Text("Home")})
           }
       ) { padding ->
           Column(
               modifier = Modifier.padding(
                   top = padding.calculateTopPadding(),
                   bottom = padding.calculateBottomPadding()
               )
           ) {

           }
       }
    }
}