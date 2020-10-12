package com.darklabs.dailydose.ui.component

import androidx.compose.foundation.Text
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable

@Composable
fun LoadingState(loadingText: String = "Loading") {
    Surface {
        Text(text = loadingText)
    }
}

@Composable
fun ErrorState(errorText: String = "Error") {
    Surface {
        Text(text = errorText)
    }
}