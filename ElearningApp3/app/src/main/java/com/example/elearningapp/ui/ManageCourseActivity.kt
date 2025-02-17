package com.example.elearningapp.ui

import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.elearningapp.R
import com.example.elearningapp.adapter.CourseAdapter
import com.example.elearningapp.database.AppDatabase
import com.example.elearningapp.entity.Course
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ManageCourseActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CourseAdapter
    private var courseList = mutableListOf<Course>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_course)

        recyclerView = findViewById(R.id.recyclerViewCourses)
        recyclerView.layoutManager = LinearLayoutManager(this)

        loadCourses()
    }

    private fun loadCourses() {
        CoroutineScope(Dispatchers.IO).launch {
            val db = AppDatabase.getDatabase(this@ManageCourseActivity)
            courseList = db.courseDao().getAllCourses().toMutableList()

            runOnUiThread {
                adapter = CourseAdapter(courseList) { course ->
                    deleteCourse(course)
                }
                recyclerView.adapter = adapter
            }
        }
    }

    private fun deleteCourse(course: Course) {
        CoroutineScope(Dispatchers.IO).launch {
            val db = AppDatabase.getDatabase(this@ManageCourseActivity)
            db.courseDao().deleteCourse(course)

            runOnUiThread {
                Toast.makeText(this@ManageCourseActivity, "Course Deleted", Toast.LENGTH_SHORT).show()
                loadCourses()  // Refresh the course list after deletion
            }
        }
    }
}
