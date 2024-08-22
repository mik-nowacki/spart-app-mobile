package com.example.spartapp.training.models

data class Program(
    var title: String = "title",
    var type: String = "type", // Skills, Strength, Stretching
    var duration: String = "1:10:00", // Total duration of a program
    var exercises: MutableList<Exercise>? = null
)
