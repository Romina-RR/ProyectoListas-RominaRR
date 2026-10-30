package com.rominarodriguez.ncr1125_rodriguez_rivas_gleidi_romina.screens.listas

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.annotations.SerializedName
import com.rominarodriguez.ncr1125_rodriguez_rivas_gleidi_romina.data.ApiService
import com.rominarodriguez.ncr1125_rodriguez_rivas_gleidi_romina.ui.theme.NCR1125_RODRIGUEZ_RIVAS_GLEIDI_ROMINATheme
import kotlinx.coroutines.launch

data class ProductoItem(
    @SerializedName("id_detalle") val id: Int = 0,
    @SerializedName("nombre") val nombre: String = "",
    @SerializedName("cantidad") val cantidad: Double = 0.0,
    @SerializedName("unidad") val unidad: String = "",
    @SerializedName("precio_real") val precioReal: Double = 0.0
)

class ProductosViewModel : ViewModel() {
    var productos by mutableStateOf<List<ProductoItem>>(emptyList())
        //private set

    var isLoading by mutableStateOf(false)
        private set

    fun cargarProductos(listaId: Int) {
        viewModelScope.launch {
            isLoading = true
            try {
                productos = ApiService.instance.obtenerProductos(listaId)
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                isLoading = false
            }
        }
    }
}

class ListaDetalleActivity : ComponentActivity() {
    private val viewModel: ProductosViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val listaId = intent.getIntExtra("LISTA_ID", -1)
        val nombreLista = intent.getStringExtra("NOMBRE_LISTA") ?: "Detalle de Lista"

        if (listaId != -1) {
            viewModel.cargarProductos(listaId)
        }

        setContent {
            NCR1125_RODRIGUEZ_RIVAS_GLEIDI_ROMINATheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PantallaListaDetalle(
                        tituloLista = nombreLista,
                        productos = viewModel.productos,
                        onVolverClick = {
                            startActivity(Intent(this@ListaDetalleActivity, ListasActivity::class.java))
                        },
                        onAgregarProductoClick = {
                            startActivity(Intent(this, NuevoProductoActivity::class.java))
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun PantallaListaDetalle(
    tituloLista: String,
    productos: List<ProductoItem>,
    onVolverClick: () -> Unit,
    onAgregarProductoClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(top = 16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = onVolverClick) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Volver"
                    )
                }
                Text(
                    text = tituloLista,
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

            IconButton(onClick = onAgregarProductoClick) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Agregar Producto"
                )
            }
        }

        HorizontalDivider(thickness = 2.dp)
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(productos) { producto ->
                TarjetaProductoItem(producto = producto)
            }
        }
    }
}

@Composable
fun TarjetaProductoItem(producto: ProductoItem) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = producto.nombre,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = "Cantidad: ${producto.cantidad} ${producto.unidad}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
                )
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
            Text(
                text = "S/ ${String.format("%.2f", producto.precioReal)}",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
                IconButton(
                    onClick = { /* Acción para eliminar */ },
                    modifier = Modifier.size(28.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.onBackground),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.background,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }}

        }

    }
}