package com.example.xogame.componrnt

import androidx.compose.foundation.border
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.xogame.model.model
import kotlinx.coroutines.delay

@Composable
fun ReplayBoard(
    modifier: Modifier = Modifier ,
    size: Int ,
    move: List<model.Move>,
    onCloseClicked: () -> Unit
) {

    val board = List(size) { MutableList(size) { "" } }
    var currentMoveIndex by remember { mutableStateOf(0) }
    val _boardSizeDP = if (size == 3) 100.dp else if (size == 5) 60.dp else 45.dp

    for (i in 0 until currentMoveIndex) {
        val moves = move[i]
        board[moves.row][moves.col] = moves.player
    }

    LaunchedEffect(Unit){
        while (currentMoveIndex < move.size){
            delay(1000)
            currentMoveIndex++
        }
    }

    Dialog(onDismissRequest = { /* Do nothing */ }){
        Surface (
            shape = RoundedCornerShape(16.dp),
            shadowElevation = 8.dp
        ){

            Column (
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){

                Spacer(modifier = modifier.height(10.dp))

                Text(
                    text = "Replay",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                )

                Spacer(modifier = modifier.height(10.dp))

                board.forEachIndexed { rowIndex, row ->
                    Row {
                        row.forEachIndexed { colIndex, cell ->
                            Box(
                                modifier = Modifier
                                    .size(_boardSizeDP)
                                    .border(1.dp, Color.DarkGray),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = cell,
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.align(Alignment.Center)
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = modifier.height(30.dp))

                Button(onClick = {
                    onCloseClicked()
                },colors = ButtonDefaults.buttonColors(Color.DarkGray)
                ) {
                    Text("Close")
                }

            }
        }
    }
}


