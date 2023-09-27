package com.example.talentouco2.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.talentouco2.data.database.DAO.OfertasDAO
import com.example.talentouco2.data.database.entities.OfertasEntity

@Database(entities = [OfertasEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    companion object{
        private const val DATABASE_NAME = "TalentoUCO.db"
        private lateinit var instance: AppDatabase

        fun getInstance(context: Context?): AppDatabase {
            if (::instance.isInitialized) {
                return instance
            }
            else{
                instance =  Room.databaseBuilder(context!!.applicationContext, AppDatabase::class.java, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build()
            }
            return instance
        }
    }
    abstract fun ofertasDAO(): OfertasDAO
}