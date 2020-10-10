package com.darklabs.dailydose.ui.component

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable

@Composable
fun LoadingState(loadingText: String = "Loading") {
    Column {
        Text(text = loadingText)
    }
}

@Composable
fun ErrorState(errorText: String = "Error") {
    Column {
        Text(text = errorText)
    }
}