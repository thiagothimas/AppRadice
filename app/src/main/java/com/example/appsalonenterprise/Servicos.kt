package com.example.appsalonenterprise

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_servicos.*
import kotlinx.android.synthetic.main.activity_tela_inicial.*

class Servicos : AppCompatActivity() {

    private val context: Context get() = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_servicos)

        botao_voltar.setOnClickListener { onClickVoltar() }


    }



        fun onClickVoltar() {
            val intent = Intent(context, TelaInicialActivity::class.java)

            startActivityForResult(intent,1)

        }

}
