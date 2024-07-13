package com.victor.bond.connect

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.victor.bond.ui.theme.BondTheme

@Composable
fun ConnectScreen(
    state: ConnectState,
    onAction: (ConnectAction) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp),
        verticalArrangement = Arrangement.Center,
    ) {
        Text(text = "Choose a name", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = state.name,
            onValueChange = {
                onAction(ConnectAction.OnNameChanged(it))
            },
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = "Name") }
        )
        state.errorMessage?.let {
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = it, color = MaterialTheme.colorScheme.error)
        }
        Spacer(Modifier.height(16.dp))
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { onAction(ConnectAction.OnConnectClicked) }
        ) {
            Text(text = "Connect")
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun ConnectScreePreview() {
    BondTheme {
        ConnectScreen(
            state = ConnectState(errorMessage = "Name can not be blank"),
            onAction = {}
        )
    }
}