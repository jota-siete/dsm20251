// MainActivity.kt
package com.example.dessertclicker

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.dessertclicker.data.Datasource
import com.example.dessertclicker.ui.DessertClickerApp
import com.example.dessertclicker.ui.theme.DessertClickerTheme

// Tag for logging
private const val TAG = "MainActivity"

class MainActivity : ComponentActivity() {

    private val dessertViewModel: DessertViewModel by viewModels {
        DessertViewModelFactory(Datasource.dessertList)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate Called")
        setContent {
            DessertClickerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .statusBarsPadding(),
                ) {
                    val uiState by dessertViewModel.uiState.collectAsState()
                    DessertClickerApp(
                        dessertUiState = uiState,
                        onDessertClicked = dessertViewModel::onDessertClicked
                    )
                }
            }
        }
    }
}