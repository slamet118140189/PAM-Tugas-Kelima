package com.example.tugas5

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.tugas5.databinding.FragmentHomeBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var auth: FirebaseAuth

    override fun onAttach(context: Context) {
        super.onAttach(context)
        sharedPreferences = context.getSharedPreferences("user_preferences", Context.MODE_PRIVATE)
        auth = FirebaseAuth.getInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment using View Binding
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        // Ambil email terakhir dari SharedPreferences
        val lastLoginEmail = sharedPreferences.getString("last_login_email", "")

        // Hapus karakter "@" dan seterusnya dari email terakhir
        val usernameWithoutAt = lastLoginEmail?.substringBefore('@') ?: ""

        // Tampilkan username di TextView atau elemen UI lainnya di dalam fragment
        binding.welcomeTextView.text = "Welcome back, $usernameWithoutAt"

        // Tangani klik pada tombol logout
        binding.logoutButton.setOnClickListener {
            logout()
        }

        return view
    }

    private fun logout() {
        // Lakukan proses logout
        auth.signOut()

        // Redirect ke halaman login setelah logout
        val intent = Intent(context, LoginActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }
}
