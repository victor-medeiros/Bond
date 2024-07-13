package com.victor.bond.connect

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import com.victor.bond.BondApp

class ConnectViewModel(
    private val app: Application
): AndroidViewModel(app) {

    var state by mutableStateOf(ConnectState())
        private set

    fun onAction(action: ConnectAction) {
        when (action) {
            ConnectAction.OnConnectClicked -> joinCall()
            is ConnectAction.OnNameChanged -> state = state.copy(name = action.name)
        }
    }

    private fun joinCall() {
        if (state.name.isBlank()) {
            state = state.copy(errorMessage = "The name can't be empty.")
            return
        }

        (app as BondApp).initVideoClient(state.name)

        state = state.copy(isConnected = true)
    }
}