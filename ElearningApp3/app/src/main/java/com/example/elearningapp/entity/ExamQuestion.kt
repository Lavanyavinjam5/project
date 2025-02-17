package com.example.elearningapp.entity


import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "exam_questions")
data class ExamQuestion(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val question: String,
    val choice1: String,
    val choice2: String,
    val choice3: String,

    val correctAnswer: String
)