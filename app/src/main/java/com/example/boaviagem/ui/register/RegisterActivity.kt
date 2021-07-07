package com.example.boaviagem.ui.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.lifecycleScope
import com.example.boaviagem.R
import com.example.boaviagem.controller.UsuarioController
import com.example.boaviagem.data.model.Usuario
import com.example.boaviagem.ui.home.HomeActivity
import com.example.boaviagem.ui.login.LoginActivity
import kotlinx.coroutines.launch

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_register)

        val email = findViewById<EditText>(R.id.editTextTextEmailAddress)
        val personName = findViewById<EditText>(R.id.editTextTextPersonName)
        val password = findViewById<EditText>(R.id.editTextTextPassword)
        val butao = findViewById<Button>(R.id.button)

        val usuarioController = UsuarioController(applicationContext)

        butao.setOnClickListener {
            val usuario = Usuario(
                email = email.text.toString(),
                name = personName.text.toString(),
                password = password.text.toString()
            )

            lifecycleScope.launch {
                usuarioController.cadastrarUsuario(usuario)

                val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}