package com.example.spartapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat.startActivity
import com.example.spartapp.training.TrainingActivity
import com.example.spartapp.ui.theme.SpartAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            trainingButton()
        }
    }
}

@Composable
fun trainingButton() {
    Column(
        modifier = Modifier.fillMaxWidth().fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val currentContext = LocalContext.current
        Button(onClick = {
            Toast.makeText(currentContext, "Entered training section", Toast.LENGTH_SHORT).show()
            val intent = Intent(currentContext, TrainingActivity::class.java)
            currentContext.startActivity(intent)
        }) {
            Text("Workout")
        }
    }
}




@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SpartAppTheme {

    }
}