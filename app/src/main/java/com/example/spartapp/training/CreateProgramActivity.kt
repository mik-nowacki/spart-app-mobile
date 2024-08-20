package com.example.spartapp.training

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.spartapp.training.ui.theme.SpartAppTheme
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class CreateProgramActivity : ComponentActivity() {
    val programCollectionRef = Firebase.firestore.collection("programs")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SpartAppTheme {
                userInputs()
            }
        }
    }

    fun SaveProgram(program: Program) = CoroutineScope(Dispatchers.IO).launch {
        try {
            programCollectionRef.add(program).await()
            withContext(Dispatchers.Main) {
                Toast.makeText(
                    this@CreateProgramActivity,
                    "Data uploaded successfully",
                    Toast.LENGTH_SHORT
                ).show()
            }
        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                Toast.makeText(this@CreateProgramActivity, e.message, Toast.LENGTH_LONG).show()
            }
        }
    }

    @Composable
    fun userInputs() {
        var prName by remember { mutableStateOf("") }
        var prType by remember { mutableStateOf("") }
        var prDuration by remember { mutableStateOf("") }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            OutlinedTextField(
                value = prName,
                onValueChange = { prName = it },
                label = { Text("Name") })

            OutlinedTextField(
                value = prType,
                onValueChange = { prType = it },
                label = { Text("Type") })

            OutlinedTextField(
                value = prDuration,
                onValueChange = { prDuration = it },
                label = { Text("Duration") })

            androidx.compose.material3.Button(onClick = {
                SaveProgram(Program(name = prName, type = prType, duration = prDuration))
            }) {
                Text(text = "Save program")
            }
        }
    }
}