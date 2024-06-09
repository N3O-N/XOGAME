package com.example.xogame.componrnt

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.example.xogame.viewModel.GameViewModel


@Composable
fun board(
    modifier: Modifier = Modifier,
    size: Int ,
    viewModel: GameViewModel,
    navController: NavController
){

    /*var board by remember { mutableStateOf(viewModel.createGameBoard(size)) }
    var currentPlayer by remember { mutableStateOf("X") }
    var winner by remember { mutableStateOf<String?>(null) }*/

    val board by viewModel.board.observeAsState()
    val winner by viewModel.winner.observeAsState()

    val _boardSizeDP = if (size == 3) 100.dp else if (size == 5) 60.dp else 45.dp

    Column (horizontalAlignment = Alignment.CenterHorizontally){

        board?.cells?.forEachIndexed { rowIndex, row ->
            Row {
                row.forEachIndexed { colIndex , cell ->
                    Box(
                        modifier = Modifier
                            .size(_boardSizeDP)
                            .border(1.dp, Color.DarkGray)
                            .clickable(enabled = cell.value.isEmpty() && winner == null) {
                                viewModel.playerMove(rowIndex, colIndex, size)
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        //แสดง O X ใน grid
                        Text(
                            text = cell.value,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }

        if (winner != null || board?.cells?.all { it.all { it.value.isNotEmpty() } } == true){
            ResultGamePopup(navController = navController, viewModel = viewModel, boardSize = size, winner = winner)
        }

    }

}

