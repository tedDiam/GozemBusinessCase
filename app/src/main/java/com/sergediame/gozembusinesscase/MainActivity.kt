package com.sergediame.gozembusinesscase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.sergediame.gozembusinesscase.navigation.Graph
import com.sergediame.gozembusinesscase.navigation.RootNavGraph
import com.sergediame.gozembusinesscase.ui.theme.GozemBusinessCaseTheme
import kotlinx.coroutines.launch
import org.koin.androidx.compose.KoinAndroidContext

class MainActivity : ComponentActivity() {

    private val viewModel: MainActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.isLoggedState.collect { isLogged ->
                    setContent {
                        KoinAndroidContext {
                            GozemBusinessCaseTheme {
                                RootNavGraph(
                                    startDestination = getStartDestination(isLogged)
                                )
                            }
                        }

                    }
                }
            }
        }
    }
}

private fun getStartDestination(isLogged: Boolean) =
    if (isLogged) Graph.Home.route else Graph.Auth.route



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GozemBusinessCaseTheme {

    }
}