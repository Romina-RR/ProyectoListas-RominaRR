package com.rominarodriguez.ncr1125_rodriguez_rivas_gleidi_romina.screens.catalogo

sealed interface CatalogoUIState {
    data object Loading : CatalogoUIState
    data class Success(val productos: List<ProductoCatalogoItem>) : CatalogoUIState
    data class Error(val mensaje: String) : CatalogoUIState
}