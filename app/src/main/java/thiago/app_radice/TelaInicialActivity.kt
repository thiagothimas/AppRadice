package thiago.app_radice

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import android.widget.ActionMenuView
import android.widget.Toast
import android.support.v7.widget.Toolbar
import kotlinx.android.synthetic.main.activity_tela_inicial.*


class TelaInicialActivity : DebugActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_inicial)

        val params = intent.extras
        val nome = intent.getStringExtra("nomeUsuario")
        val numero = intent.getIntExtra("numero", 0)


        mensagemTelaInicial.setText(nome)



        botao_sair.setOnClickListener {
            val returnIntent = Intent()
            returnIntent.putExtra("result", "Você Saiu")
            setResult(Activity.RESULT_OK, returnIntent)
            finish()
        }


        var toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.title = "Menu Principal"

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        (menu?.findItem(R.id.action_buscar)?.actionView as SearchView).setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {

                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {

                return false
            }

        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId
        if (id == R.id.action_buscar) {
            Toast.makeText(this, "Clicou em Buscar", Toast.LENGTH_SHORT).show()
        } else if (id == R.id.action_atualizar) {
            Toast.makeText(this, "Clicou em Atualizar", Toast.LENGTH_SHORT).show()
        } else if (id == R.id.action_config) {
            Toast.makeText(this, "Clicou em Configurações", Toast.LENGTH_SHORT).show()
        } else if (id == android.R.id.home) {
            finish()
        }

        return super.onOptionsItemSelected(item)
    }
}