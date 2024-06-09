package com.example.xogame.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.xogame.ui.theme.XOGAMETheme
import com.example.xogame.viewModel.GameViewModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : ComponentActivity() {

    private val viewModel: GameViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {

        val database = FirebaseDatabase.getInstance()
        val gameHistoryRef = database.getReference("game history")
        gameHistoryRef.setValue(null)

        super.onCreate(savedInstanceState)
        setContent {
            XOGAMETheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,

                ) {
                    AppNavigation(viewModel)
                }
            }
        }
    }
}

@Preview
@Composable
fun MenuScreenPreview() {
    val navController = rememberNavController()
    XOGAMETheme {
        MenuScreen(title = "XO GAME!", navController = navController)
    }
}
