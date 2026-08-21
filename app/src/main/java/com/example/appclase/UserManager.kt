package com.example.appclase

import java.util.regex.Pattern

/**
 * Singleton object to manage user storage and operations.
 * Demonstrates: Collections, Functions with parameters/returns, Null safety.
 */
object UserManager {
    private val users = mutableListOf<User>()

    // Requirement 1 & 3: Function with parameters and null safety
    fun buildUser(name: String?, email: String?): User? {
        // Use of null safety (Safe call and Elvis operator)
        val safeName = name ?: "Usuario Anónimo"
        val safeEmail = email ?: "sin@correo.com"
        
        return if (safeName.isNotBlank()) {
            User(safeName, safeEmail)
        } else {
            null
        }
    }

    // Requirement 1 & 3: Function with parameters, return, and Exception Handling
    fun registerUser(user: User?): Result<String> {
        return try {
            // Null safety check
            val nonNullUser = user ?: throw IllegalArgumentException("El usuario no puede ser nulo")
            
            if (!isValidEmail(nonNullUser.email)) {
                throw Exception("Formato de correo inválido")
            }
            
            // Requirement 2: Using a collection (List)
            users.add(nonNullUser)
            Result.success("Usuario ${nonNullUser.username} registrado con éxito")
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun getAllUsers(): List<User> = users

    // Initial dummy data using the internal functions
    init {
        val u1 = buildUser("Ana García", "ana@gmail.com")
        val u2 = buildUser("Juan Pérez", "juan@outlook.com")
        registerUser(u1)
        registerUser(u2)
    }

    private fun isValidEmail(email: String): Boolean {
        val emailPattern = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"
        return Pattern.compile(emailPattern).matcher(email).matches()
    }
}