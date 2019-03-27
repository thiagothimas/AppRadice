package thiago.app_radice

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import kotlinx.android.synthetic.main.activity_tela_inicial.*
import kotlinx.android.synthetic.main.login.*

class MainActivity : DebugActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)


        imagem_login.setImageResource(R.drawable.radice_logo2)
        texto_login.setText(R.string.mensagem_inicial)

        botao_login.setOnClickListener {onClickLogin()}
}

    private val context: Context get() = this

    fun onClickLogin() {
        Toast.makeText(context, "Efetuou o Login", Toast.LENGTH_SHORT).show()

        // Criação da intent para navegar entre telas/activities
        val intent = Intent(this, TelaInicialActivity::class.java)

        // Colocar um parametro do tipo String
        intent.putExtra("nomeUsuario",campo_usuario.text.toString())
        intent.putExtra("numero", 10)

        startActivityForResult(intent, 1)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (requestCode == 1) {
            val result = data?.getStringExtra("result")
            Toast.makeText(context, result, Toast.LENGTH_SHORT).show()
        }
    }
}
