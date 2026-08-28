package com.example.appclase

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.launch

class TasksFragment : Fragment() {

    private val viewModel: TaskViewModel by viewModels {
        val database = AppDatabase.getDatabase(requireContext())
        TaskViewModelFactory(TaskRepository(database.taskDao()))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_tasks, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.rvTasks)
        val adapter = TaskAdapter(
            onTaskChecked = { task, isChecked ->
                viewModel.update(task.copy(isCompleted = isChecked))
            },
            onEditClick = { task ->
                showTaskDialog(task)
            },
            onDeleteClick = { task ->
                viewModel.delete(task)
            }
        )

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        lifecycleScope.launch {
            viewModel.allTasks.collect { tasks ->
                adapter.submitList(tasks)
            }
        }

        view.findViewById<FloatingActionButton>(R.id.fabAddTask).setOnClickListener {
            showTaskDialog(null)
        }

        return view
    }

    private fun showTaskDialog(task: Task?) {
        val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.fragment_task_edit, null)
        val etTitle = dialogView.findViewById<EditText>(R.id.etTitle)
        val etDescription = dialogView.findViewById<EditText>(R.id.etDescription)
        val btnSave = dialogView.findViewById<Button>(R.id.btnSave)

        if (task != null) {
            etTitle.setText(task.title)
            etDescription.setText(task.description)
            btnSave.text = "Actualizar"
        }

        val dialog = AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .setTitle(if (task == null) "Nueva Tarea" else "Editar Tarea")
            .create()

        btnSave.setOnClickListener {
            val title = etTitle.text.toString()
            val description = etDescription.text.toString()

            if (title.isNotBlank()) {
                if (task == null) {
                    viewModel.insert(title, description)
                } else {
                    viewModel.update(task.copy(title = title, description = description))
                }
                dialog.dismiss()
            } else {
                etTitle.error = "El título es obligatorio"
            }
        }

        dialog.show()
    }
}