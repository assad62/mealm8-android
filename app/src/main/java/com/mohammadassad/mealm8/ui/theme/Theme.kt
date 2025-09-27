package com.mohammadassad.mealm8.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = Primary400,
    onPrimary = Primary900,
    primaryContainer = Primary800,
    onPrimaryContainer = Primary100,
    
    secondary = Secondary400,
    onSecondary = Secondary900,
    secondaryContainer = Secondary800,
    onSecondaryContainer = Secondary100,
    
    tertiary = AccentBlue,
    onTertiary = White,
    tertiaryContainer = Primary800,
    onTertiaryContainer = Primary100,
    
    background = BackgroundPrimaryDark,
    onBackground = TextPrimaryDark,
    surface = BackgroundSecondaryDark,
    onSurface = TextPrimaryDark,
    surfaceVariant = CardBackgroundDark,
    onSurfaceVariant = TextSecondaryDark,
    
    error = AccentRed,
    onError = White,
    errorContainer = Primary800,
    onErrorContainer = AccentRed
)

private val LightColorScheme = lightColorScheme(
    primary = Primary500,
    onPrimary = Secondary50,
    primaryContainer = Primary100,
    onPrimaryContainer = Primary900,
    
    secondary = Secondary600,
    onSecondary = Secondary50,
    secondaryContainer = Secondary100,
    onSecondaryContainer = Secondary900,
    
    tertiary = AccentBlue,
    onTertiary = White,
    tertiaryContainer = Primary100,
    onTertiaryContainer = Primary900,
    
    background = BackgroundPrimaryLight,
    onBackground = TextPrimaryLight,
    surface = BackgroundSecondaryLight,
    onSurface = TextPrimaryLight,
    surfaceVariant = CardBackgroundLight,
    onSurfaceVariant = TextSecondaryLight,
    
    error = AccentRed,
    onError = White,
    errorContainer = Primary100,
    onErrorContainer = AccentRed
)

@Composable
fun MealM8Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false, // Disabled to use our custom green theme
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
    
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}