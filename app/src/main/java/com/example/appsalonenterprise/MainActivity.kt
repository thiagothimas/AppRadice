package com.example.appsalonenterprise

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import kotlinx.android.synthetic.main.activity_servicos.*
import kotlinx.android.synthetic.main.activity_tela_inicial.*
import kotlinx.android.synthetic.main.login.*

class MainActivity : DebugActivity() {

    private val context: Context get() = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        campo_imagem.setImageResource(R.drawable.radice_logo2)
        texto_login.setText(R.string.mensagem_inicial)

        botao_login.setOnClickListener {onClickLogin()}

    }

    override fun onResume() {
        super.onResume()
        campo_usuario.setText(Prefs.getString("lembrarNome"))
        campo_senha.setText(Prefs.getString("lembrarSenha"))
        checkBox.isChecked = Prefs.getBoolean("lembrar")
    }


    fun onClickLogin(){
        val campoUsuario = findViewById<EditText>(R.id.campo_usuario)
        val campoSenha = findViewById<EditText>(R.id.campo_senha)

        val valorUsuario = campoUsuario.text.toString()
        val valorSenha = campoSenha.text.toString()

        Prefs.setBoolean("lembrar", checkBox.isChecked)

        if (checkBox.isChecked) {
            Prefs.setString("lembrarNome", valorUsuario)
            Prefs.setString("lembrarSenha", valorSenha)
        } else {
            Prefs.setString("lembrarNome", "")
            Prefs.setString("lembrarSenha", "")
        }

        val intent = Intent(context, TelaInicialActivity::class.java)

        intent.putExtra("numero", 10)

        // startActivity(intent)

        startActivityForResult(intent, 1)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (requestCode == 1) {
            val result = data?.getStringExtra("result")
            Toast.makeText(context, result, Toast.LENGTH_SHORT).show()
        }
    }
}