package com.victor.bond

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.victor.bond.connect.ConnectScreen
import com.victor.bond.connect.ConnectViewModel
import com.victor.bond.ui.theme.BondTheme
import com.victor.bond.video.CallState
import com.victor.bond.video.VideoCallScreen
import com.victor.bond.video.VideoCallViewModel
import io.getstream.video.android.compose.theme.VideoTheme
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BondTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = ConnectRoute,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable<ConnectRoute> {
                            val viewModel = koinViewModel<ConnectViewModel>()
                            val state = viewModel.state

                            LaunchedEffect(key1 = state) {
                                if (state.isConnected)
                                    navController.navigate(VideoCallRoute) {
                                        popUpTo(VideoCallRoute) { inclusive = true }
                                    }
                            }

                            ConnectScreen(state, viewModel::onAction)
                        }
                        composable<VideoCallRoute> {
                            val viewModel = koinViewModel<VideoCallViewModel>()
                            val state = viewModel.state

                            LaunchedEffect(key1 = state) {
                                if (state.callState == CallState.ENDED)
                                    navController.navigate(ConnectRoute) {
                                        popUpTo(VideoCallRoute) { inclusive = true }
                                    }
                            }
                            VideoTheme {
                                VideoCallScreen(state, viewModel::onAction)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Serializable
data object ConnectRoute

@Serializable
data object VideoCallRoute