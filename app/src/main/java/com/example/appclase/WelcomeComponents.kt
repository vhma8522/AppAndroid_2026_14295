package com.example.appclase

/**
 * Requirement 4: Interface, Class, and Object implementation.
 */

// Simple Interface
interface WelcomeDisplay {
    fun getWelcomeTitle(): String
    fun getWelcomeSubTitle(): String
}

// Class implementing the interface
class AppWelcome(private val appName: String) : WelcomeDisplay {
    override fun getWelcomeTitle(): String = "Bienvenido a $appName"
    override fun getWelcomeSubTitle(): String = "La mejor plataforma para gestionar tus usuarios."
}

// Object (Singleton) to provide the implementation
object WelcomeFactory {
    fun provideDefaultWelcome(): WelcomeDisplay {
        return AppWelcome("AppClase")
    }
}