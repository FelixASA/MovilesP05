package com.example.movilesp05

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [country::class,countryLanguage::class,city::class,puntoTuristico::class,puntoGPS::class], version = 1, exportSchema = false)
abstract class AppDB: RoomDatabase() {
    abstract fun appDao():DaoTables

    companion object{
        @Volatile
        var INSTANCE:AppDB?=null
        fun getDB(context: Context):AppDB{
            return INSTANCE?: synchronized(this){
                val instance = Room.databaseBuilder(context,AppDB::class.java,"app_db").build()
                INSTANCE=instance
                return instance
            }
        }
    }

}