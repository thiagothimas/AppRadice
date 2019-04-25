package com.example.appsalonenterprise

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.net.URL

object DisciplinaService {

    val host = "http://fesousa.pythonanywhere.com"
    val TAG = "WS_LMSApp"

    fun getDisciplinas(context: Context) : List<Disciplinas> {

        val url = "$host/disciplinas"
        val json = URL(url).readText()
        Log.d(TAG, json)
        return perserJson<List<Disciplinas>>(json)
    }

    inline fun <reified T> parserJson(json: String) : T {
        val type = object: TypeToken<T>(){}.type
        return Gson().fromJson<T>(json, type)
    }
}