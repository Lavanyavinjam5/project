package com.example.elearningapp.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

import com.example.elearningapp.R
import com.example.elearningapp.database.AppDatabase
import com.example.elearningapp.entity.Student
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddStudentActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)

        val etName = findViewById<EditText>(R.id.etStudentName)
        val btnBack = findViewById<Button>(R.id.btnBack)

        val etEmail = findViewById<EditText>(R.id.etStudentEmail)
        val etPassword = findViewById<EditText>(R.id.etStudentPassword)
        val btnAddStudent = findViewById<Button>(R.id.btnAddStudent)

        btnAddStudent.setOnClickListener {
            val name = etName.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
                CoroutineScope(Dispatchers.IO).launch {
                    val db = AppDatabase.getDatabase(this@AddStudentActivity)
                    db.studentDao().insertStudent(Student(name = name, email = email, password = password))

                    runOnUiThread {
                        Toast.makeText(this@AddStudentActivity, "Student Added!", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                }
            } else {
                Toast.makeText(this, "Fill all fields!", Toast.LENGTH_SHORT).show()
            }
        }
        btnBack.setOnClickListener {
            val intent = Intent(this, DashboardActivity::class.java)  // Navigate to DashboardActivity
            startActivity(intent)
            finish()  // Optionally, finish the current activity
        }
    }
}