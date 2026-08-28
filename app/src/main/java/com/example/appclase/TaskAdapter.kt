package com.example.appclase

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class TaskAdapter(
    private val onTaskChecked: (Task, Boolean) -> Unit,
    private val onEditClick: (Task) -> Unit,
    private val onDeleteClick: (Task) -> Unit
) : ListAdapter<Task, TaskAdapter.TaskViewHolder>(TaskDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = getItem(position)
        holder.bind(task, onTaskChecked, onEditClick, onDeleteClick)
    }

    class TaskViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val cbCompleted: CheckBox = view.findViewById(R.id.cbCompleted)
        private val tvTitle: TextView = view.findViewById(R.id.tvTitle)
        private val tvDescription: TextView = view.findViewById(R.id.tvDescription)
        private val btnEdit: ImageButton = view.findViewById(R.id.btnEdit)
        private val btnDelete: ImageButton = view.findViewById(R.id.btnDelete)

        fun bind(
            task: Task,
            onTaskChecked: (Task, Boolean) -> Unit,
            onEditClick: (Task) -> Unit,
            onDeleteClick: (Task) -> Unit
        ) {
            tvTitle.text = task.title
            tvDescription.text = task.description
            cbCompleted.isChecked = task.isCompleted

            cbCompleted.setOnCheckedChangeListener { _, isChecked ->
                onTaskChecked(task, isChecked)
            }

            btnEdit.setOnClickListener { onEditClick(task) }
            btnDelete.setOnClickListener { onDeleteClick(task) }
        }
    }

    class TaskDiffCallback : DiffUtil.ItemCallback<Task>() {
        override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean = oldItem == newItem
    }
}