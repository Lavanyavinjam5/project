package com.example.elearningapp.ui


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.elearningapp.R

class DashboardActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val btnAddCourse = findViewById<Button>(R.id.btnAddCourse)
        val btnManageCourse = findViewById<Button>(R.id.btnManageCourse)
        val btnAddStudent = findViewById<Button>(R.id.btnAddStudent)
        val btnManageStudent = findViewById<Button>(R.id.btnManageStudent)
        val btnAddExam = findViewById<Button>(R.id.btnAddExam)
        val btnLogout = findViewById<Button>(R.id.logout)

        btnAddCourse.setOnClickListener {
            startActivity(Intent(this, AddCourseActivity::class.java))
        }
        btnManageCourse.setOnClickListener {
            startActivity(Intent(this, ManageCourseActivity::class.java))
        }
        btnAddStudent.setOnClickListener {
            startActivity(Intent(this, AddStudentActivity::class.java))
        }
        btnManageStudent.setOnClickListener {
            startActivity(Intent(this, ManageStudentActivity::class.java))
        }
        btnAddExam.setOnClickListener {
            startActivity(Intent(this, AddExamQuestionActivity::class.java))
        }
        btnLogout.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}