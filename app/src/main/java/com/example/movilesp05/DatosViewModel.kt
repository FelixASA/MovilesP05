package com.example.movilesp05

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DatosViewModel(private val repositorio:RepoFunctions = Graph.repo):ViewModel() {
    private val _estado= MutableStateFlow(EstadoApp())
    val estado : StateFlow<EstadoApp> =_estado

    init {
        loadPuntos()
        loadCiudades()
        loadPaises()
        loadIdiomas()
        loadGPSs()
    }

    fun loadPuntos(){
        viewModelScope.launch(Dispatchers.IO) {
            repositorio.puntos.collectLatest {
                _estado.update { x -> x.copy(listaPuntos = it, listaVisible = it) }
                //
            }
        }
    }
    fun loadPaises(){
        viewModelScope.launch(Dispatchers.IO) {
            repositorio.paises.collectLatest {
                _estado.update { x -> x.copy(listaPaises = it) }
            }
        }
    }

    fun loadCiudades(){
        viewModelScope.launch(Dispatchers.IO){
            repositorio.ciudades.collectLatest{
                _estado.update { x -> x.copy(listaCiudades = it) }
            }
        }
    }
    fun loadIdiomas(){
        viewModelScope.launch(Dispatchers.IO){
            repositorio.idiomas.collectLatest {
                _estado.update { x -> x.copy(listaIdiomas = it) }
            }
        }
    }
    fun loadGPSs(){
        viewModelScope.launch(Dispatchers.IO){
            repositorio.gpss.collectLatest {
                _estado.update { x -> x.copy(listaGPS = it) }
            }
        }
    }

    fun agregarPais(p:country){
        viewModelScope.launch(Dispatchers.IO) {
            repositorio.agregarPais(p)
        }

    }

    fun agregarCiudad(c:city){
        viewModelScope.launch(Dispatchers.IO) {
            repositorio.agregarCiudad(c)
        }
    }

    fun agregarPunto(p:puntoTuristico){
        viewModelScope.launch(Dispatchers.IO){
            repositorio.agregarPunto(p)
        }

    }

    fun agregarIdioma(i:countryLanguage){
        viewModelScope.launch(Dispatchers.IO) { repositorio.agregarIdioma(i) }

    }
    fun agregarGps(g:puntoGPS){
        viewModelScope.launch(Dispatchers.IO) { repositorio.agregarGPS(g) }

    }

    fun limpiarDB(){
        viewModelScope.launch(Dispatchers.IO) {
            repositorio.borrarPaises()
            repositorio.borrarCiudades()
            repositorio.borrarIdiomas()
            repositorio.borrarPuntos()
            repositorio.borrarGPSs()
        }
    }

    fun getNombrePais(x:Int): String{
        val lista = _estado.value.listaPaises
        if(lista.isEmpty())
            return "Desconocido"
        val res=lista.find { it.countryId == x }
        if (res != null) {
            return res.name
        }
        return "Desconocido"
    }

    fun getNombreCiudad(x:Int): String{
        val lista = _estado.value.listaCiudades
        if(lista.isEmpty())
            return "Desconocido"
        val res=lista.find { it.cityId == x }
        if (res != null) {
            return res.name
        }
        return "Desconocido"
    }

    fun buscarGratis(){
        viewModelScope.launch {
            repositorio.puntosGratis.collectLatest{
                _estado.update { x -> x.copy(listaVisible = it) }
            }
        }
    }

    fun buscarPago(){
        viewModelScope.launch {
            repositorio.puntosPago.collectLatest{
                _estado.update { x -> x.copy(listaVisible = it) }
            }
        }
    }


    fun resetVisibles(){
        _estado.update { x -> x.copy(listaVisible = x.listaPuntos)}
    }

}

data class EstadoApp (
    val fragmentoVisible:Int=0,
    val modo:Int=0, //0-Puntos 1-Ciudades 2-Paises
    val listaPaises: List<country> = listOf(),
    val listaCiudades: List<city> = listOf(),
    val listaPuntos: List<puntoTuristico> = listOf(),
    val listaIdiomas: List<countryLanguage> = listOf(),
    val listaGPS: List<puntoGPS> = listOf(),
    val listaVisible: List<puntoTuristico> = listOf(),
//    val paisActual:Pais?= Pais(0,"PaisDEF",0.0f,"America",1234,"Presidente"),
//    val ciudadActual:Ciudad?= Ciudad(0,"CiudadDEF","EstadoDef",1234,0),
//    val puntoActual:PuntoTuristico?= PuntoTuristico(0,"PuntoDEF","DESC",0f,0,0),
//    val gpsActual:Gps=Gps(0, 0.0,0.0,0.0)
)