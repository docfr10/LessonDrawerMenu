package com.example.lesson_drawermenu

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

// Определение экранов
sealed class Screen(val route: String) {
    object Screen1 : Screen("Screen1")
    object Screen2 : Screen("Screen2")
    object Screen3 : Screen("Screen3")
}

// Функция для отображения главного экрана
@Composable
fun MainScreen(navController: NavHostController, padding: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = Screen.Screen1.route,
        modifier = Modifier.padding(paddingValues = padding),
        builder = {
            composable(Screen.Screen1.route) { Screen1() }
            composable(Screen.Screen2.route) { Screen2() }
            composable(Screen.Screen3.route) { Screen3() }
        })
}

@Composable
fun Screen1() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Screen 1")
    }
}

@Composable
fun Screen2() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Screen 2")
    }
}

@Composable
fun Screen3() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Screen 3")
    }
}
