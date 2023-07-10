package com.example.lesson_drawermenu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.lesson_drawermenu.ui.theme.LessonDrawerMenuTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LessonDrawerMenuTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                    val coroutineScope = rememberCoroutineScope()

                    val currentRoute = remember { mutableStateOf("") }

                    navController.addOnDestinationChangedListener { _, destination, _ ->
                        currentRoute.value = destination.route!!
                    }

                    Scaffold(
                        content = { padding ->
                            ModalNavigationDrawer(
                                drawerContent = {
                                    ModalDrawerSheet {
                                        Text("Drawer title", modifier = Modifier.padding(16.dp))
                                        Divider()
                                        NavigationDrawerItem(
                                            label = { Text(text = "Item 1") },
                                            selected = currentRoute.value == Screen.Screen1.route,
                                            onClick = {
                                                navController.navigate(Screen.Screen1.route)
                                                coroutineScope.launch { drawerState.close() }
                                            }
                                        )
                                        NavigationDrawerItem(
                                            label = { Text(text = "Item 2") },
                                            selected = currentRoute.value == Screen.Screen2.route,
                                            onClick = {
                                                navController.navigate(Screen.Screen2.route)
                                                coroutineScope.launch { drawerState.close() }
                                            }
                                        )
                                        NavigationDrawerItem(
                                            label = { Text(text = "Item 3") },
                                            selected = currentRoute.value == Screen.Screen3.route,
                                            onClick = {
                                                navController.navigate(Screen.Screen3.route)
                                                coroutineScope.launch { drawerState.close() }
                                            }
                                        )
                                    }
                                },
                                drawerState = drawerState,
                                content = {
                                    TopAppBar(
                                        title = { Text(text = currentRoute.value) },
                                        navigationIcon = {
                                            IconButton(onClick = {
                                                coroutineScope.launch { drawerState.open() }
                                            }) {
                                                Icon(
                                                    imageVector = Icons.Default.Menu,
                                                    contentDescription = "Drawer menu"
                                                )
                                            }
                                        },
                                        colors = TopAppBarDefaults.smallTopAppBarColors()
                                    )
                                    MainScreen(navController = navController, padding = padding)
                                }
                            )
                        })
                }
            }
        }
    }
}