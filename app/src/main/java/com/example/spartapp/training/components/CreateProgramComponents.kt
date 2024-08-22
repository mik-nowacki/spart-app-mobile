package com.example.spartapp.training.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(
    showBackground = true,
    backgroundColor = 0xD3D3D3,
    widthDp = 412, heightDp = 915
)
@Composable
fun CreateProgramActivityPreview() {

    Column(
        modifier = Modifier.padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CreateWorkoutTitle()
        Spacer(modifier = Modifier.height(32.dp))
        UserInputTextField(value = "", onValueChange = {}, label = "Title")
        Spacer(modifier = Modifier.height(32.dp))
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TypeButton(type = "Skills")
            TypeButton(type = "Strength")
            TypeButton(type = "Stretching")
        }
        AddExerciseButton()
        ExerciseListBox()
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
fun TypeButton(
    type: String

) {
    Button(onClick = { /*TODO*/ }) {
        Text(text = type)
    }
}

@Composable
fun AddExerciseButton(

) {
    Button(onClick = { /*TODO*/ }) {
        Text(text = "Add exercise")
    }
}

@Composable
fun ExerciseListBox(

) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(15.dp))
            .background(color = Color.Gray)
            .padding(24.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            ExerciseBox()
            ExerciseBox()
            ExerciseBox()

        }
    }
}

@Composable
fun ExerciseBox(

) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(color = Color.LightGray)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize().padding(horizontal = 0.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly

        ) {
            Text(text = "Exercise 1")
            Text(text = "Edit")
            Text(text = "Delete")
        }
    }
}
