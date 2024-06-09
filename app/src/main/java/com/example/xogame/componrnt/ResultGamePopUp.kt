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
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.example.xogame.model.model
import com.example.xogame.viewModel.GameViewModel

@Composable
fun ResultGamePopup(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: GameViewModel,
    boardSize: Int,
    winner: String?
) {

        Dialog(onDismissRequest = { /* Do nothing */ }){
            Surface (
                shape = RoundedCornerShape(16.dp),
                shadowElevation = 8.dp
            ){
                Column(
                    modifier =modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (winner != null) {
                        Text(text = "The Winner Is",
                            textAlign = TextAlign.Center,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,)
                        Spacer(modifier = modifier.height(20.dp))
                        Text(text = "$winner",
                            textAlign = TextAlign.Center,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,)
                    } else {
                        Text(text = "It's a draw!",
                            textAlign = TextAlign.Center,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,)
                    }
                    Spacer(modifier = modifier.height(30.dp))
                    Button(onClick = {
                        viewModel.regame(boardSize)
                    },colors = ButtonDefaults.buttonColors(Color.DarkGray)
                    ) {
                        Text("Play Again")
                    }
                    Spacer(modifier = modifier.height(10.dp))
                    Button(onClick = {
                        viewModel.regame(boardSize)
                        navController.navigate("MenuScreen")
                    },colors = ButtonDefaults.buttonColors(Color.DarkGray)
                    ) {
                        Text("Quit")
                    }
                }
            }
        }

}