package com.darklabs.dailydose.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.clickable
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.loadVectorResource
import com.darklabs.dailydose.R

@Composable
fun Toolbar(title: String, onFilterClick: () -> Unit) {
    TopAppBar(
            title = { Text(text = title) },
            actions = {
                val image = loadVectorResource(id = R.drawable.ic_filter)
                image.resource.resource?.let {
                    Image(asset = it, modifier = Modifier.clickable(onClick = onFilterClick))
                }
            },
            backgroundColor = MaterialTheme.colors.primaryVariant
    )
}