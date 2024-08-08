package com.example.todolistapp

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TaskAdapter(
    private val context: Context,
    private val tasks: ArrayList<Task>,
    private val onLongClick: (Int) -> Unit
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val taskName: TextView = itemView.findViewById(R.id.textViewTask)
        val checkBox: CheckBox = itemView.findViewById(R.id.checkBoxTask)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.task_item, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]
        holder.taskName.text = task.name
        holder.checkBox.isChecked = task.isCompleted

        // 체크박스 상태에 따른 텍스트 스타일 변경
        updateTaskStyle(holder.taskName, task.isCompleted)

        holder.checkBox.setOnCheckedChangeListener { _, isChecked ->
            task.isCompleted = isChecked
            updateTaskStyle(holder.taskName, isChecked)
        }

        holder.itemView.setOnLongClickListener {
            onLongClick(position)
            true
        }
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    private fun updateTaskStyle(textView: TextView, isCompleted: Boolean) {
        if (isCompleted) {
            textView.paintFlags = textView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            textView.setTextColor(context.getColor(android.R.color.darker_gray))
        } else {
            textView.paintFlags = textView.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
            textView.setTextColor(context.getColor(android.R.color.black))
        }
    }
}
