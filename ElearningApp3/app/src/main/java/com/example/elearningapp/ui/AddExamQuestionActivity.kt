package com.example.elearningapp.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.elearningapp.R
import com.example.elearningapp.database.AppDatabase
import com.example.elearningapp.entity.ExamQuestion
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddExamQuestionActivity : AppCompatActivity() {

    private lateinit var database: AppDatabase

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_exam_question)


        database = AppDatabase.getDatabase(this)

        // Find views
        val etQuestion = findViewById<EditText>(R.id.etQuestion)
        val etChoice1 = findViewById<EditText>(R.id.etChoice1)
        val etChoice2 = findViewById<EditText>(R.id.etChoice2)
        val etChoice3 = findViewById<EditText>(R.id.eChoice3)
        val etCorrectAnswer = findViewById<EditText>(R.id.etCorrectAnswer)
        val btnSaveQuestion = findViewById<Button>(R.id.btnSaveQuestion)
        val btnBack = findViewById<Button>(R.id.btnBack)

        // Save question button click listener
        btnSaveQuestion.setOnClickListener {
            val question = etQuestion.text.toString()
            val choice1 = etChoice1.text.toString()
            val choice2 = etChoice2.text.toString()
            val choice3 = etChoice3.text.toString()

            val correctAnswer = etCorrectAnswer.text.toString()

            if (question.isNotEmpty() && choice1.isNotEmpty() && choice2.isNotEmpty() && choice3.isNotEmpty() && correctAnswer.isNotEmpty()) {
                // Create an ExamQuestion object
                val examQuestion = ExamQuestion(
                    question = question,
                    choice1  = choice1,
                    choice2  = choice2,
                    choice3 = choice3,
                    correctAnswer = correctAnswer
                )

                // Save the question to the database using coroutines
                CoroutineScope(Dispatchers.IO).launch {
                    database.examQuestionDao().insert(examQuestion)

                    // Show success message on the main thread
                    runOnUiThread {
                        Toast.makeText(this@AddExamQuestionActivity, "Question saved successfully!", Toast.LENGTH_SHORT).show()
                    }
                }

                // Clear input fields
                etQuestion.text.clear()
                etChoice1.text.clear()
                etChoice2.text.clear()
                etChoice3.text.clear()
                etCorrectAnswer.text.clear()
            } else {
                // Show error message if any field is empty
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }
        btnBack.setOnClickListener {
            val intent = Intent(this, DashboardActivity::class.java)  // Navigate to DashboardActivity
            startActivity(intent)
            finish()  // Optionally, finish the current activity
        }
    }

}
