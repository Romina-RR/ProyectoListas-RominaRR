package com.rominarodriguez.ncr1125_rodriguez_rivas_gleidi_romina.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun ModalConfirmacion(
    titulo: String,
    mensaje: String,
    textobtnConfirmar: String = "Si, eliminar",
    onDismiss: () -> Unit,
    onConfirmar: () -> Unit
){
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {Text(text = titulo)},
        text = {Text(text = mensaje)},
        confirmButton = {
            Button(onClick = onConfirmar,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.error
                )
            ){
                Text(text = textobtnConfirmar)
            }
                },
            dismissButton = {
                TextButton(onClick = onDismiss){
                    Text(text = "Cancelar")
                }
            }
            )
        }




