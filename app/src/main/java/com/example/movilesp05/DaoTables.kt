package com.example.movilesp05

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface DaoTables{
    //Insert
    @Upsert
    suspend fun addCiudad(ciudad:city)

    @Upsert
    suspend fun addPais(pais:country)

    @Upsert
    suspend fun addLenguaje(lenguaje: countryLanguage)

    @Upsert
    suspend fun addPuntoTuristico(turistic: puntoTuristico)

    @Upsert
    suspend fun addPuntoGPS(gps: puntoGPS)

    //Delete
    @Delete
    suspend fun removeCiudad(ciudad:city)

    @Delete
    suspend fun removePais(pais:country)

    @Delete
    suspend fun removeLenguaje(lenguaje: countryLanguage)

    @Delete
    suspend fun removePuntoTuristico(turistic: puntoTuristico)

    @Delete
    suspend fun removePuntoGPS(gps: puntoGPS)

    //Delete All
    @Query("DELETE FROM Pais")
    suspend fun delPaises()

    @Query("DELETE FROM Ciudad")
    suspend fun delCiudades()

    @Query("DELETE FROM Lenguaje")
    suspend fun delLenguajes()

    @Query("DELETE FROM PuntoTuristico")
    suspend fun delTuristicos()

    @Query("DELETE FROM PuntoGPS")
    suspend fun delGPSs()

    //Get One
    @Query("SELECT * FROM Pais WHERE countryId =:idGet")
    fun getPais(idGet:Int):Flow<country>

    @Query("SELECT * FROM Ciudad WHERE cityId =:idGet")
    fun getCiudad(idGet:Int):Flow<city>

    @Query("SELECT * FROM Lenguaje WHERE languageId =:idGet")
    fun getLenguaje(idGet:Int):Flow<countryLanguage>

    @Query("SELECT * FROM PuntoTuristico WHERE turisticId =:idGet")
    fun getTuristico(idGet:Int):Flow<puntoTuristico>

    @Query("SELECT * FROM PuntoGPS WHERE pointId =:idGet")
    fun getGPS(idGet:Int):Flow<puntoGPS>

    //Get all
    @Query("SELECT * FROM Pais")
    fun getPaises(): Flow<List<country>>

    @Query("SELECT * FROM Lenguaje")
    fun getLenguajes(): Flow<List<countryLanguage>>

    @Query("SELECT * FROM Ciudad")
    fun getCiudades(): Flow<List<city>>

    @Query("SELECT * FROM PuntoTuristico")
    fun getTuristicos(): Flow<List<puntoTuristico>>

    @Query("SELECT * FROM PuntoGPS")
    fun getGPSs(): Flow<List<puntoGPS>>

    //Busquedas
    @Query("SELECT * FROM PuntoTuristico WHERE tax=0")
    fun getPuntosGratis():Flow<List<puntoTuristico>>

    @Query("SELECT * FROM PuntoTuristico WHERE tax>0")
    fun getPuntosCosto():Flow<List<puntoTuristico>>

    //Relaciones de tablas
    @Transaction
    @Query("SELECT * FROM PuntoTuristico")
    fun getTuristicosyGPS(): List<PuntoAndGPS>

    @Transaction
    @Query("SELECT * FROM Ciudad")
    fun getCiudadyPuntos(): List<CiudadConPuntos>

    @Transaction
    @Query("SELECT * FROM Pais")
    fun getPaisyCiudades(): List<PaisConCiudades>

    @Transaction
    @Query("SELECT * FROM Lenguaje")
    fun getLenguajesConPaises(): List<LenguajesConPaises>

    @Transaction
    @Query("SELECT * FROM Pais")
    fun getPaisesConLenguajes(): List<PaisesConLenguaje>



}