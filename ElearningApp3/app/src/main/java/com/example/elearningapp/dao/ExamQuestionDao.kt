package com.example.elearningapp.dao


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.elearningapp.entity.ExamQuestion

@Dao
interface ExamQuestionDao {

    @Insert
    fun insert(question: ExamQuestion)

    @Query("SELECT * FROM exam_questions")
    fun getAllQuestions(): List<ExamQuestion>
}
