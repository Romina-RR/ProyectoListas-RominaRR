package com.rominarodriguez.ncr1125_rodriguez_rivas_gleidi_romina.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.rominarodriguez.ncr1125_rodriguez_rivas_gleidi_romina.R

// Declaración de la familia Kedebideri con todos sus pesos
val kedebideriFamily = FontFamily(
    Font(R.font.kedebideri_regular, FontWeight.Normal),
    Font(R.font.kedebideri_medium, FontWeight.Medium),
    Font(R.font.kedebideri_bold, FontWeight.Bold),
    Font(R.font.kedebideri_black, FontWeight.Black)
)

/*
 * Sistema tipográfico usando exclusivamente Kedebideri
 */
val Typography = Typography(

    // -------------------------------------------------
    // TÍTULOS Y CABECERAS
    // -------------------------------------------------

    // Pantalla "Mis listas", "Despensa"
    displayLarge = TextStyle(
        fontFamily = kedebideriFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 35.sp,
        lineHeight = 46.sp,
        letterSpacing = (-0.5).sp
    ),

    // Títulos de pantalla secundarios o vistas de detalle
    headlineLarge = TextStyle(
        fontFamily = kedebideriFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 32.sp,
        lineHeight = 38.sp,
        letterSpacing = (-0.5).sp
    ),

    headlineMedium = TextStyle(
        fontFamily = kedebideriFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 24.sp,
        lineHeight = 30.sp
    ),

    // -------------------------------------------------
    // TARJETAS Y ELEMENTOS DESTACADOS
    // -------------------------------------------------

    // Nombre de la lista en la tarjeta ("Cosas de casa", "Finde Mallorca")
    titleLarge = TextStyle(
        fontFamily = kedebideriFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        lineHeight = 24.sp
    ),

    // Subtítulos o nombres de secciones
    titleMedium = TextStyle(
        fontFamily = kedebideriFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 22.sp
    ),

    // -------------------------------------------------
    // ÍTEMS Y TEXTO GENERAL
    // -------------------------------------------------

    // Nombres de productos ("Tomate rallado", "Aguacate")
    bodyLarge = TextStyle(
        fontFamily = kedebideriFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 22.sp
    ),

    // Texto secundario o descripciones
    bodyMedium = TextStyle(
        fontFamily = kedebideriFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp
    ),

    // -------------------------------------------------
    // ETIQUETAS, FECHAS Y CANTIDADES
    // -------------------------------------------------

    // Cantidades ("2u.", "500g", "14/17") y fechas ("20/05/2023")
    labelMedium = TextStyle(
        fontFamily = kedebideriFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp
    ),

    // Botones (Pestañas "Actuales" / "Archivadas")
    labelLarge = TextStyle(
        fontFamily = kedebideriFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 18.sp
    )
)