package com.example.movilesp05

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.movilesp05.ui.theme.MovilesP05Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovilesP05Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavigationView()
                }
            }
        }
    }
}

@Composable
fun NavigationView(){
    val navController = rememberNavController()
    val viewModel = viewModel(modelClass = DatosViewModel::class.java)
    val estado = viewModel.estado.collectAsState().value

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(
                onMenu = { navController.navigate("menu")}
            )
        }
        composable("menu") {
            Menu(
                onBack = { navController.popBackStack() },
                cityList = { navController.navigate("cityList") },
                countryList = { navController.navigate("countryList") },
                turisticList = { navController.navigate("turisticList")},
                viewModel = viewModel,
                estado = estado
            )
        }
        composable("cityList"){
            ListaCiudad(
                onBack = { navController.popBackStack() },
                formCity = { navController.navigate("cityForm") },
                viewModel = viewModel,
                estado = estado)
        }
        composable("countryList"){
            ListaPaises(
                onBack = { navController.popBackStack() },
                formCountry = { navController.navigate("countryForm") },
                viewModel = viewModel,
                estado = estado)
        }
        composable("turisticList"){
            ListaTuristivos(
                onBack = { navController.popBackStack() },
                formTuristic = { navController.navigate("turisticForm") },
                viewModel = viewModel,
                estado = estado
            )
        }
        composable("cityForm"){
            FormularioCiudad(
                dvm = viewModel,
                onBack = { navController.popBackStack() }
            )
        }
        composable("countryForm"){
            FormularioPais(
                dvm = viewModel,
                onBack = { navController.popBackStack() }
            )
        }
        composable("turisticForm"){
            FormularioTuristico(
                dvm = viewModel,
                onBack = { navController.popBackStack() }
            )
        }

    }
}

@Composable
fun HomeScreen(onMenu: () -> Unit) {
    Column(modifier = Modifier
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text(text = stringResource(R.string.Titulo), fontWeight = FontWeight.Bold, fontSize = 30.sp)
        Spacer(modifier = Modifier.height(50.dp))
        Button(onClick = onMenu) {
            Text(text = "Iniciar", fontWeight = FontWeight.Bold, fontSize = 30.sp, modifier = Modifier.size(140.dp,50.dp), textAlign = TextAlign.Center)
        }
    }
}