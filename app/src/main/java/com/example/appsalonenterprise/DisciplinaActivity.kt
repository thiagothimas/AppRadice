package com.example.appsalonenterprise

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.adapter_disciplina.*


class DisciplinaActivity : DebugActivity() {

    private val context: Context get() = this
    var disciplina: Disciplina? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_disciplina)

        disciplina = intent.getSerializableExtra("disciplina") as Disciplina

        var toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.title = disciplina?.nome

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        var texto = findViewById<TextView>(R.id.nomeDisciplina)
        texto.text = disciplina?.nome
        var imagem = findViewById<ImageView>(R.id.imagemDisciplina)
        
        if (disciplina?.foto != "")
            Picasso.with(this).load(disciplina?.foto).fit().into(imagem,
                object: com.squareup.picasso.Callback{
                    override fun onSuccess() {}
                    override fun onError() {}
                })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main_disciplina, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId

        if (id == R.id.action_remover) {
            AlertDialog.Builder(this)

                .setTitle(R.string.app_name)
                .setMessage("Deseja excluir a disciplina")
                .setPositiveButton("Sim") {
                        dialog, which -> dialog.dismiss()
                            taskExcluir()
                }.setNegativeButton("Não") {
                        dialog, which -> dialog.dismiss()
                }.create().show()

        }
        else if (id == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun taskExcluir() {
        if (this.disciplina != null && this.disciplina is Disciplina) {
            Thread {
                DisciplinaService.delete(this.disciplina as Disciplina)
                runOnUiThread {
                    finish()
                }
            }.start()
        }
    }

}
