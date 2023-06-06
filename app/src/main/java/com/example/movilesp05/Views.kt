package com.example.movilesp05

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Menu(onBack: () -> Unit, cityList: () -> Unit, countryList: () -> Unit, turisticList: () -> Unit, viewModel: DatosViewModel, estado: EstadoApp){
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Button(onClick = cityList, modifier = Modifier.size(200.dp,70.dp), shape = ShapeDefaults.ExtraLarge) {
            Text(text = "Ciudades", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = countryList, modifier = Modifier.size(200.dp,70.dp), shape = ShapeDefaults.ExtraLarge) {
            Text(text = "Paises", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = turisticList, modifier = Modifier.size(200.dp,70.dp), shape = ShapeDefaults.ExtraLarge) {
            Text(text = "Turisticos", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = { viewModel.limpiarDB() }, modifier = Modifier.size(150.dp,50.dp), shape = ShapeDefaults.ExtraLarge) {
            Text(text = "Borrar DB", fontSize = 15.sp, fontWeight = FontWeight.Bold)
        }
    }

}

@Composable
fun ListaCiudad(onBack: () -> Unit, formCity: () -> Unit, viewModel: DatosViewModel, estado: EstadoApp){
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        LazyColumn(
        ) {
            items(estado.listaCiudades){
                VistaCiudad(c = it, dvm = viewModel)
            }

        }
        Button(onClick = formCity) {
            Text(text = "Agregar")
        }
    }
}

@Composable
fun ListaPaises(onBack: () -> Unit, formCountry: () -> Unit, viewModel: DatosViewModel, estado: EstadoApp){
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        LazyColumn(
        ) {
            items(estado.listaPaises){
                VistaPais(c = it, dvm = viewModel)
            }

        }
        Button(onClick = formCountry) {
            Text(text = "Agregar")
        }
    }
}

