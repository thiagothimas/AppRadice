package com.example.appsalonenterprise

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.adapter_disciplina.*

class DisciplinaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_disciplina)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
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

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId
        if (id == R.id.action_remover) {
            AlertDialog.Builder(this)

                .setTitle(R.string.app_name)
                .setMessage("Deseja excluir a disciplina")
                .setPositiveButton("Sim") {
                        dialog, which ->
                    dialog.dismiss()
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

}
