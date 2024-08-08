package com.example.todolistapp

import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var editTextTask: EditText
    private lateinit var buttonAddTask: Button
    private lateinit var recyclerViewTasks: RecyclerView
    private val tasks = ArrayList<Task>()
    private lateinit var adapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // UI 요소 초기화
        editTextTask = findViewById(R.id.editTextTask)
        buttonAddTask = findViewById(R.id.buttonAddTask)
        recyclerViewTasks = findViewById(R.id.recyclerViewTasks)

        // 어댑터 설정
        adapter = TaskAdapter(this, tasks) { position ->
            showTaskOptionsDialog(position)
        }
        recyclerViewTasks.layoutManager = LinearLayoutManager(this)
        recyclerViewTasks.adapter = adapter

        // 추가 버튼 클릭 리스너
        buttonAddTask.setOnClickListener {
            val taskName = editTextTask.text.toString()
            Log.d("MainActivity", "Add button clicked") // 로그 추가
            if (taskName.isNotBlank()) {
                tasks.add(Task(name = taskName, isCompleted = false))  // Task 객체 추가, 초기 상태 지정
                adapter.notifyDataSetChanged()  // 어댑터에 변경사항 알림
                editTextTask.text.clear()  // 입력 필드 초기화
                Log.d("MainActivity", "Task added: $taskName") // 로그 추가
            } else {
                Toast.makeText(this, "Please enter a task", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Task 옵션 대화상자 표시(업데이트 및 삭제 기능)
    private fun showTaskOptionsDialog(position: Int) {
        Log.d("MainActivity", "showTaskOptionsDialog called for position: $position") // 로그 추가
        val options = arrayOf("업데이트", "삭제")

        AlertDialog.Builder(this)
            .setTitle("옵션 선택")
            .setItems(options) { _, which ->
                when (which) {
                    0 -> showUpdateTaskDialog(position) // 업데이트 선택 시
                    1 -> deleteTask(position) // 삭제 선택 시
                }
            }
            .show()
    }

    private fun showUpdateTaskDialog(position: Int) {
        Log.d("MainActivity", "showUpdateTaskDialog called for position: $position") // 로그 추가
        val builder = AlertDialog.Builder(this)
        builder.setTitle("할 일 업데이트")
        val input = EditText(this)
        input.setText(tasks[position].name) // 기존 Task 내용으로 초기화
        builder.setView(input)

        builder.setPositiveButton("업데이트") { dialog, _ ->
            val updatedTask = input.text.toString()
            if (updatedTask.isNotBlank()) {
                tasks[position].name = updatedTask
                adapter.notifyDataSetChanged()
                Toast.makeText(this, "Task updated", Toast.LENGTH_SHORT).show()
                Log.d("MainActivity", "Task updated: $updatedTask") // 로그 추가
            } else {
                Toast
                .makeText(this, "Task cannot be empty", Toast.LENGTH_SHORT).show()
            }
            dialog.dismiss()
        }
        builder.setNegativeButton("취소") { dialog, _ ->
            dialog.cancel()
        }
        builder.show()
    }

    private fun deleteTask(position: Int) {
        Log.d("MainActivity", "deleteTask called for position: $position") // 로그 추가
        AlertDialog.Builder(this)
            .setTitle("할 일 삭제")
            .setMessage("정말 삭제하시겠습니까?")
            .setPositiveButton("예") { _, _ ->
                tasks.removeAt(position)
                adapter.notifyDataSetChanged()
                Toast.makeText(this, "Task deleted", Toast.LENGTH_SHORT).show()
                Log.d("MainActivity", "Task deleted at position: $position") // 로그 추가
            }
            .setNegativeButton("아니오", null)
            .show()
    }
}
