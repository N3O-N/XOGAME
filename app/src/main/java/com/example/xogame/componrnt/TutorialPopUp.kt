package com.example.xogame.componrnt

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
fun TutorialPopUp(modifier: Modifier = Modifier, onCloseClicked: () -> Unit) {
    Dialog(onDismissRequest = { /* Do nothing */ }){
        Surface (
            shape = RoundedCornerShape(16.dp),
            shadowElevation = 8.dp
        ){

            Column(
                modifier =modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(text = "How to Play",
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,)

                Spacer(modifier = modifier.height(10.dp))

                Text(
                    text = "First players choose the size of the game board \n\t board size have 3x3 5x5 7x7 \nafter choose enjoin your game \n\nWinning condition: \n\t Board 3x3: player must line up 3 of same symbols, either vertically, horizontally or diagonally to win \n\t Board 5x5 or 7x7: player must line up 4 of same symbols, either vertically, horizontally or diagonally to win\n",
                    textAlign = TextAlign.Start,
                    fontSize = 16.sp,
                )

                Text(
                    text = "GameHistory: \n\tPlayer can watch the game replay at GameHistory",
                    textAlign = TextAlign.Start,
                    fontSize = 16.sp,
                )

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