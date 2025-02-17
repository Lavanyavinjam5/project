package com.example.elearningapp.dao
import androidx.room.*
import com.example.elearningapp.entity.Student

@Dao
interface StudentDao {
    @Insert
    fun insertStudent(student: Student)

    @Query("SELECT * FROM students")
    fun getAllStudents(): List<Student>

    @Delete
    fun deleteStudent(student: Student)

    @Update
    fun updateStudent(student: Student)
}