package com.example.spartapp.training.activities

import android.os.Bundle
import android.text.Layout
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Label
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.spartapp.training.models.Program
import com.example.spartapp.training.ui.theme.SpartAppTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class CreateProgramActivity : ComponentActivity() {
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
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        UserInputs()
                    }
                }
            }
        }
    }


    private fun saveProgram(program: Program) = CoroutineScope(Dispatchers.IO).launch {
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
    fun UserInputTextField(
        value: String,
        onValueChange: (String) -> Unit,
        label: String
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(text = label) })

    }

    @Composable
    fun UserInputs() {
        var prTitle by remember { mutableStateOf("") }
        var prType by remember { mutableStateOf("") }
        var prDuration by remember { mutableStateOf("") }


        UserInputTextField(
            value = prTitle,
            onValueChange = { prTitle = it },
            label = "Name"
        )

        UserInputTextField(
            value = prType,
            onValueChange = { prType = it },
            label = "Type"
        )

        UserInputTextField(
            value = prDuration,
            onValueChange = { prDuration = it },
            label = "Duration"
        )

        androidx.compose.material3.Button(onClick = {
            saveProgram(
                Program(
                    title = prTitle,
                    type = prType,
                    duration = prDuration,
                    exercises = null
                )
            )
        }) {
            Text(text = "Save program")
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

}

@Composable
fun CreateWorkoutTitle() {
    Box(
        modifier = Modifier
            .width(240.dp)
            .height(40.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .padding(6.dp)
    ) {
        Text(
            "Create your workout",
            fontSize = 24.sp,
            color = MaterialTheme.colorScheme.onBackground
        )
    }


}

@Preview(
    showBackground = true,
    backgroundColor = 0xD3D3D3,
    widthDp = 412, heightDp = 915
)
@Composable
fun CreateProgramActivityPreview() {

        CreateWorkoutTitle()

}

