package com.rominarodriguez.ncr1125_rodriguez_rivas_gleidi_romina.screens.catalogo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rominarodriguez.ncr1125_rodriguez_rivas_gleidi_romina.components.ImputFormulario
import com.rominarodriguez.ncr1125_rodriguez_rivas_gleidi_romina.screens.catalogo.ui.theme.NCR1125_RODRIGUEZ_RIVAS_GLEIDI_ROMINATheme

class EditarProductoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NCR1125_RODRIGUEZ_RIVAS_GLEIDI_ROMINATheme {
                EditarProductoScreen(onBackClick = { finish() })
            }
    }
    }
}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditarProductoScreen(onBackClick: () -> Unit){
    var nombre by remember { mutableStateOf("") }
    var precio by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
            title = {Text("Actualizar Pedido")},
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(Icons.Outlined.ArrowBack,
                        contentDescription = "Volver"
                    )
                }
                }
            )
        }
    ) {innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("Modifica los datos del Producto: ",
                style = MaterialTheme.typography.titleMedium)

            ImputFormulario(
                value = nombre,
                onValueChange = {nombre = it},
                label = "Nombre del producto"
            )

            ImputFormulario(
                value = precio,
                onValueChange = {precio = it},
                label = "Precio S/. "
            )
            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {/*accion para guardar*/},
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Guardar Cambios")
            }
        }

    }
}



