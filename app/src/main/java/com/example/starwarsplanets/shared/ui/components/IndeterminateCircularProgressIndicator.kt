package com.example.starwarsplanets.shared.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.starwarsplanets.shared.ui.theme.Purple200

@Composable
fun IndeterminateCircularProgressIndicator(modifier: Modifier = Modifier) {
    Box(
        Modifier
            .fillMaxSize()
            .background(Purple200)
            .then(modifier),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
        Text("Geduldig sein du musst")
    }
}