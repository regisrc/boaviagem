package com.example.boaviagem.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.*
import androidx.lifecycle.lifecycleScope

import com.example.boaviagem.R
import com.example.boaviagem.ui.register.RegisterActivity
import com.example.boaviagem.controller.UsuarioController
import com.example.boaviagem.ui.home.HomeActivity
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        val username = findViewById<EditText>(R.id.username)
        val password = findViewById<EditText>(R.id.password)
        val login = findViewById<Button>(R.id.login)
        val registerButton = findViewById<TextView>(R.id.registerButton)

        val usuarioController = UsuarioController(applicationContext)

        registerButton.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        login.setOnClickListener {
            lifecycleScope.launch {
                val usuario = usuarioController.logar(username.text.toString(), password.text.toString())

                if (usuario != null) {
                    val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                    intent.putExtra("id", usuario.id)
                    startActivity(intent)
                    finish()
                }
                else
                    Toast.makeText(this@LoginActivity, "Login Incorreto", Toast.LENGTH_SHORT).show()
            }
        }
    }
}