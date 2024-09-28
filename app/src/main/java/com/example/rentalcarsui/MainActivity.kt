package com.example.rentalcarsui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import com.example.rentalcarsui.ui.CarList
import com.example.rentalcarsui.ui.Pager
import com.example.rentalcarsui.ui.TopBar
import com.example.rentalcarsui.ui.theme.Blur
import com.example.rentalcarsui.ui.theme.RentalCarsUiTheme
import dev.chrisbanes.haze.HazeDefaults
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.haze
import dev.chrisbanes.haze.hazeChild

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RentalCarsUiTheme {
                val hazeState = remember {
                    HazeState()
                }
                val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(
                    state = rememberTopAppBarState()
                )
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .nestedScroll(scrollBehavior.nestedScrollConnection)
                        .background(MaterialTheme.colorScheme.background),
                    containerColor = Color.Transparent,
                    topBar = {
                        Column {
                            TopBar(
                                modifier = Modifier.hazeChild(
                                    state = hazeState,
                                ),
                                scrollBehavior = scrollBehavior,
                            )
                            Pager(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .hazeChild(state = hazeState)
                            )
                        }

                    },

                    ) { innerPadding ->
                    HomeScreen(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        hazeState = hazeState
                    )
                }
            }
        }
    }

    @Composable
    fun HomeScreen(modifier: Modifier = Modifier, hazeState: HazeState) {
        Box(
            modifier = modifier.background(MaterialTheme.colorScheme.background)
        ) {
            CarList(
                modifier = Modifier
                    .fillMaxSize()
                    .haze(
                        state = hazeState,
                        blurRadius = 13.dp,
                        tint = HazeDefaults.tint(Blur),
                        backgroundColor = Blur,

                        )
            )
        }
    }
}


