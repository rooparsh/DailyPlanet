package com.darklabs.dailydose.ui.component

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun Toolbar(title: String) {
    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = MaterialTheme.colors.primaryVariant
    ) {
        Box(modifier = Modifier.fillMaxSize().align(Alignment.CenterVertically)) {
            Text(
                modifier = Modifier.fillMaxSize().align(Alignment.Center),
                text = title,
                style = MaterialTheme.typography.body1
            )
        }
    }
}