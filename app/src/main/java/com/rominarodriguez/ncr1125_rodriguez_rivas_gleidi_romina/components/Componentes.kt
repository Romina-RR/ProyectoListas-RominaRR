package com.rominarodriguez.ncr1125_rodriguez_rivas_gleidi_romina.components

import android.content.Intent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.automirrored.filled.ReceiptLong
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import com.rominarodriguez.ncr1125_rodriguez_rivas_gleidi_romina.screens.perfil.PerfilActivity
import com.rominarodriguez.ncr1125_rodriguez_rivas_gleidi_romina.screens.catalogo.CatalogoActivity
import com.rominarodriguez.ncr1125_rodriguez_rivas_gleidi_romina.screens.gastos.GastosActivity
import com.rominarodriguez.ncr1125_rodriguez_rivas_gleidi_romina.screens.listas.ListasActivity

@Composable
fun GalleryBottomBar(selectedTab: Int) {
    val context = LocalContext.current

    NavigationBar(containerColor = Color.White) {
        NavigationBarItem(
            selected = selectedTab == 0,
            onClick = {
                if (selectedTab != 0) {
                    val intent = Intent(context, ListasActivity::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                    }
                    context.startActivity(intent)
                }
            },
            icon = { Icon(Icons.AutoMirrored.Filled.List, contentDescription = "Listas") },
            label = { Text("Listas", fontSize = 10.sp) }
        )
        NavigationBarItem(
            selected = selectedTab == 1,
            onClick = {
                if (selectedTab != 1) {
                    val intent = Intent(context, CatalogoActivity::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                    }
                    context.startActivity(intent)
                }
            },
            icon = { Icon(Icons.Default.ShoppingCart, contentDescription = "Catálogo") },
            label = { Text("Catálogo", fontSize = 10.sp) }
        )
        NavigationBarItem(
            selected = selectedTab == 2,
            onClick = {
                if (selectedTab != 2) {
                    val intent = Intent(context, GastosActivity::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                    }
                    context.startActivity(intent)
                }
            },
            icon = { Icon(Icons.AutoMirrored.Filled.ReceiptLong, contentDescription = "Gastos") },
            label = { Text("Gastos", fontSize = 10.sp) }
        )
        NavigationBarItem(
            selected = selectedTab == 3,
            onClick = {
                if (selectedTab != 3) {
                    val intent = Intent(context, PerfilActivity::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                    }
                    context.startActivity(intent)
                }
            },
            icon = { Icon(Icons.Default.Person, contentDescription = "Perfil") },
            label = { Text("Perfil", fontSize = 10.sp) }
        )
    }
}