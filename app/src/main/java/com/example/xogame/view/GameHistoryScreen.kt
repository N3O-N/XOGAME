package com.example.xogame.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.xogame.componrnt.BackButton
import com.example.xogame.componrnt.ReplayBoard
import com.example.xogame.model.model
import com.example.xogame.ui.theme.XOGAMETheme
import com.example.xogame.viewModel.GameViewModel

@Composable
fun GameHistoryScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: GameViewModel
) {

    val gameHistories = remember { mutableStateOf<List<model.GameHistory>>(emptyList()) }

    LaunchedEffect(Unit) {
        viewModel.fetchGameHistories { histories ->
            gameHistories.value = histories
        }
    }

    BackButton(onClick = { navController.navigate("MenuScreen") })

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(start = 30.dp, top = 50.dp, end = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Game History",
            fontSize = 24.sp,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp)
        )
        Spacer(modifier = Modifier.height(5.dp))
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            items(gameHistories.value) { gameHistory ->
                GameHistoryItem(gameHistory)
            }
        }
    }
}

@Composable
fun GameHistoryItem(game: model.GameHistory) {

    var isVisble by remember { mutableStateOf(false) }

    val winnerText = if (game.winner != "Draw") {
        "winner is ${game.winner ?: "Not fount"}"
    } else {
        "Draw"
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable(onClick = {
                isVisble = true
            }),
        colors = CardDefaults.cardColors(
            containerColor = Color.Gray,
            contentColor = Color.White
        ),
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "GameResult: ${winnerText}")
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Board Size: ${game.boardSize}")

            if (isVisble){
                ReplayBoard(
                    modifier = Modifier,
                    size = game.boardSize,
                    move = game.moves
                ) {
                    isVisble = false
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GameHistoryScreenPreview() {
    val viewModel = GameViewModel()
    XOGAMETheme {
        GameHistoryScreen(navController = rememberNavController(), viewModel = viewModel)
    }
}
