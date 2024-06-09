package com.example.xogame.view

import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.xogame.componrnt.BackButton
import com.example.xogame.ui.theme.XOGAMETheme
import com.example.xogame.viewModel.GameViewModel

@Composable
fun GameSetUpScreen(
    modifier: Modifier = Modifier,
    navController: NavController ,
    viewModel: GameViewModel
) {

    BackButton(onClick = { navController.navigate("MenuScreen") })

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment= Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = modifier.height(50.dp))
        Text(
            text = "SELECT YOUR BOARD SIZE",
            modifier = modifier.align(Alignment.CenterHorizontally).padding(16.dp),
            fontSize = 24.sp
        )
        Spacer(modifier = modifier.height(30.dp))
        Button(
            onClick = {
                viewModel.setBoardSize(3)
                navController.navigate("PlayScreen/${3}") },
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 50.dp, end = 50.dp),
            shape = MaterialTheme.shapes.medium,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.DarkGray,
                contentColor = Color.White )
        ) {
            Text(text = "3 x 3")
        }
        Spacer(modifier = modifier.height(20.dp))
        Button(
            onClick = {
                viewModel.setBoardSize(5)
                navController.navigate("PlayScreen/${5}") },
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 50.dp, end = 50.dp),
            shape = MaterialTheme.shapes.medium,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.DarkGray,
                contentColor = Color.White )
        ) {
            Text(text = "5 x 5")
        }
        Spacer(modifier = modifier.height(20.dp))
        Button(
            onClick = {
                viewModel.setBoardSize(7)
                navController.navigate("PlayScreen/${7}") },
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 50.dp, end = 50.dp),
            shape = MaterialTheme.shapes.medium,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.DarkGray,
                contentColor = Color.White )
        ) {
            Text(text = "7 x 7")
        }

    }
}

@Preview(showBackground = true)
@Composable
fun GameSetUpScreenPreview() {
    val viewModel = GameViewModel()
    XOGAMETheme {
        GameSetUpScreen(navController = rememberNavController(), viewModel = viewModel)
    }
}