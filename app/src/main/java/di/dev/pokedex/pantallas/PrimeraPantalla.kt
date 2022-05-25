package di.dev.pokedex.pantallas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import di.dev.pokedex.navegacion.NavegacionPantallas


@Composable
fun PrimeraPantalla(navController: NavController){
    //Scaffold hace que aparezca el nombre de Pokedex en la primera pantalla de la aplicación.
    Scaffold(topBar = {
        TopAppBar() {
            Text(text = "Pokedex")
        }
    }) {
        Detalle(navController)
    }
}

@Composable
fun Detalle(navController: NavController){
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){
        Text(text =  "Primera Prantalla")
        Button(onClick = { navController.navigate(route = NavegacionPantallas.PokemonsPantalla.pantalla) }) {
            Text(text = "Click para ver pokemon")
        }
    }
}