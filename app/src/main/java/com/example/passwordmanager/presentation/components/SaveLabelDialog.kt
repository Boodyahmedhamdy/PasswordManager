package com.example.passwordmanager.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Label
import androidx.compose.material.icons.outlined.Save
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SaveLabelDialog(
    value: String,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    onConfirmButtonClicked: () -> Unit = {},
    onDismissRequest: () -> Unit = {},
    onDismissButtonClicked: () -> Unit = {},
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = { onDismissRequest() },
        confirmButton = {
            Button(onClick = { onConfirmButtonClicked() }) {
                Text(text = "Save")
            }
        },
        dismissButton = {
            OutlinedButton(onClick = { onDismissButtonClicked() }) {
                Text(text = "Cancel")
            }
        },
        title = {
            Text(text = "Save password with label")
        },
        icon = {
            Icon(imageVector = Icons.Outlined.Save, contentDescription = "")
        },

        text = {
            OutlinedTextField(
                value = value,
                onValueChange = { onValueChanged(it) },
                placeholder = {
                    Text(text = "Facebook, Twitter")
                },
                label = {
                    Text(text = "label")
                },
                maxLines = 1,
                leadingIcon = {
                    Icon(imageVector = Icons.AutoMirrored.Outlined.Label, contentDescription = "")
                }
            )
        },


    )
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun SaveLabelDialogPreview() {

    var name by remember {
        mutableStateOf("")
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        SaveLabelDialog(
            value = name,
            onValueChanged = {
                name = it
            }
        )

    }
}