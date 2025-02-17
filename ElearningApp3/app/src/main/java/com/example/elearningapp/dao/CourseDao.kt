package com.example.elearningapp.dao

import androidx.room.*
import com.example.elearningapp.entity.Course

@Dao
interface CourseDao {
    @Insert
    fun insertCourse(course: Course)

    @Query("SELECT * FROM courses")
    fun getAllCourses(): List<Course>

    @Delete
    fun deleteCourse(course: Course)
}