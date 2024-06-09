package com.example.xogame.view

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.xogame.componrnt.TutorialPopUp
import com.example.xogame.viewModel.GameViewModel

@Composable
fun MenuScreen(
    title: String,
    modifier: Modifier = Modifier ,
    navController: NavController
) {

    var isVisble by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment= Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Spacer(modifier = modifier.height(50.dp))

        Text(
            text = "$title!",
            modifier = modifier,
            fontSize = 50.sp
        )

        Spacer(modifier = modifier.height(30.dp))

        Button(
            onClick = { navController.navigate("GameSetUpScreen")},
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 50.dp, end = 50.dp),
            shape = MaterialTheme.shapes.medium,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.DarkGray,
                contentColor = Color.White )
        ) {
            Text(text = "PLAY GAME")
        }

        Spacer(modifier = modifier.height(20.dp))

        Button(
            onClick = {
                navController.navigate("GameHistoryScreen") },
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 50.dp, end = 50.dp),
            shape = MaterialTheme.shapes.medium,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.DarkGray,
                contentColor = Color.White )
        ) {
            Text(text = "GAME HISTORY")
        }

        Spacer(modifier = modifier.height(20.dp))

        Button(
            onClick = { isVisble = true },
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 50.dp, end = 50.dp),
            shape = MaterialTheme.shapes.medium,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.DarkGray,
                contentColor = Color.White )
        ) {
            Text(text = "HOW TO PLAY?")
            if (isVisble == true) {
                TutorialPopUp {
                    isVisble = false
                }
            }
        }

    }
}