package com.rominarodriguez.ncr1125_rodriguez_rivas_gleidi_romina.screens.catalogo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rominarodriguez.ncr1125_rodriguez_rivas_gleidi_romina.data.ApiService
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CatalogoViewModel : ViewModel() {
    private val _uiState = MutableStateFlow<CatalogoUIState>(CatalogoUIState.Loading)
    val uiState: StateFlow<CatalogoUIState> = _uiState.asStateFlow()

    init {
        fetchProductos()
    }

    fun fetchProductos() {
        viewModelScope.launch {
            _uiState.value = CatalogoUIState.Loading
            _uiState.value = try {
                val lista = ApiService.instance.obtenerProductosCatalogo()
                CatalogoUIState.Success(lista)
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                CatalogoUIState.Error("No se pudieron cargar los productos: ${e.message}")
            }
        }
    }
}