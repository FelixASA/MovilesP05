package com.example.movilesp05

class RepoFunctions(private val dao:DaoTables) {
    val paises=dao.getPaises()
    val ciudades=dao.getCiudades()
    val puntos=dao.getTuristicos()
    val idiomas=dao.getLenguajes()
    val gpss=dao.getGPSs()

    val puntosGratis=dao.getPuntosGratis()
    val puntosPago=dao.getPuntosCosto()
//    fun puntosCiudad(c:Int)=dao.getPuntosByCiudad(c)
//    fun puntosPais(p:Int)=dao.getPuntosByPais(p)


    fun unPais(x:Int)=dao.getPais(x)
    fun unaCiudad(x:Int)=dao.getCiudad(x)
    fun unPunto(x:Int)=dao.getTuristico(x)
    fun unGPS(x:Int)=dao.getGPS(x)
    fun unIdioma(x:Int)=dao.getLenguaje(x)

    suspend fun agregarPais(p:country)=dao.addPais(p)
    suspend fun agregarCiudad(c:city)=dao.addCiudad(c)
    suspend fun agregarPunto(p:puntoTuristico)=dao.addPuntoTuristico(p)
    suspend fun agregarIdioma(i:countryLanguage)=dao.addLenguaje(i)
    suspend fun agregarGPS(g:puntoGPS)=dao.addPuntoGPS(g)

    suspend fun borrarPais(p:country)=dao.removePais(p)
    suspend fun borrarCiudad(c:city)=dao.removeCiudad(c)
    suspend fun borrarPunto(p:puntoTuristico)=dao.removePuntoTuristico(p)
    suspend fun borrarIdioma(i:countryLanguage)=dao.removeLenguaje(i)
    suspend fun borrarGPS(g:puntoGPS)=dao.removePuntoGPS(g)

    suspend fun borrarPaises()=dao.delPaises()
    suspend fun borrarCiudades()=dao.delCiudades()
    suspend fun borrarPuntos()=dao.delTuristicos()
    suspend fun borrarIdiomas()=dao.delLenguajes()
    suspend fun borrarGPSs()=dao.delGPSs()
}