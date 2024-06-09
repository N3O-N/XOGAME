package com.example.xogame.componrnt

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.xogame.viewModel.GameViewModel

@Composable
fun RegameButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier.padding(start = 10.dp , top = 10.dp),
        horizontalArrangement= Arrangement.Start,
    ) {
        IconButton(
            onClick = { onClick() },
            modifier = modifier.size(50.dp)
                .shadow(
                    2.dp,
                    shape = RoundedCornerShape(20.dp)
                ).background(Color.Gray),

            ) {
            Icon(
                Icons.Filled.Refresh ,
                contentDescription = "Refresh",
                tint = Color.White)
        }
    }
}