package com.example.movilesp05

import android.content.Context

object Graph {
    lateinit var db: AppDB
        private set

    val repo by lazy{
        RepoFunctions(dao = db.appDao())
    }

    fun provide(context: Context){
        db = AppDB.getDB(context)
    }
}