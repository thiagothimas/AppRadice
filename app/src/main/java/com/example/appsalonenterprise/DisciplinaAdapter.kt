package com.example.appsalonenterprise

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.adapter_disciplina.view.*

class DisciplinaAdapter(
        val disciplinas: List<Disciplinas>,
        val onClick: (Disciplinas) -> Unit
):RecyclerView.Adapter<DisciplinaAdapter.DisciplinasViewHolder>() {

    class DisciplinasViewHolder(view: View):
            RecyclerView.ViewHolder(view) {
        val cardNome: TextView
        val cardImg: ImageView
        val cardProgress: ProgressBar
        val cardView: CardView

        init {
            cardNome = view.card_nome
            cardImg = view.card_img
            cardProgress = view.card_progress
            cardView = view.card_disciplina
        }
    }

    override fun getItemCount() = this.disciplinas.size

    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int): DisciplinasViewHolder {

        val view = LayoutInflater.from(parent.context)
                        .inflate(R.layout.adapter_disciplina, parent, false)

        val holder = DisciplinasViewHolder(view)
        return holder
    }

    override fun onBindViewHolder(
            holder: DisciplinasViewHolder,
            position: Int) {

        val contexto = holder.itemView.context
        val disciplina = this.disciplinas[position]

        holder.cardNome.text = disciplina.nome
        holder.cardProgress.visibility = View.VISIBLE

        Picasso.with(contexto).load(disciplina.foto).fit()
                .into(holder.cardImg,
                object: com.squareup.picasso.Callback {
                    override fun onSuccess() {
                        holder.cardProgress.visibility = View.INVISIBLE
                    }

                    override fun onError() {
                        holder.cardProgress.visibility = View.INVISIBLE
                    }
                })
        holder.itemView.setOnClickListener {onClick(disciplina)}


    }

}