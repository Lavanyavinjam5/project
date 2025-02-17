package com.example.elearningapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.elearningapp.R
import com.example.elearningapp.entity.Student

class StudentAdapter( private val studentList: List<Student>, private val onDeleteClick: (Student) -> Unit, private val onUpdateClick: (Student) -> Unit ) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.tvStudentName)
        val email: TextView = itemView.findViewById(R.id.tvStudentEmail)
        val btnDelete: Button = itemView.findViewById(R.id.btnDeleteStudent)
        val btnUpdate: Button = itemView.findViewById(R.id.btnUpdateStudent)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_student, parent, false)
        return StudentViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val currentStudent = studentList[position]
        holder.name.text = currentStudent.name
        holder.email.text = currentStudent.email

        holder.btnDelete.setOnClickListener {
            onDeleteClick(currentStudent)
        }
        holder.btnUpdate.setOnClickListener {
            onUpdateClick(currentStudent)
        }
    }

    override fun getItemCount(): Int {
        return studentList.size
    }
}