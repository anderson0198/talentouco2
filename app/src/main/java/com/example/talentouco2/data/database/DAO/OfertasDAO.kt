package com.example.talentouco2.data.database.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.talentouco2.data.database.entities.OfertasEntity

@Dao
interface OfertasDAO {
    @Query("SELECT * FROM OfertasEntity")
    fun getAll(): List<OfertasEntity>

    @Query("SELECT * FROM OfertasEntity WHERE id = :id")
    fun findById (id: Int) : OfertasEntity

    @Insert
    fun insertAll (vararg ofertasEntity: OfertasEntity)

    @Update
    fun update (ofertasEntity: OfertasEntity)

    @Delete
    fun delete (ofertasEntity: OfertasEntity)

    @Query("DELETE FROM OfertasEntity")
    fun deleteAll()
}