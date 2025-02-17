package com.example.elearningapp.database
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.elearningapp.dao.AdminDao
import com.example.elearningapp.dao.CourseDao
import com.example.elearningapp.dao.ExamQuestionDao
import com.example.elearningapp.dao.StudentDao
import com.example.elearningapp.entity.Admin
import com.example.elearningapp.entity.Course
import com.example.elearningapp.entity.ExamQuestion
import com.example.elearningapp.entity.Student

@Database(entities = [Admin::class, Course::class, Student::class, ExamQuestion::class], version = 2)
abstract class AppDatabase : RoomDatabase() {

    abstract fun adminDao(): AdminDao
    abstract fun courseDao(): CourseDao
    abstract fun studentDao(): StudentDao
    abstract fun examQuestionDao(): ExamQuestionDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "elearning_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }
}