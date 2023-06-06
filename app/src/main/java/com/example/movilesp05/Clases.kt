package com.example.movilesp05

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "Lenguaje")
data class countryLanguage(
    @PrimaryKey val languageId: Int,
    val languaje: String,
    val isOfficial: Boolean,
    val percentage: Float,
)

data class LenguajesConPaises(
    @Embedded val lenguaje: countryLanguage,
    @Relation(
        parentColumn = "languageId",
        entityColumn = "countryId"
    )
    val paises: List<country>
)

@Entity(primaryKeys = ["languageId", "countryId"])
data class LenguajesyPaises(
    val lenguageId: Int,
    val countryId: Int,
)

data class PaisesConLenguaje(
    @Embedded val pais: country,
    @Relation(
        parentColumn = "countryId",
        entityColumn = "languageId"
    )
    val idiomas: List<countryLanguage>
)

@Entity(tableName = "Pais")
data class country(
    @PrimaryKey val countryId: Int,
    val name: String,
    val continent: String,
    val surfaceArea: Float,
    val population: Int,
    val gobernant: String,
)

data class PaisConCiudades(
    @Embedded val pais: country,
    @Relation(
        parentColumn = "countryId",
        entityColumn = "countryIdFK"
    )
    val ciudades: List<city>
)

@Entity(tableName = "Ciudad")
data class city(
    @PrimaryKey val cityId: Int,
    val name: String,
    val countryIdFK: Int,
    val district: String,
    val population: Int,
)

data class CiudadConPuntos(
    @Embedded val ciudad: city,
    @Relation(
        parentColumn = "cityId",
        entityColumn = "cityIdFK"
    )
    val puntoTuristico: List<puntoTuristico>
)

@Entity(tableName = "PuntoTuristico")
data class puntoTuristico(
    @PrimaryKey val turisticId: Int,
    val name: String,
    val description: String,
    val tax: Float = 0f,
    val cityIdFK: Int,
)

data class PuntoAndGPS(
    @Embedded val point: puntoTuristico,
    @Relation(
        parentColumn = "turisticId",
        entityColumn = "turisticIdFK"
    )
    val pointGPS: puntoGPS
)

@Entity(tableName = "PuntoGPS")
data class puntoGPS(
    @PrimaryKey val pointId: Int,
    val pos: String,
    val turisticIdFK: Int,
)



