package com.mohammadassad.mealm8.ui.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onSplashFinished: () -> Unit
) {
    val scale = remember { Animatable(0f) }
    val alpha = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        // Start animations
        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(1000)
        )
        alpha.animateTo(
            targetValue = 1f,
            animationSpec = tween(1000)
        )
        
        // Wait for a bit to show the splash
        delay(2000)
        
        // Fade out
        alpha.animateTo(
            targetValue = 0f,
            animationSpec = tween(500)
        )
        
        // Navigate to home screen
        onSplashFinished()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // App icon using Material 3 restaurant icon
            Icon(
                imageVector = Icons.Default.Restaurant,
                contentDescription = "MealM8 Logo",
                modifier = Modifier
                    .size(120.dp)
                    .scale(scale.value)
                    .alpha(alpha.value),
                tint = MaterialTheme.colorScheme.onPrimary
            )
            
            // App name with Plus Jakarta Sans typography
            Text(
                text = "MealM8",
                style = MaterialTheme.typography.displayLarge,
                color = MaterialTheme.colorScheme.onPrimary,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top = 24.dp)
                    .alpha(alpha.value)
            )
            
            // Tagline with proper typography
            Text(
                text = "Your Personal Meal Companion",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.8f),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top = 12.dp)
                    .alpha(alpha.value)
            )
            
            // Subtitle with accent color
            Text(
                text = "Discover • Plan • Enjoy",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.primaryContainer,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top = 8.dp)
                    .alpha(alpha.value)
            )
        }
    }
}
