package com.victor.bond.connect

sealed interface ConnectAction {
    data class OnNameChanged(val name: String) : ConnectAction
    data object OnConnectClicked : ConnectAction
}