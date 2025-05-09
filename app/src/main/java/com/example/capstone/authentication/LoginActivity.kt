package com.example.capstone.authentication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.capstone.R
import com.example.capstone.customer.CustomerMainActivity
import com.example.capstone.data.models.LoginRequest
import com.example.capstone.repository.UserRepository
import com.example.capstone.viewmodel.UserViewModel
import com.example.capstone.viewmodel.UserViewModelFactory

class LoginActivity : AppCompatActivity() {

    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.authentication_activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val factory = UserViewModelFactory(UserRepository())
        userViewModel = ViewModelProvider(this, factory)[UserViewModel::class.java]


        val emailaddressinput = findViewById<EditText>(R.id.emailAddET)
        val passwordinput = findViewById<EditText>(R.id.passET)
        val loginButton = findViewById<Button>(R.id.loginBtn)
        val signup = findViewById<TextView>(R.id.signupTv)
        val forgotpass = findViewById<TextView>(R.id.forgotpassTv)


        signup.setOnClickListener{
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        forgotpass.setOnClickListener{
            val intent = Intent(this, PassRecoveryActivity::class.java)
            startActivity(intent)
        }

        loginButton.setOnClickListener {
            val email = emailaddressinput.text.toString().trim()
            val password = passwordinput.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                val loginRequest = LoginRequest(email, password)

                userViewModel.loginUser(loginRequest).observe(this) { response ->
                    Log.d("LoginResponse", "Received: $response")
                    if (response != null) {
                        Log.d("LoginResponse", "Token: ${response.token}")
                        val token = response.token  // <- this must match your actual data class
                        if (!token.isNullOrEmpty()) {
                            saveAuthToken(token)
                            Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this, CustomerMainActivity::class.java))
                            finish()
                        } else {
                            Toast.makeText(this, "Login failed: token missing.", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(this, "Login failed: invalid credentials.", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                // Handle case where email or password is empty
                Toast.makeText(this, "Please enter both email and password", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun saveAuthToken(token: String?) {
        val sharedPref = getSharedPreferences("MyAppPrefs", MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putString("auth_token", token)
        editor.apply()
    }

}