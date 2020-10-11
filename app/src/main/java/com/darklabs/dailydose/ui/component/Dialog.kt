package com.darklabs.dailydose.ui.component

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog


@Composable
fun SingleSelectDialog(title: String,
                       optionsList: List<String>,
                       defaultSelected: Int,
                       submitButtonText: String,
                       onSubmitButtonClick: (Int) -> Unit,
                       onDismissRequest: () -> Unit) {

    val selectedOption = mutableStateOf(defaultSelected)

    Dialog(onDismissRequest = { onDismissRequest.invoke() }) {
        Surface(modifier = Modifier.preferredWidth(300.dp),
                shape = RoundedCornerShape(10.dp)) {

            Column(modifier = Modifier.padding(10.dp)) {

                Text(text = title)

                Spacer(modifier = Modifier.preferredHeight(10.dp))

                LazyColumnFor(items = optionsList, modifier = Modifier.preferredHeight(500.dp)) {
                    RadioButton(it, optionsList[selectedOption.value]) { selectedValue ->
                        selectedOption.value = optionsList.indexOf(selectedValue)
                    }
                }

                Spacer(modifier = Modifier.preferredHeight(10.dp))

                Button(onClick = {
                    onSubmitButtonClick.invoke(selectedOption.value)
                    onDismissRequest.invoke()
                },
                        shape = MaterialTheme.shapes.medium) {
                    Text(text = submitButtonText)
                }
            }

        }
    }
}