package com.example.elearningapp.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.elearningapp.R
import com.example.elearningapp.database.AppDatabase
import com.example.elearningapp.entity.Course
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddCourseActivity : AppCompatActivity() {

    private var selectedPdfUri: Uri? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_course)

        val etTitle = findViewById<EditText>(R.id.etCourseTitle)
        val etDescription = findViewById<EditText>(R.id.etCourseDescription)
        val btnUploadPDF = findViewById<Button>(R.id.btnUploadPDF)
        val btnAddCourse = findViewById<Button>(R.id.btnAddCourse)
        val btnBack = findViewById<Button>(R.id.btnBack)

        // Open file chooser to select PDF
        btnUploadPDF.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "application/pdf" // Only allow PDF files
            startActivityForResult(intent, 100)  // 100 is the request code
        }

        // Add course with title, description, and PDF URI
        btnAddCourse.setOnClickListener {
            val title = etTitle.text.toString().trim()
            val description = etDescription.text.toString().trim()

            if (title.isNotEmpty() && description.isNotEmpty() && selectedPdfUri != null) {
                lifecycleScope.launch(Dispatchers.IO) {
                    val db = AppDatabase.getDatabase(this@AddCourseActivity)
                    db.courseDao().insertCourse(
                        Course(
                            title = title,
                            description = description,
                            filePath = selectedPdfUri.toString()  // Save the selected PDF URI
                        )
                    )

                    runOnUiThread {
                        Toast.makeText(this@AddCourseActivity, "Course Added!", Toast.LENGTH_SHORT)
                            .show()
                        finish()  // Optionally close the activity after saving
                    }
                }
            } else {
                Toast.makeText(this, "Fill all fields and upload a PDF!", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        // Navigate back to the DashboardActivity
        btnBack.setOnClickListener {
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
            finish()  // Optionally, finish the current activity
        }
    }

    // Handle PDF selection result
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && resultCode == Activity.RESULT_OK) {
            selectedPdfUri = data?.data  // Store the selected PDF URI
            Toast.makeText(this, "PDF Selected: ${selectedPdfUri?.path}", Toast.LENGTH_SHORT)
                .show()
        }
    }
}
