package com.example.passwordmanager.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Face
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.passwordmanager.presentation.states.PasswordUiState
import com.example.passwordmanager.ui.theme.PasswordManagerTheme

@Composable
fun PasswordListItem(
    state: PasswordUiState,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(MaterialTheme.colorScheme.primary)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // image
        Image(
            imageVector = Icons.Rounded.Face,
            contentDescription = "",
            modifier = Modifier
                .padding(8.dp)
                .size(44.dp)
        )
        // column
        Column(
            modifier = Modifier.weight(1f)
        ){
            Text(
                text = state.label, 
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
        // copy icon
        /*IconButton(
            onClick = { onCopyClicked() }
        ) {
            Icon(
                imageVector = Icons.Outlined.ContentCopy,
                contentDescription = stringResource(R.string.copy_password),
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }*/
    }
}

@Preview(showSystemUi = true)
@Composable
private fun PasswordListItemPreview() {
    val state = PasswordUiState(label = "Facebook")

    PasswordManagerTheme {
        Column {
            PasswordListItem(state = state, onClick = {  })
            PasswordListItem(state = state, onClick = {  })
        }
    }
}