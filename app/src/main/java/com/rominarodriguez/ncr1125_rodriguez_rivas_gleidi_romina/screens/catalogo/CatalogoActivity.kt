package com.rominarodriguez.ncr1125_rodriguez_rivas_gleidi_romina.screens.catalogo

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.ErrorOutline
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rominarodriguez.ncr1125_rodriguez_rivas_gleidi_romina.ui.theme.NCR1125_RODRIGUEZ_RIVAS_GLEIDI_ROMINATheme
import com.google.gson.annotations.SerializedName
import com.rominarodriguez.ncr1125_rodriguez_rivas_gleidi_romina.components.GalleryBottomBar
import com.rominarodriguez.ncr1125_rodriguez_rivas_gleidi_romina.models.ProductoCatalogoItem

data class ProductoCatalogoItem(
    @SerializedName("id") val id: Int,
    @SerializedName("nombre") val nombreProducto: String,
    @SerializedName("cantidad") val cantidad: String,
    @SerializedName("unidad") val unidad: String,
    @SerializedName("precio") val precioReal: Double,
    @SerializedName("imagen_url") val imagenUrl: String,
    @SerializedName("categoria") val categoria: String
)

class CatalogoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = ViewModelProvider(this)[CatalogoViewModel::class.java]
        enableEdgeToEdge()
        setContent {
            NCR1125_RODRIGUEZ_RIVAS_GLEIDI_ROMINATheme {
                val uiState by viewModel.uiState.collectAsStateWithLifecycle()
                CatalogoScreen(
                    uiState = uiState,
                    onBackClick = { finish() },
                    onReintentar = { viewModel.fetchProductos() }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("DefaultLocale")
@Composable
fun CatalogoScreen(
    uiState: CatalogoUIState,
    onBackClick: () -> Unit,
    onReintentar: () -> Unit
) {
    var categoriaSeleccionada by remember { mutableStateOf("Todas") }
    val categorias = listOf("Todas", "Frutas", "Limpieza", "Abarrotes", "Bebidas")

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = "Catálogo de Productos",
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontFamily = FontFamily.Serif,
                                fontWeight = FontWeight.Bold,
                                fontSize = 24.sp
                            ),
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        Text(
                            text = "Nuestros productos disponibles",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(.7f)
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Outlined.ArrowBack, contentDescription = "Volver")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
            )
        },
        bottomBar = { GalleryBottomBar(selectedTab = 1) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(8.dp))

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(bottom = 12.dp)
            ) {
                items(categorias) { cat ->
                    val esSeleccionado = cat == categoriaSeleccionada
                    Surface(
                        onClick = { categoriaSeleccionada = cat },
                        shape = RoundedCornerShape(20.dp),
                        color = if (esSeleccionado) MaterialTheme.colorScheme.onBackground else MaterialTheme.colorScheme.surface,
                        border = if (esSeleccionado) null else BorderStroke(
                            1.dp,
                            MaterialTheme.colorScheme.outlineVariant
                        )
                    ) {
                        Text(
                            text = cat,
                            color = if (esSeleccionado) MaterialTheme.colorScheme.background else MaterialTheme.colorScheme.onBackground,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                        )
                    }
                }
            }

            Box(modifier = Modifier.fillMaxSize()) {
                when (uiState) {
                    is CatalogoUIState.Loading -> {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center),
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }
                    is CatalogoUIState.Error -> {
                        Column(
                            modifier = Modifier.align(Alignment.Center).padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                Icons.Outlined.ErrorOutline,
                                contentDescription = null,
                                modifier = Modifier.size(48.dp),
                                tint = MaterialTheme.colorScheme.error
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(uiState.mensaje, style = MaterialTheme.typography.titleMedium)
                            Spacer(modifier = Modifier.height(12.dp))
                            FilledTonalButton(onClick = onReintentar) {
                                Text("Reintentar")
                            }
                        }
                    }
                    is CatalogoUIState.Success -> {
                        val productosFiltrados = if (categoriaSeleccionada == "Todas") {
                            uiState.productos
                        } else {
                            uiState.productos.filter { it.categoria.equals(categoriaSeleccionada, ignoreCase = true) }
                        }

                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp),
                            contentPadding = PaddingValues(bottom = 16.dp),
                            modifier = Modifier.fillMaxSize()
                        ) {
                            items(productosFiltrados, key = { it.id }) { producto ->
                                ProductoCatalogoItem(producto = producto)
                            }
                        }
                    }
                }
            }
        }
    }
}