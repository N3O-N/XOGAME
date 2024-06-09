package com.example.xogame.view

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.xogame.componrnt.BackButton
import com.example.xogame.componrnt.RegameButton
import com.example.xogame.componrnt.board
import com.example.xogame.viewModel.GameViewModel

@Composable
fun PlayScreen(
    modifier: Modifier = Modifier ,
    navController: NavController,
    boardSize: Int ,
    viewModel: GameViewModel
) {

    val currentPlayer by viewModel.currentPlayer.observeAsState()

    BackButton(onClick = { navController.navigate("MenuScreen") })

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp),
        horizontalAlignment= Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {

        Spacer(modifier = modifier.height(1.dp))

        Row  {
            Box (
                modifier = modifier
                    .height(100.dp)
                    .width(150.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .border(
                        2.dp,
                        color = Color.LightGray,
                        shape = RoundedCornerShape(20.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Turn Player \n\n$currentPlayer",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(8.dp),
                    textAlign = TextAlign.Center,

                    )
            }
        }

        Box (
            contentAlignment = Alignment.Center
        ){
            //for (i in 0 until boardSize)
            board(size = boardSize, viewModel = viewModel, navController = navController)
        }

        RegameButton() {
            viewModel.regame(boardSize)
        }

        Spacer(modifier = modifier.height(100.dp))
    }

}


