package com.rominarodriguez.ncr1125_rodriguez_rivas_gleidi_romina.screens.listas

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ListaViewModel : ViewModel() {

    var datos by mutableStateOf<List<ListaItem>>(emptyList())
        private set

    var isLoading by mutableStateOf(false)
        private set

    init {
        cargarDatosDesdeApi()
    }

    fun cargarDatosDesdeApi() {
        viewModelScope.launch {
            isLoading = true
            try {
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                isLoading = false
            }
        }
    }
}