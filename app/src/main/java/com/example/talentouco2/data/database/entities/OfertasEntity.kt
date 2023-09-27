package com.example.talentouco2.data.database.entities

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity
data class OfertasEntity (
    @PrimaryKey (autoGenerate = true) @ColumnInfo val id:Int,
    @NonNull
    @ColumnInfo val empresa:String,
    @NonNull
    @ColumnInfo val fechaInicial:String,
    @NonNull
    @ColumnInfo val fechaFinal:String,
    @NonNull
    @ColumnInfo val requisitoCarrera:String,
    @NonNull
    @ColumnInfo val requisitoSemestre:Int,
    @NonNull
    @ColumnInfo val salario:String,
    @NonNull
    @ColumnInfo val horario:String,
    @ColumnInfo val descripcion:String,
    @ColumnInfo val imagen:Int,
)