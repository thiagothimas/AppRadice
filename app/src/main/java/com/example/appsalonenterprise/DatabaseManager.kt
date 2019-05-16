package com.example.appsalonenterprise

import android.arch.persistence.room.Room

object DatabaseManager {

    private var dbInstance: LMSDatabase

    init {
        val appContext = LMSApplication.getInstance().applicationContext
        dbInstance = Room.databaseBuilder(
            appContext,
            LMSDatabase::class.java,
            "lms.sqlite"
        ).build()
    }

    fun getDisciplinaDAO(): DisciplinaDAO {
        return dbInstance.disciplinaDAO()
    }
}