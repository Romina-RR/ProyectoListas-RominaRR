package com.rominarodriguez.ncr1125_rodriguez_rivas_gleidi_romina.screens.listas

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Cake
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.LocalPharmacy
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.gson.annotations.SerializedName
import com.rominarodriguez.ncr1125_rodriguez_rivas_gleidi_romina.components.GalleryBottomBar
import com.rominarodriguez.ncr1125_rodriguez_rivas_gleidi_romina.MainActivity
import com.rominarodriguez.ncr1125_rodriguez_rivas_gleidi_romina.R
import com.rominarodriguez.ncr1125_rodriguez_rivas_gleidi_romina.components.ModalConfirmacion
import com.rominarodriguez.ncr1125_rodriguez_rivas_gleidi_romina.data.ApiService
import com.rominarodriguez.ncr1125_rodriguez_rivas_gleidi_romina.ui.theme.AppTheme
import com.rominarodriguez.ncr1125_rodriguez_rivas_gleidi_romina.ui.theme.NCR1125_RODRIGUEZ_RIVAS_GLEIDI_ROMINATheme
import kotlinx.coroutines.launch

data class ListaItem(
    @SerializedName("id_lista") val id: Int,
    @SerializedName("nombre") val titulo: String,
    @SerializedName("estado") val estado: String,
    @SerializedName("fecha_creacion") val fechaCreacion: String?,
    @SerializedName("presupuesto_limite") val presupuestoLimite: Double?,
    @SerializedName("cantidad_productos") val cantidadProductos: Int = 0
) {
    val esArchivada: Boolean
        get() = estado.equals("archivada", ignoreCase = true)

    val icono: ImageVector
        get() = when {
            titulo.contains("Super", true) -> Icons.Default.ShoppingCart
            titulo.contains("Farm", true) -> Icons.Default.LocalPharmacy
            titulo.contains("Ferre", true) -> Icons.Default.Build
            else -> Icons.Default.Cake
        }
}

class ListasViewModel : ViewModel() {
    var listas by mutableStateOf<List<ListaItem>>(emptyList())

    var isLoading by mutableStateOf(false)
        private set

    init {
        cargarListas()
    }


    fun cargarListas() {
        viewModelScope.launch {
            isLoading = true
            try {
                listas = ApiService.instance.obtenerListas()
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                isLoading = false
            }
        }
    }
}

class ListasActivity : ComponentActivity() {
    private val viewModel: ListasViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NCR1125_RODRIGUEZ_RIVAS_GLEIDI_ROMINATheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.primaryContainer
                ) {
                    ListaScreen(
                        viewModel = viewModel,
                        onStartClick = {
                            startActivity(Intent(this@ListasActivity, MainActivity::class.java))
                        },
                        onNuevaListaClick = {
                            startActivity(Intent(this@ListasActivity, NuevaListaActivity::class.java))
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun ListaScreen(
    viewModel: ListasViewModel,
    onStartClick: () -> Unit,
    onNuevaListaClick: () -> Unit
) {
    val context = LocalContext.current
    val todasLasListas = viewModel.listas
    val isLoading = viewModel.isLoading
    var mostrarModal by remember { mutableStateOf(false) }
    var listaSeleccionada by remember {mutableStateOf<ListaItem?>(null)}

    Scaffold(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        bottomBar = {
            GalleryBottomBar(selectedTab = 0)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .statusBarsPadding()
                .padding(horizontal = 16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(id = R.string.tituloLista),
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        shape = CircleShape,
                        color = Color.White,
                        modifier = Modifier.padding(4.dp)
                    ) {
                        IconButton(onClick = onNuevaListaClick) {
                            Icon(
                                imageVector = Icons.Filled.Add,
                                contentDescription = "Agregar",
                                tint = MaterialTheme.colorScheme.onBackground
                            )
                        }
                    }
                }
            }

            HorizontalDivider(thickness = 2.dp)
            Spacer(modifier = Modifier.height(AppTheme.spacing.medium))

            Image(
                painter = painterResource(id = R.drawable.pic2),
                contentDescription = stringResource(R.string.logo_description)
            )

            Spacer(modifier = Modifier.height(8.dp))

            if (isLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentPadding = PaddingValues(vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(todasLasListas) { lista ->
                        TarjetaListaItem(
                            lista = lista,
                            onClick = {
                                val intent = Intent(context, ListaDetalleActivity::class.java).apply {
                                    putExtra("LISTA_ID", lista.id)
                                    putExtra("NOMBRE_LISTA", lista.titulo)
                                }
                                context.startActivity(intent)
                            },
                            onDeleteClick = {
                                listaSeleccionada = lista
                                mostrarModal = true
                            }
                        )
                    }
                }
            }
        }
    }


if(mostrarModal){
    ModalConfirmacion(
        titulo = "Eliminar Lista",
        mensaje = "¿Esta seguro de eliminar '${listaSeleccionada?.titulo}'?",
        textobtnConfirmar = "Si, eliminar",
        onDismiss = {
            mostrarModal = false
        },
        onConfirmar = {
           if (listaSeleccionada != null){
               viewModel.listas = viewModel.listas.filter{it.id != listaSeleccionada?.id}
           }
            mostrarModal = false
        }
    )
}
}


@Composable
fun TarjetaListaItem(
    lista: ListaItem,
    onClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Card(
        onClick = onClick,
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
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = lista.icono,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.tertiary,
                        modifier = Modifier.padding(AppTheme.spacing.extraSmall)
                    )
                    Text(
                        text = lista.titulo,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    if (lista.esArchivada) {
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "(Archivada)",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
                Text(
                    text = "${lista.cantidadProductos} productos",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
                )
            }

            IconButton(
                onClick = onDeleteClick,
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
            }
        }
        HorizontalDivider(thickness = 1.dp)
    }
}







