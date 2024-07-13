package com.victor.bond.video

sealed interface VideoCallAction {
    data object OnDisconnectClick : VideoCallAction
    data object JoinCallClick : VideoCallAction
}