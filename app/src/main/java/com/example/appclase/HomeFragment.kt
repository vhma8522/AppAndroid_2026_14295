package com.example.appclase

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class HomeFragment : Fragment() {

    // Requirement: Using the interface implementation
    private val welcomeDisplay: WelcomeDisplay = WelcomeFactory.provideDefaultWelcome()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val tvTitle = view.findViewById<TextView>(R.id.tvWelcomeTitle)
        val tvSub = view.findViewById<TextView>(R.id.tvWelcomeSub)

        // Using interface methods
        tvTitle.text = welcomeDisplay.getWelcomeTitle()
        tvSub.text = welcomeDisplay.getWelcomeSubTitle()

        return view
    }
}
