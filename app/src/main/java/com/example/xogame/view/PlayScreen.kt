package com.example.xogame.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.xogame.componrnt.BackButton
import com.example.xogame.componrnt.board
import com.example.xogame.viewModel.GameViewModel

@Composable
fun PlayScreen(
    modifier: Modifier = Modifier ,
    navController: NavController,
    boardSize: Int ,
    viewModel: GameViewModel
) {

    BackButton(onClick = { navController.navigate("MenuScreen") })

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp),
        horizontalAlignment= Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Spacer(modifier = modifier.height(1.dp))

        Box (
            modifier = modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .clip(RoundedCornerShape(20.dp))
                ,
            contentAlignment = Alignment.Center
        ){
            //for (i in 0 until boardSize)
            board(size = boardSize, viewModel = viewModel, navController = navController)
        }

        Spacer(modifier = modifier.height(10.dp))
    }

}


