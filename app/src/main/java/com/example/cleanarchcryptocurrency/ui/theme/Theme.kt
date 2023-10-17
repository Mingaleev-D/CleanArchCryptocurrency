package com.example.cleanarchcryptocurrency.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable


@SuppressLint("ConflictingOnColor")
private val DarkColorPalette = darkColors(
    primary = Black900,
    secondary = Black900,
    primaryVariant = Black900,
    background = Black900,
    surface = White150,
    onPrimary = White,
    onSecondary = Black500,
    onBackground = White,
    onSurface = White850,
)

@SuppressLint("ConflictingOnColor")
private val LightColorPalette = lightColors(
    primary = White,
    secondary = White,
    primaryVariant = White,
    background = White,
    surface = White850,
    onPrimary = Black900,
    onSecondary = Black500,
    onBackground = Gray,
    onSurface = Gray,
)

@Composable
fun CleanArchCryptocurrencyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
   val colors = if (darkTheme) {
      DarkColorPalette
   } else {
      LightColorPalette
   }

   MaterialTheme(
       colors = colors,
       typography = Typography,
       content = content
   )
}