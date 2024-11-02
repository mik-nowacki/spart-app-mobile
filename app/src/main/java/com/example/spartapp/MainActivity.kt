package com.example.spartapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.spartapp.diet.activities.DietActivity
import com.example.spartapp.training.activities.TrainingActivity
import com.example.spartapp.ui.theme.SpartAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SpartAppTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val currentContext = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            Toast.makeText(currentContext, "Entered training section", Toast.LENGTH_SHORT).show()
            currentContext.startActivity(Intent(currentContext, TrainingActivity::class.java))
        }) {
            Text("Workout")
        }

        Button(onClick = {
            Toast.makeText(currentContext, "Entered diet section", Toast.LENGTH_SHORT).show()
            currentContext.startActivity(Intent(currentContext, DietActivity::class.java))
        }) {
            Text("Diet")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SpartAppTheme {
        MainScreen()
    }
}
