package com.example.spartapp.training.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.spartapp.training.components.BottomNavigationBar
import com.example.spartapp.training.models.Program
import com.example.spartapp.training.ui.theme.SpartAppTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class TrainingActivity : ComponentActivity() {
    private val programCollectionRef = Firebase.firestore.collection("programs")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SpartAppTheme {
                SetBarColor(color = MaterialTheme.colorScheme.background)
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HomeScreen()
                }
            }
        }

    }

    @Composable
    private fun SetBarColor(color: Color) {
        val systemUiController = rememberSystemUiController()
        SideEffect {
            systemUiController.setSystemBarsColor(
                color = color
            )
        }
    }

    @Composable
    private fun HomeScreen() {
        Scaffold(
            bottomBar = { BottomNavigationBar() }
        ) { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                CreateProgramButton()
                UserPrograms()
            }
        }
    }


    private suspend fun getPrograms(): String {
        return try {
            val querySnapshot = programCollectionRef.get().await()
            val sb = StringBuilder()
            for (document in querySnapshot.documents) {
                val program = document.toObject<Program>()
                sb.append("$program\n")
            }
            sb.toString()
        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                Toast.makeText(this@TrainingActivity, e.message, Toast.LENGTH_LONG).show()
            }
            "failed"
        }
    }

    @Composable
    fun UserPrograms() {
        val coroutineScope = rememberCoroutineScope()
        val programs = remember { mutableStateOf("hello") }

        val getProgramOnClick: () -> Unit = {
            coroutineScope.launch {
                programs.value = getPrograms()
            }
        }

        androidx.compose.material3.Button(onClick = getProgramOnClick) {
            Text(text = "Get programs")
        }
        Text(text = programs.value)

    }

    @Composable
    fun CreateProgramButton() {
        val currentContext = LocalContext.current
        androidx.compose.material3.Button(onClick = {
            Toast.makeText(currentContext, "Creating new program!", Toast.LENGTH_SHORT).show()
            val intent = Intent(currentContext, CreateProgramActivity::class.java)
            currentContext.startActivity(intent)
        }) {
            Text("Create new program")
        }
    }
}

