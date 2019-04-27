package com.example.appsalonenterprise

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.net.URL
import java.util.ArrayList

object DisciplinaService {

    val host = "http://fesousa.pythonanywhere.com"
    val TAG = "WS_LMSApp"

    fun getDisciplinas(context: Context) : List<Disciplina> {
        if (AndroidUtils.isInternetDisponivel(context)) {
            val url = "$host/disciplinas"
            val json = HttpHelper.get(url)
            Log.d(TAG, json)
            return parserJson<List<Disciplina>>(json)
        } else {
            return ArrayList()
        }
    }

    fun saveDisciplina(disciplina: Disciplina) : Response {
        val json = HttpHelper.post("$host/disciplinas", disciplina.toJson())
        return parserJson(json)
    }

    inline fun <reified T> parserJson(json: String) : T {
        val type = object: TypeToken<T>(){}.type
        return Gson().fromJson<T>(json, type)
    }
}