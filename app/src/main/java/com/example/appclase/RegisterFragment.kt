package com.example.appclase

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText

class RegisterFragment : Fragment() {

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

            // Call the function that handles nullability and registration
            processUserRegistration(username, email, etUsername, etEmail)
        }

        return view
    }

    /**
     * Requirement: Function with parameters, processes safe-null/null, 
     * and saves records in a collection.
     */
    private fun processUserRegistration(
        name: String?, 
        email: String?, 
        etName: TextInputEditText, 
        etMail: TextInputEditText
    ) {
        // Safe-null and null check inside UserManager.buildUser
        val userCandidate = UserManager.buildUser(name, email)

        if (userCandidate != null) {
            // Registering the user in the shared collection
            val result = UserManager.registerUser(userCandidate)
            
            result.onSuccess { message ->
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                etName.text?.clear()
                etMail.text?.clear()
            }.onFailure { error ->
                Toast.makeText(context, "Error: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(context, "El nombre de usuario es obligatorio", Toast.LENGTH_SHORT).show()
        }
    }
}
