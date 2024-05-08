package core.ui

import androidx.compose.material.Button
import androidx.compose.runtime.Composable

sealed class State<out R> {
        object Idle : State<Nothing>()
        object Loading : State<Nothing>()
        data class Result<out R>(val data: R): State<R>()
        data class Failed(val error: String) : State<Nothing>()
}