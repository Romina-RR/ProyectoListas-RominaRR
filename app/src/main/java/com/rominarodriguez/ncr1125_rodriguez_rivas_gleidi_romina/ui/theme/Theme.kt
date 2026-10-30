package com.rominarodriguez.ncr1125_rodriguez_rivas_gleidi_romina.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.platform.LocalContext

private val LightColorScheme = lightColorScheme(
    // Principal
    primary = AppPrimary,
    onPrimary = AppSurface,

    primaryContainer = AppBackground,
    onPrimaryContainer = AppForeground,

    // Secundario
    secondary = AppSecondary,
    onSecondary = AppForeground,

    secondaryContainer = AppBackground,
    onSecondaryContainer = AppForeground,

    // Acento
    tertiary = AppAccent,
    onTertiary = AppForeground,

    // Fondo
    background = AppBackground,
    onBackground = AppForeground,

    // Superficies
    surface = AppSurface,
    onSurface = AppForeground,

    // Variante de superficie
    surfaceVariant = AppBackground,
    onSurfaceVariant = AppForeground,

    // Bordes
    outline = AppSecondary,

    // Tint
    surfaceTint = AppPrimary
)

private val DarkColorScheme = darkColorScheme(
    primary = AppBackground,
    onPrimary = AppForeground,

    primaryContainer = AppPrimary,
    onPrimaryContainer = AppSurface,

    secondary = AppSecondary,
    onSecondary = AppSurface,

    secondaryContainer = AppSecondary,
    onSecondaryContainer = AppSurface,

    tertiary = AppAccent,
    onTertiary = AppForeground,

    background = AppForeground,
    onBackground = AppSurface,

    surface = AppPrimary,
    onSurface = AppSurface,

    surfaceVariant = AppSecondary,
    onSurfaceVariant = AppSurface,

    outline = AppBackground,

    surfaceTint = AppBackground
)

@Composable
fun NCR1125_RODRIGUEZ_RIVAS_GLEIDI_ROMINATheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

object AppTheme{
    val spacing: AppSpacing
        @Composable @ReadOnlyComposable get() = LocalAppSpacing.current

    val sizes: AppComponentSizes
        @Composable @ReadOnlyComposable get() = LocalAppComponentSizes.current
}