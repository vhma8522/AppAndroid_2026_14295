package com.example.appclase

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText

class RefisterAlterFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_register, container, false)

        val etUsername = view.findViewById<TextInputEditText>(R.id.etUsername)
        val etEmail = view.findViewById<TextInputEditText>(R.id.etEmail)
        val btnRegister = view.findViewById<Button>(R.id.btnRegister)

        btnRegister.setOnClickListener {
            // Processing safe-null from the EditText
            val username: String? = etUsername.text?.toString()
            val email: String? = etEmail.text?.toString()
        }

        return view
    }
}