package di.dev.pokedex.navegacion

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import di.dev.pokedex.pantallas.PokemonsPantalla
import di.dev.pokedex.pantallas.PrimeraPantalla

@Composable
fun NavegacionApp(){
    //rememberNavController permite desplazarnos entre pantallas dentro de la app
    val navController = rememberNavController()
    //navHost es un contenedor que contiene las pantallas en las que nos vamos a desplazar
    NavHost(navController = navController, startDestination = NavegacionPantallas.PrimeraPantalla.pantalla){
        composable(route = NavegacionPantallas.PrimeraPantalla.pantalla){
            PrimeraPantalla(navController)
        }
        composable(route = NavegacionPantallas.PokemonsPantalla.pantalla){
            PokemonsPantalla(navController)
        }
    }
}