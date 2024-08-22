package com.example.spartapp.training.models

data class Exercise(
    var name: String = "name",
    var description: String = "description",
    var numberOfSets: String = "3", // TODO: String to Int
    var restBetweenSets: String = "120", // in seconds
    var repsPerSet: String = "5" // TODO: String to Int -> later instead of <numberOfSets>
)
