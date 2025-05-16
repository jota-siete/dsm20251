// DessertViewModel.kt
package com.example.dessertclicker

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dessertclicker.model.Dessert
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

data class DessertUiState(
    val revenue: Int = 0,
    val dessertsSold: Int = 0,
    val currentDessertImageId: Int = 0,
    val currentDessertPrice: Int = 0
)

class DessertViewModel(private val desserts: List<Dessert>) : ViewModel() {

    private val _uiState = MutableStateFlow(DessertUiState(
        currentDessertImageId = desserts.first().imageId,
        currentDessertPrice = desserts.first().price
    ))
    val uiState: StateFlow<DessertUiState> = _uiState

    private var currentDessertIndex = 0

    fun onDessertClicked() {
        val currentDessert = desserts[currentDessertIndex]
        _uiState.update { currentState ->
            currentState.copy(
                revenue = currentState.revenue + currentDessert.price,
                dessertsSold = currentState.dessertsSold + 1
            )
        }
        updateCurrentDessert()
    }

    private fun updateCurrentDessert() {
        val nextDessert = determineDessertToShow(desserts, _uiState.value.dessertsSold)
        currentDessertIndex = desserts.indexOf(nextDessert)
        _uiState.update { currentState ->
            currentState.copy(
                currentDessertImageId = nextDessert.imageId,
                currentDessertPrice = nextDessert.price
            )
        }
    }
}

/**
 * Determine which dessert to show based on the number of desserts sold.
 */
private fun determineDessertToShow(
    desserts: List<Dessert>,
    dessertsSold: Int
): Dessert {
    var dessertToShow = desserts.first()
    for (dessert in desserts) {
        if (dessertsSold >= dessert.startProductionAmount) {
            dessertToShow = dessert
        } else {
            break
        }
    }
    return dessertToShow
}