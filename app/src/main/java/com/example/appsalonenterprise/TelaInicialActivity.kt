package com.example.appsalonenterprise

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.*
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_tela_inicial.*


class TelaInicialActivity : DebugActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val context: Context get() = this
    private var disciplinas = listOf<Disciplina>()
    var recyclerDisc: RecyclerView? = null
    private var REQUEST_CADASTRO = 1
    private var REQUEST_REMOVE = 2


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_inicial)

        //botao_services.setOnClickListener {onClickServicos()}
        //botao_sair.setOnClickListener {cliqueSair()}

        //val args = intent.extras
        //val nome = args.getString("nome")
        //val numero = intent.getIntExtra("nome",0)

        //Toast.makeText(context, "Parâmetro: $nome", Toast.LENGTH_LONG).show()
        //Toast.makeText(context, "Numero: $numero", Toast.LENGTH_LONG).show()

        //val mensagem = findViewById<TextView>(R.id.mensagemTelaInicial)
        //mensagem.text = "Bem Vindo"


        // Toolbar
        var toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.title = "Menu Principal"

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // configuração do menu lateral
        configuraMenuLateral()

        recyclerDisc = findViewById<RecyclerView>(R.id.recyclerDisciplinas)
        recyclerDisc?.layoutManager = LinearLayoutManager(context)
        recyclerDisc?.itemAnimator = DefaultItemAnimator()
        recyclerDisc?.setHasFixedSize(true)
    }

    override fun onResume() {
        super.onResume()
        taskDisciplinas()
    }

    fun taskDisciplinas() {
        Thread {
            disciplinas = DisciplinaService.getDisciplinas(context)
            runOnUiThread {
                recyclerDisc?.adapter = DisciplinaAdapter(this.disciplinas) {onClickDisciplina(it)}
            }
        }.start()
    }

    fun onClickDisciplina(disciplina: Disciplina) {
        Toast.makeText(context, "Clicou ${disciplina.nome}", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, DisciplinaActivity::class.java)
        intent.putExtra("disciplina", disciplina)
        startActivityForResult(intent, REQUEST_REMOVE)

    }



    //fun onClickServicos() {
    //    val intent = Intent(context, Servicos::class.java)

    //    startActivityForResult(intent,1)
    // }

    // fun cliqueSair() {
    //     val intent = Intent(context, MainActivity::class.java)

    //    startActivityForResult(intent,1)
    // }

   // fun cliqueSair() {
   //     val returnIntent = Intent()
   //     returnIntent.putExtra("result","Saiu do RadiceApp")
    //    setResult(Activity.RESULT_OK,returnIntent)
   //     finish()
   // }



    private fun configuraMenuLateral() {
        var toolbar = findViewById<Toolbar>(R.id.toolbar)
        var menuLateral = findViewById<DrawerLayout>(R.id.layourMenuLateral)

        // ícone de menu (hamburger) para mostrar o menu
        var toogle = ActionBarDrawerToggle(
            this,
            menuLateral,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )

        menuLateral.addDrawerListener(toogle)
        toogle.syncState()

        val navigationView = findViewById<NavigationView>(R.id.menu_lateral)
        navigationView.setNavigationItemSelectedListener(this)
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_servicos -> {
                Toast.makeText(this, "Clicou Serviços", Toast.LENGTH_SHORT).show()
            }

            R.id.nav_cadastros -> {
                Toast.makeText(this, "Clicou Cadastros", Toast.LENGTH_SHORT).show()
            }

            R.id.nav_produtos -> {
                Toast.makeText(this, "Clicou Produtos", Toast.LENGTH_SHORT).show()
            }

            R.id.nav_estoque -> {
                Toast.makeText(this, "Clicou Estoque", Toast.LENGTH_SHORT).show()
            }

            R.id.nav_config -> {
                Toast.makeText(this, "Clicou Configurações", Toast.LENGTH_SHORT).show()
            }
        }

        val drawer = findViewById<DrawerLayout>(R.id.layourMenuLateral)
        drawer.closeDrawer(GravityCompat.START)
        return true
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
            Toast.makeText(this, "Clicou em Buscar", Toast.LENGTH_LONG).show()
        } else if (id == R.id.action_atualizar) {
            Toast.makeText(this, "Clicou em Atualizar", Toast.LENGTH_LONG).show()
        } else if (id == R.id.action_config) {
            Toast.makeText(this, "Clicou em Configurações", Toast.LENGTH_LONG).show()
        } else if (id == R.id.action_adicionar) {
            // iniciar cadastro
            val intent = Intent(context, DisciplinaCadastroActivity::class.java)
            startActivityForResult(intent, REQUEST_CADASTRO)
        }

        else if (id == android.R.id.home) {
            finish()
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CADASTRO || requestCode == REQUEST_REMOVE) {
            taskDisciplinas()
        }
    }

}