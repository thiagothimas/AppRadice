package com.example.appsalonenterprise

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_disciplina.*
import kotlinx.android.synthetic.main.activity_disciplina.nomeDisciplina
import kotlinx.android.synthetic.main.activity_disciplina_cadastro.*

class DisciplinaCadastroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_disciplina_cadastro)
        setTitle("Nova Disciplina")

        salvarDisciplina.setOnClickListener {
            val disciplina = Disciplina()
            disciplina.nome = nomeDisciplina.text.toString()
            disciplina.ementa = ementaDisciplina.text.toString()
            disciplina.professor = professorDisciplina.text.toString()
            disciplina.foto = urlFoto.text.toString()

            taskAtualizar(disciplina)
        }
    }

    private fun taskAtualizar(disciplina: Disciplina) {
        Thread {
            DisciplinaService.save(disciplina)
            runOnUiThread {
                finish()
            }
        }.start()
    }

}
