package com.example.elearningapp.ui


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.elearningapp.R
import com.example.elearningapp.adapter.StudentAdapter
import com.example.elearningapp.database.AppDatabase
import com.example.elearningapp.entity.Student
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import androidx.appcompat.app.AlertDialog

class ManageStudentActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: StudentAdapter
    private var studentList = mutableListOf<Student>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_student)

        recyclerView = findViewById(R.id.recyclerViewStudents)
        recyclerView.layoutManager = LinearLayoutManager(this)

        loadStudents()
    }

    private fun loadStudents() {
        CoroutineScope(Dispatchers.IO).launch {
            val db = AppDatabase.getDatabase(this@ManageStudentActivity)
            studentList = db.studentDao().getAllStudents().toMutableList()

            withContext(Dispatchers.Main) {
                adapter = StudentAdapter(studentList,
                    { student -> deleteStudent(student) },
                    { student -> showUpdateDialog(student) }
                )
                recyclerView.adapter = adapter
            }
        }
    }

    private fun deleteStudent(student: Student) {
        CoroutineScope(Dispatchers.IO).launch {
            val db = AppDatabase.getDatabase(this@ManageStudentActivity)
            db.studentDao().deleteStudent(student)

            runOnUiThread {
                Toast.makeText(this@ManageStudentActivity, "Student Deleted", Toast.LENGTH_SHORT).show()
                loadStudents()
            }
        }
    }



    override fun onBackPressed() {
        super.onBackPressed()  // This will call the default back button behavior to finish the current activity and return to the previous one
    }

    @SuppressLint("MissingInflatedId")
    private fun showUpdateDialog(student: Student) {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_update_student, null)
        val etName = dialogView.findViewById<EditText>(R.id.etUpdateName)
        val etEmail = dialogView.findViewById<EditText>(R.id.etUpdateEmail)
        val etPassword = dialogView.findViewById<EditText>(R.id.etUpdatePassword)

        etName.setText(student.name)
        etEmail.setText(student.email)
        etPassword.setText(student.password)

        val dialog = AlertDialog.Builder(this)
            .setTitle("Update Student")
            .setView(dialogView)
            .setPositiveButton("Save") { _, _ ->
                val updatedName = etName.text.toString()
                val updatedEmail = etEmail.text.toString()
                val updatedPassword = etPassword.text.toString()

                val updatedStudent = Student(student.id, updatedName, updatedEmail, updatedPassword)
                updateStudent(updatedStudent)
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .create()

        dialog.show()
    }

    private fun updateStudent(student: Student) {
        CoroutineScope(Dispatchers.IO).launch {
            val db = AppDatabase.getDatabase(this@ManageStudentActivity)
            db.studentDao().updateStudent(student)

            withContext(Dispatchers.Main) {
                Toast.makeText(this@ManageStudentActivity, "Student Updated", Toast.LENGTH_SHORT).show()
                loadStudents()
            }
        }
    }

}



