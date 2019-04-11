package com.example.appsalonenterprise

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
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


    fun onClickLogin(){
        //val campoUsuario = findViewById<EditText>(R.id.campo_usuario)
        //val campoSenha = findViewById<EditText>(R.id.campo_senha)
        //val valorUsuario = campoUsuario.text.toString()
        //val valorSenha = campoSenha.text.toString()

        //Toast.makeText(context, "$valorUsuario : $valorSenha", Toast.LENGTH_LONG).show()

        // criar intent
        val intent = Intent(context, TelaInicialActivity::class.java)

        // enviar parĂ¢metros simplificado
        intent.putExtra("numero", 10)

        // fazer a chamada
        //startActivity(intent)

        // fazer a chamada esperando resultado
        startActivityForResult(intent, 1)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (requestCode == 1) {
            val result = data?.getStringExtra("result")
            Toast.makeText(context, result, Toast.LENGTH_SHORT).show()
        }
    }
}