@Composable
fun ListaTuristivos(onBack: () -> Unit, formTuristic: () -> Unit, viewModel: DatosViewModel, estado: EstadoApp){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.Top
        ) {
        Button(onClick = {viewModel.resetVisibles()}, modifier = Modifier
            .height(45.dp)
            .padding(5.dp)
            .clip(CircleShape)) { Text(text = "Todos")}
        Button(onClick = {viewModel.buscarGratis()}, modifier = Modifier
            .height(45.dp)
            .padding(5.dp)
            .clip(CircleShape)) { Text(text = "Gratis")}
        Button(onClick = {viewModel.buscarPago()}, modifier = Modifier
            .height(45.dp)
            .padding(5.dp)
            .clip(CircleShape)) { Text(text = "De Pago")}
    }
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {

        LazyColumn(
        ) {
            items(estado.listaVisible){
                VistaTuristicos(c = it, dvm = viewModel)
            }

        }
        Button(onClick = formTuristic) {
            Text(text = "Agregar")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormularioCiudad(dvm: DatosViewModel, onBack: () -> Unit) {
    var id by remember { mutableStateOf("0") }
    var nombre by remember { mutableStateOf("Ciudad") }
    var estado by remember { mutableStateOf("Estado") }
    var habitantes by remember { mutableStateOf("1234") }
    var idPais by remember { mutableStateOf("0") }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Ciudad")
        OutlinedTextField(
            value = id,
            onValueChange = { id = it },
            label = { Text("IdCiudad") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = estado,
            onValueChange = { estado = it },
            label = { Text("Estado") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = habitantes,
            onValueChange = { habitantes = it },
            label = { Text("Habitantes") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = idPais,
            onValueChange = { idPais = it },
            label = { Text("IdPais") },
            modifier = Modifier.fillMaxWidth()
        )
        Button(onClick = {dvm.agregarCiudad(city(id.toInt(),nombre,idPais.toInt(), estado, habitantes.toInt())); onBack()}, modifier = Modifier
            .padding(vertical = 10.dp)
            .clip(CircleShape)) {
            Text(text = "GUARDAR")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormularioPais(dvm: DatosViewModel, onBack: () -> Unit){
    var id by remember { mutableStateOf("0") }
    var nombre by remember { mutableStateOf("Pais") }
    var continente by remember { mutableStateOf("Continente") }
    var poblacion by remember { mutableStateOf("0") }
    var extension by remember { mutableStateOf("0") }
    var gobernante by remember { mutableStateOf("Gobernante") }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Pais")
        OutlinedTextField(
            value = id,
            onValueChange = { id = it },
            label = { Text("IdPais") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = continente,
            onValueChange = { continente = it },
            label = { Text("Continente") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = poblacion,
            onValueChange = { poblacion = it },
            label = { Text("Poblacion") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = extension,
            onValueChange = { extension = it },
            label = { Text("Extension") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = gobernante,
            onValueChange = { gobernante = it },
            label = { Text("Gobernante") },
            modifier = Modifier.fillMaxWidth()
        )
        Button(onClick = {dvm.agregarPais(country(id.toInt(),nombre,continente,extension.toFloat(),poblacion.toInt(),gobernante)); onBack()}, modifier = Modifier
            .padding(vertical = 10.dp)
            .clip(CircleShape)) {
            Text(text = "GUARDAR")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormularioTuristico(dvm: DatosViewModel, onBack: () -> Unit){
    var id by remember { mutableStateOf("0") }
    var nombre by remember { mutableStateOf("Nombre") }
    var descripcion by remember { mutableStateOf("Descripcion") }
    var tax by remember { mutableStateOf("0") }
    var cityId by remember { mutableStateOf("0") }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Punto Turistico")
        OutlinedTextField(
            value = id,
            onValueChange = { id = it },
            label = { Text("IdTuristico") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = descripcion,
            onValueChange = { descripcion = it },
            label = { Text("Descripcion") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = tax,
            onValueChange = { tax = it },
            label = { Text("Precio") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = cityId,
            onValueChange = { cityId = it },
            label = { Text("Id Ciudad") },
            modifier = Modifier.fillMaxWidth()
        )
        Button(onClick = {dvm.agregarPunto(puntoTuristico(id.toInt(),nombre,descripcion,tax.toFloat(),cityId.toInt())); onBack()}, modifier = Modifier
            .padding(vertical = 10.dp)
            .clip(CircleShape)) {
            Text(text = "GUARDAR")
        }
    }
}

@Composable
fun VistaCiudad(c: city, dvm: DatosViewModel) {
    Column(
        modifier = Modifier
            .padding(horizontal = 30.dp, vertical = 5.dp)
            .clip(ShapeDefaults.Large)
            .background(Color.LightGray)
            .fillMaxWidth()
            .heightIn(30.dp, 200.dp)

    ) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = c.name,
                fontSize = 30.sp,
                color = Color.Black,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .weight(1f)
            )
            Text(
                text = c.cityId.toString(),
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier
                    .padding(horizontal = 5.dp)
                    .weight(.3f)
            )
        }
        Text(
            text = "Estado: ${c.district}",
            fontSize = 15.sp,
            color = Color.Black,
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .heightIn(20.dp, 250.dp)
        )
        Text(
            text = "Habitantes: ${c.population}",
            fontSize = 15.sp,
            color = Color.Black,
            modifier = Modifier
                .padding(horizontal = 10.dp)
        )
        Text(
            text = "Pais: ${dvm.getNombrePais(c.countryIdFK)} [${c.countryIdFK}]",
            fontSize = 15.sp,
            color = Color.Black,
            modifier = Modifier
                .padding(horizontal = 10.dp)
        )

    }
}

@Composable
fun VistaPais(c: country, dvm: DatosViewModel) {
    Column(
        modifier = Modifier
            .padding(horizontal = 30.dp, vertical = 5.dp)
            .clip(ShapeDefaults.Large)
            .background(Color.LightGray)
            .fillMaxWidth()
            .heightIn(30.dp, 200.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = c.name,
                fontSize = 30.sp,
                color = Color.Black,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .weight(1f)
            )
            Text(
                text = c.countryId.toString(),
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier
                    .padding(horizontal = 5.dp)
                    .weight(.3f)
            )
        }
        Text(
            text = "Continente: ${c.continent}",
            fontSize = 15.sp,
            color = Color.Black,
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .heightIn(20.dp, 250.dp)
        )
        Text(
            text = "Habitantes: ${c.population}",
            fontSize = 15.sp,
            color = Color.Black,
            modifier = Modifier
                .padding(horizontal = 10.dp)
        )
        Text(
            text = "Extension: ${c.surfaceArea}",
            fontSize = 15.sp,
            color = Color.Black,
            modifier = Modifier
                .padding(horizontal = 10.dp)
        )
        Text(
            text = "Gobernante: ${c.gobernant}",
            fontSize = 15.sp,
            color = Color.Black,
            modifier = Modifier
                .padding(horizontal = 10.dp)
        )

    }
}

@Composable
fun VistaTuristicos(c: puntoTuristico, dvm: DatosViewModel) {
    Column(
        modifier = Modifier
            .padding(horizontal = 30.dp, vertical = 5.dp)
            .clip(ShapeDefaults.Large)
            .background(Color.LightGray)
            .fillMaxWidth()
            .heightIn(30.dp, 200.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = c.name,
                fontSize = 30.sp,
                color = Color.Black,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .weight(1f)
            )
            Text(
                text = c.turisticId.toString(),
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier
                    .padding(horizontal = 5.dp)
                    .weight(.3f)
            )
        }
        Text(
            text = "Descripcion: ${c.description}",
            fontSize = 15.sp,
            color = Color.Black,
            modifier = Modifier
                .padding(horizontal = 10.dp)
        )
        Text(
            text = "Precio: ${c.tax}",
            fontSize = 15.sp,
            color = Color.Black,
            modifier = Modifier
                .padding(horizontal = 10.dp)
        )
        Text(
            text = "Ciudad: ${dvm.getNombreCiudad(c.cityIdFK)} [${c.cityIdFK}]",
            fontSize = 15.sp,
            color = Color.Black,
            modifier = Modifier
                .padding(horizontal = 10.dp)
        )

    }
}