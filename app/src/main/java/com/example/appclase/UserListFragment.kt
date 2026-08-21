package com.example.appclase

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class UserListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_user_list, container, false)

        val rvUsers = view.findViewById<RecyclerView>(R.id.rvUsers)
        rvUsers.layoutManager = LinearLayoutManager(context)

        // Requirement 2: Using the shared collection from UserManager
        val rawUsers = UserManager.getAllUsers()

        // Requirement 5: Applying a higher-order function (lambda) to transform data
        // Here we ensure all names are trimmed and the first letter is capitalized
        val processedUsers = rawUsers.map { user ->
            User(
                username = user.username.trim().capitalize(),
                email = user.email.trim().lowercase()
            )
        }

        rvUsers.adapter = UserAdapter(processedUsers)

        return view
    }
}
