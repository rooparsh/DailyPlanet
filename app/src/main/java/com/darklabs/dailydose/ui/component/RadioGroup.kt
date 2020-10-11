package com.darklabs.dailydose.ui.component

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RadioButton(text: String, selectedValue: String, onClickListener: (String) -> Unit) {
    Row(Modifier
            .fillMaxWidth()
            .selectable(
                    selected = (text == selectedValue),
                    onClick = {
                        onClickListener(text)
                    }
            )
            .padding(horizontal = 16.dp)
    ) {
        RadioButton(
                selected = (text == selectedValue),
                onClick = {
                    onClickListener(text)
                }
        )
        Text(
                text = text,
                style = MaterialTheme.typography.body1.merge(),
                modifier = Modifier.padding(start = 16.dp)
        )
    }
}